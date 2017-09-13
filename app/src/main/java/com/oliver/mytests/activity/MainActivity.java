package com.oliver.mytests.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.oliver.mytests.R;
import com.oliver.mytests.adapter.MyPagerAdapter;
import com.oliver.mytests.fragment.FourthFragment;
import com.oliver.mytests.fragment.HomeFragment;
import com.oliver.mytests.fragment.ClassifyFragment;
import com.oliver.mytests.fragment.ThirdFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //view
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    //data
    private String[] tabs = {"首页", "分类", "朋友圈", "个人"};
    private int[] tab_icons = {R.mipmap.home_normal,R.mipmap.classify_normal,R.mipmap.moments_normal,R.mipmap.user_normal};
    private int[] tab_press_icons = {R.mipmap.home_pressed,R.mipmap.classify_pressed,R.mipmap.moments_pressed,R.mipmap.user_pressed};
    private List<Fragment> fragments = new ArrayList<>();
    private HomeFragment homeFragment;
    private ClassifyFragment secondFragment;
    private ThirdFragment thirdFragment;
    private FourthFragment fourthFragment;
    private MyPagerAdapter myPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addFragments();
        initView();
        initData();

    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        toolbar.setTitle("首页");
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        viewPager = (ViewPager) findViewById(R.id.viewpager);

    }

    private void initData() {

        if (myPagerAdapter == null) {
            myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), fragments);
        }
        viewPager.setAdapter(myPagerAdapter);
        viewPager.setOffscreenPageLimit(4);
        viewPager.setAdapter(myPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        setTabIcons();
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                toolbar.setTitle(tabs[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d("onTabSelected", "onTabSelected:***   "+tab.getPosition());
                changePressd(tab,tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.d("onTabSelected", "onTabUnselected:+++ "+tab.getPosition());
                changeNormal(tab,tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    private void changeNormal(TabLayout.Tab tab,int pos) {

        View view = tab.getCustomView();
        ImageView imageView = (ImageView)view.findViewById(R.id.tab_icon);

        String str = toolbar.getTitle().toString();

        if(pos==0){
            imageView.setImageResource(tab_icons[0]);
        }else if(pos==1){
            imageView.setImageResource(tab_icons[1]);
        }else if(pos==2){
            imageView.setImageResource(tab_icons[2]);
        }else if(pos==3){
            imageView.setImageResource(tab_icons[3]);
        }


    }

    private void changePressd(TabLayout.Tab tab,int pos) {
        View view = tab.getCustomView();
        ImageView imageView = (ImageView)view.findViewById(R.id.tab_icon);
        if(pos==0){
            imageView.setImageResource(tab_press_icons[0]);
        }else if(pos==1){
            imageView.setImageResource(tab_press_icons[1]);
        }else if(pos==2){
            imageView.setImageResource(tab_press_icons[2]);
        }else if(pos==3){
            imageView.setImageResource(tab_press_icons[3]);
        }
    }


    private void setTabIcons() {
        tabLayout.getTabAt(0).setCustomView(getTabView(0));
        tabLayout.getTabAt(1).setCustomView(getTabView(1));
        tabLayout.getTabAt(2).setCustomView(getTabView(2));
        tabLayout.getTabAt(3).setCustomView(getTabView(3));
    }

    private View getTabView(int position) {
        View view = LayoutInflater.from(this).inflate(R.layout.tab_item, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.tab_icon);
        if (position == 0) {
            imageView.setImageResource(tab_press_icons[position]);
        } else {
            imageView.setImageResource(tab_icons[position]);
        }
        return view;
    }

    private void addFragments() {
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
            fragments.add(homeFragment);
        }
        if (secondFragment == null) {
            secondFragment = new ClassifyFragment();
            fragments.add(secondFragment);
        }
        if (thirdFragment == null) {
            thirdFragment = new ThirdFragment();
            fragments.add(thirdFragment);
        }
        if (fourthFragment == null) {
            fourthFragment = new FourthFragment();
            fragments.add(fourthFragment);
        }
    }
}
