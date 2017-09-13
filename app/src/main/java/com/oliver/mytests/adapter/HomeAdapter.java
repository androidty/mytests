package com.oliver.mytests.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.katelee.widget.RecyclerViewLayout;
import com.oliver.mytests.R;
import com.oliver.mytests.model.GankModel;

import java.util.List;

/**
 * Created by android_1 on 2016/12/27.
 */
public class HomeAdapter extends RecyclerViewLayout.Adapter<HomeAdapter.ItemHolder> {

    private Context mContext;
    private List<GankModel.ResultEntity> mData;

    public HomeAdapter(Context context, List<GankModel.ResultEntity> data) {

        this.mContext = context;
        this.mData = data;
        Log.d("HomeAdapter", "HomeAdapter: "+mData.size());
//        Log.d("HomeAdapter", "HomeAdapter: "+mData.get(20));
    }


    @Override
    protected ItemHolder onAdapterCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.test_home_item,null);
        ItemHolder itemHolder = new ItemHolder(view);
        return itemHolder;
    }

    @Override
    protected void onAdapterBindViewHolder(ItemHolder viewHolder, int position) {
        viewHolder.textView.setText(mData.get(position).getCreatedAt());
        Glide.with(mContext).load(mData.get(position).getImageUrl()).asBitmap().centerCrop().into(viewHolder.imageView);
//        viewHolder.imageView.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.bg));
//        Picasso.with(mContext).load(mData.get(position).getImageUrl()).into(viewHolder.imageView);
        Log.d("HomeAdapter", "onAdapterBindViewHolder: "+mData.get(position).getImageUrl());
    }

    @Override
    protected View onLoadMoreCreateView(ViewGroup viewGroup) {
        Log.d("onLoadMoreCreateView", "onLoadMoreCreateView: ");
        return LayoutInflater.from(mContext).inflate(R.layout.view_common_loadmore, viewGroup, false);
    }

    @Override
    public int getAdapterItemCount() {
        return mData.size();
    }

    @Override
    public boolean hasHeader() {
        return false;
    }

    static class ItemHolder extends RecyclerView.ViewHolder {
       TextView textView;
        ImageView imageView;

        public ItemHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text);
            imageView = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}
