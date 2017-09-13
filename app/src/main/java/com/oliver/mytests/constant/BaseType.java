package com.oliver.mytests.constant;

/**
 * Created by android_1 on 2016/12/27.
 */

public enum BaseType {
    app("app"),all("all"),fuli("福利");
    String value;

    private BaseType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
