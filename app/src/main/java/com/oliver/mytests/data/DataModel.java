package com.oliver.mytests.data;

/**
 * Created by android_1 on 2017/1/5.
 */

public class DataModel {
    public static final int TYPE_ONE = 1;
    public static final int TYPE_TWO = 2;
    public static final int TYPE_THREE = 3;
    public static final int TYPE_FOUR = 4;


    private String imgUrl;
    private String circleImgUrl;
    private String title;
    private String contentTitle;

    public DataModel(String imgUrl, String circleImgUrl, String title, String contentTitle) {
        this.imgUrl = imgUrl;
        this.circleImgUrl = circleImgUrl;
        this.title = title;
        this.contentTitle = contentTitle;
    }

    public DataModel() {
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getCircleImgUrl() {
        return circleImgUrl;
    }

    public void setCircleImgUrl(String circleImgUrl) {
        this.circleImgUrl = circleImgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }
}
