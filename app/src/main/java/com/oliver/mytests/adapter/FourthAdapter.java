package com.oliver.mytests.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.oliver.mytests.R;
import com.oliver.mytests.model.GankModel;

import java.util.List;

/**
 * Created by android_1 on 2016/12/29.
 */

public class FourthAdapter extends RecyclerView.Adapter<FourthAdapter.MyViewHolder>{

    private Context context;
    private List<GankModel.ResultEntity> datas ;


    public FourthAdapter(Context context, List<GankModel.ResultEntity> datas){
        this.context = context;
        this.datas = datas;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.test_home_item,null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
//        holder.textView.setText(datas.get(position).getCreatedAt());
        Glide.with(context).load(datas.get(position).getImageUrl()).asBitmap().centerCrop().into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        private ImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);
//            textView = (TextView) itemView.findViewById(R.id.text);
            imageView = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}
