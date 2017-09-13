package com.oliver.mytests.test;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oliver.mytests.R;

import java.util.List;

/**
 * Created by android_1 on 2016/12/27.
 */

public class TestHomeAdapter extends RecyclerView.Adapter<TestHomeAdapter.MyHolder>{
    private Context context;
    private List<String> datas;
    public TestHomeAdapter(Context context,List<String> datas){
        this.context = context;
        this.datas = datas;
        Log.d("TestHomeAdapter", "TestHomeAdapter: "+datas.size());
    }
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.test_home_item,null);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.textView.setText(datas.get(position));
        Log.d("TestHomeAdapter", "onBindViewHolder: "+position);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    static class MyHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public MyHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text);
        }
    }
}
