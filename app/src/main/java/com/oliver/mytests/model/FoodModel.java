package com.oliver.mytests.model;

import java.util.List;

/**
 * Created by Android on 2017/3/8.
 */

public class FoodModel {
    private boolean error;
    private List<FoodResult> foodResults;

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean isError() {
        return error;
    }

    public List<FoodResult> getFoodResults() {
        return foodResults;
    }

    public void setFoodResults(List<FoodResult> foodResults) {
        this.foodResults = foodResults;
    }

    public static class FoodResult {
        private String title;
        private String picUrl;
        private String material;
        private List<String> toUrls;

        public FoodResult(String title, String picUrl, String material, List<String> toUrls) {
            this.title = title;
            this.picUrl = picUrl;
            this.material = material;
            this.toUrls = toUrls;
        }

        public FoodResult(){

        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getMaterial() {
            return material;
        }

        public void setMaterial(String material) {
            this.material = material;
        }

        public List<String> getToUrls() {
            return toUrls;
        }

        public void setToUrls(List<String> toUrls) {
            this.toUrls = toUrls;
        }
    }

}
