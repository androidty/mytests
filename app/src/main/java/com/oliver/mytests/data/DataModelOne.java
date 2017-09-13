package com.oliver.mytests.data;

/**
 * Created by android_1 on 2017/1/5.
 */

public class DataModelOne {
    public int type = DataModel.TYPE_ONE;

    private String imgUrl;
    private String circleImgUrl;
    private String title;
    private String contentTitle;

    public DataModelOne() {
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
