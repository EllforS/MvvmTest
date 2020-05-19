package com.ellfors.mvvmtest.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * ArticlesBean
 * 2020-05-19 14:50
 */
public class ArticlesBean {
    /**
     * _id : 5e7225f8907078dcbfe9579a
     * author : wuzheng
     * category : GanHuo
     * createdAt : 2017-02-22 16:08:18
     * desc : 美丽说热修复框架
     * images : []
     * likeCounts : 0
     * publishedAt : 2017-02-23 11:50:23
     * stars : 1
     * title : Aceso
     * type : Android
     * url : https://github.com/meili/Aceso
     * views : 3
     */
    private String _id;
    private String author;
    private String category;
    private String createdAt;
    private String desc;
    private int likeCounts;
    private String publishedAt;
    private int stars;
    private String title;
    private String type;
    private String url;
    private int views;
    private List<?> images;

    public String get_id() {
        return _id == null ? "" : _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getAuthor() {
        return author == null ? "" : author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category == null ? "" : category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCreatedAt() {
        return createdAt == null ? "" : createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc == null ? "" : desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getLikeCounts() {
        return likeCounts;
    }

    public void setLikeCounts(int likeCounts) {
        this.likeCounts = likeCounts;
    }

    public String getPublishedAt() {
        return publishedAt == null ? "" : publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type == null ? "" : type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url == null ? "" : url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public List<?> getImages() {
        if (images == null)
            return new ArrayList<>();
        return images;
    }

    public void setImages(List<?> images) {
        this.images = images;
    }
}
