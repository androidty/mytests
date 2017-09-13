package com.oliver.mytests.adapter.classify;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.oliver.mytests.R;
import com.oliver.mytests.data.DataModel;
import com.oliver.mytests.data.DataModelFour;

/**
 * Created by android_1 on 2017/1/5.
 */

public class ViewHolderFour extends AbstractViewHolder<DataModelFour> {
    public int type = DataModel.TYPE_FOUR;
    public ImageView imageView;
    public TextView textView;
    public ViewHolderFour(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.img);
        textView = (TextView) itemView.findViewById(R.id.tv_title);
    }

    @Override
    public void bindHolder(DataModelFour dataModel, Context context, final ItemClick itemClick, final int position) {
        Glide.with(context).load(dataModel.getImgUrl()).asBitmap().centerCrop().into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClick.onItemClick(type,position);
            }
        });
    }

}
