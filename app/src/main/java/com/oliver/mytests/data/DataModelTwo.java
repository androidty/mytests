package com.oliver.mytests.data;

/**
 * Created by android_1 on 2017/1/5.
 */

public class DataModelTwo {
    private String imgUrl;
    private String circleImgUrl;
    private String title;

    public DataModelTwo() {
    }

    public DataModelTwo(String imgUrl, String circleImgUrl, String title) {
        this.imgUrl = imgUrl;
        this.circleImgUrl = circleImgUrl;
        this.title = title;
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
}
