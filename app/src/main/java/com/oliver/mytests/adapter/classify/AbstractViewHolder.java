package com.oliver.mytests.adapter.classify;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;


/**
 * Created by android_1 on 2017/1/5.
 */

public abstract class AbstractViewHolder<T> extends RecyclerView.ViewHolder{


    public AbstractViewHolder(View itemView) {
        super(itemView);
    }

    public  abstract void bindHolder(T dataModel, Context context, ItemClick itemClick, int position);
}
