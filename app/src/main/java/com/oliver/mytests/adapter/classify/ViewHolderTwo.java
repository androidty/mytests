package com.oliver.mytests.adapter.classify;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.oliver.mytests.R;
import com.oliver.mytests.data.DataModel;
import com.oliver.mytests.data.DataModelTwo;
import com.oliver.mytests.view.CircleImageView;

/**
 * Created by android_1 on 2017/1/5.
 */

public class ViewHolderTwo extends AbstractViewHolder<DataModelTwo> {
    public int type = DataModel.TYPE_TWO;
    public ImageView imageView;
    public CircleImageView circleImageView;
    public TextView textView;
    public ViewHolderTwo(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.img);
        circleImageView = (CircleImageView) itemView.findViewById(R.id.circle_img);
        textView = (TextView) itemView.findViewById(R.id.tv_title);
    }

    @Override
    public void bindHolder(DataModelTwo dataModel, Context context, final ItemClick itemClick, final int position) {
        Glide.with(context).load(dataModel.getImgUrl()).asBitmap().centerCrop().into(imageView);
        Glide.with(context).load(dataModel.getImgUrl()).asBitmap().centerCrop().into(circleImageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClick.onItemClick(type,position);
            }
        });
    }
}
