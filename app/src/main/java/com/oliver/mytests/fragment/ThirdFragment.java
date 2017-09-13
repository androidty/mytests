package com.oliver.mytests.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oliver.mytests.R;
import com.oliver.mytests.base.BaseFragment;
import com.oliver.mytests.view.LoadMoreRecyclerView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by android_1 on 2016/12/27.
 */

public class ThirdFragment extends BaseFragment {
    private View view;
    private LoadMoreRecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_third,null);
//        initData();
//        initView(view);
        return view;
    }

    private void initView(View view){
        recyclerView = (LoadMoreRecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }



    private void initData(){
        //从一个URL加载一个Document对象。
        new Thread(new Runnable() {
            @Override
            public void run() {
                Document doc = null;
                try {
                    doc = Jsoup.connect("http://home.meishichina.com/show-top-type-recipe.html").get();

                    //选择“美食天下”所在节点
                    Elements elements = doc.select("div.top-bar");
                    //“椒麻鸡”和它对应的图片都在<div class="pic">中
                    Elements titleAndPic = doc.select("div.pic");
                    //使用Element.select(String selector)查找元素，使用Node.attr(String key)方法取得一个属性的值
                    for(int i =0;i<titleAndPic.size();i++) {
                        Log.i("mytag", "title:" + titleAndPic.get(i).select("a").attr("title") + "pic:" + titleAndPic.get(i).select("a").select("img").attr("data-src"));
                    }
                    //所需链接在<div class="detail">中的<a>标签里面
                    Elements url = doc.select("div.detail").select("a");
                    for(int i =0;i<url.size();i++) {
                        Log.i("mytag", "url:" + url.get(i).attr("href"));
                    }
                    //原料在<p class="subcontent">中
                    Elements burden = doc.select("p.subcontent");
                    //对于一个元素中的文本，可以使用Element.text()方法
                    Log.i("mytag", "burden:" + burden.get(1).text());
                    //打印 <a>标签里面的title
                    Log.i("mytag", elements.select("a").attr("title"));

                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}




