package com.ellfors.mvvmtest.http.interceptor;

import androidx.annotation.NonNull;

import com.ellfors.mvvmtest.base.BaseException;
import com.ellfors.mvvmtest.bean.BaseResponse;
import com.ellfors.mvvmtest.constant.MyCodes;
import com.google.gson.Gson;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * 为Response添加请求Url的拦截器
 * 2018/11/28 16:05
 */
public class ResponseAddUrlInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");

    @Override
    public Response intercept(@NonNull Interceptor.Chain chain) throws IOException {
        //获取request
        Request request = chain.request();
        if (request == null)
            return null;
        //获取请求的Url
        String url = request.url().toString().replace("https://gank.io", "");
        if (url.contains("?"))
            url = url.substring(0, url.indexOf("?"));
        //获取返回
        Response response;
        try {
            response = chain.proceed(request);
        } catch (Exception e) {
            //捕获网络异常
            throw new BaseException("请检查您的网络", MyCodes.HTTP_NET_ERROR, url);
        }
        if (response == null)
            return null;
        //解析返回值
        ResponseBody responseBody = response.body();
        if (responseBody == null)
            return response;
        long contentLength = responseBody.contentLength();
        if (!bodyEncoded(response.headers())) {
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE);
            Buffer buffer = source.buffer();
            Charset charset = UTF8;
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                try {
                    charset = contentType.charset(UTF8);
                } catch (UnsupportedCharsetException e) {
                    return response;
                }
            }
            if (!isPlaintext(buffer))
                return response;
            if (contentLength != 0 && charset != null) {
                String result = buffer.clone().readString(charset);
                BaseResponse baseResponse = null;
                try {
                    baseResponse = new Gson().fromJson(result, BaseResponse.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (baseResponse != null) {
                    //重新拼接返回值，添加url标识
                    baseResponse.setUrl(url);
                    String json = new Gson().toJson(baseResponse);
                    return response
                            .newBuilder()
                            .body(ResponseBody.create(MediaType.parse("application/json"), json))
                            .request(chain.request())
                            .build();
                }
            }
        }
        return response;
    }

    private boolean bodyEncoded(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");
        return contentEncoding != null && !contentEncoding.equalsIgnoreCase("identity");
    }

    private boolean isPlaintext(Buffer buffer) throws EOFException {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted())
                    break;
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint))
                    return false;
            }
            return true;
        } catch (EOFException e) {
            return false;
        }
    }
}
