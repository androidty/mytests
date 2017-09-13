package com.oliver.mytests.data;

/**
 * Created by android_1 on 2017/1/5.
 */

public class DataModelFour {
    private String imgUrl;
    private String title;

    public DataModelFour() {
    }

    public DataModelFour(String imgUrl, String title) {
        this.imgUrl = imgUrl;
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
