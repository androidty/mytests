package com.oliver.mytests.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by android_1 on 2016/12/29.
 */

public class CustomRecyclerView extends RecyclerView {




    public CustomRecyclerView(Context context) {
        this(context,null);
        init();
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
        init();
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }


    private void init(){

    }
}
