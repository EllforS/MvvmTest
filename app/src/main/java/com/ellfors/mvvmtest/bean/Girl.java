package com.ellfors.mvvmtest.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Girl
 * 2020-05-06 16:46
 */
public class Girl {
    /**
     * _id : 5e958faf808d6d2fe6b56ecb
     * author : 鸢媛
     * category : Girl
     * createdAt : 2020-04-27 08:00:00
     * desc : 相似的人适合玩闹/互补的人才能终老。 ​​​​
     * images : ["http://gank.io/images/4817628a6762410895f814079a6690a1"]
     * likeCounts : 0
     * publishedAt : 2020-04-27 08:00:00
     * stars : 1
     * title : 第68期
     * type : Girl
     * url : http://gank.io/images/4817628a6762410895f814079a6690a1
     * views : 271
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
    private List<String> images;

    public Girl() {
    }

    public Girl(String title, String url) {
        this.title = title;
        this.url = url;
    }

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

    public List<String> getImages() {
        if (images == null)
            return new ArrayList<>();
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "Girl{" +
                "_id='" + _id + '\n' +
                ", author='" + author + '\n' +
                ", category='" + category + '\n' +
                ", createdAt='" + createdAt + '\n' +
                ", desc='" + desc + '\n' +
                ", likeCounts=" + likeCounts + '\n' +
                ", publishedAt='" + publishedAt + '\n' +
                ", stars=" + stars + '\n' +
                ", title='" + title + '\n' +
                ", type='" + type + '\n' +
                ", url='" + url + '\n' +
                ", views=" + views + '\n' +
                ", images=" + images + '}';
    }
}
