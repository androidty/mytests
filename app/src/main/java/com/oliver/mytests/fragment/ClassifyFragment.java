package com.oliver.mytests.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.oliver.mytests.R;
import com.oliver.mytests.adapter.classify.ClassifyAdapter;
import com.oliver.mytests.adapter.classify.ItemClick;
import com.oliver.mytests.base.BaseFragment;
import com.oliver.mytests.constant.BaseService;
import com.oliver.mytests.constant.BaseType;
import com.oliver.mytests.constant.GankIoService;
import com.oliver.mytests.data.DataModel;
import com.oliver.mytests.data.DataModelFour;
import com.oliver.mytests.data.DataModelOne;
import com.oliver.mytests.data.DataModelTwo;
import com.oliver.mytests.model.GankModel;
import com.oliver.mytests.view.LoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by android_1 on 2016/12/27.
 */

public class ClassifyFragment extends BaseFragment implements ItemClick {
    private View view;
    private LoadMoreRecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    private GridLayoutManager gridLayoutManager;

    private List<DataModelOne> list1;
    private List<DataModelTwo> list2;
    private List<DataModelFour> list4;
    private ClassifyAdapter classifyAdapter = new ClassifyAdapter();


    private Subscription loadDataSubscription;
    private Retrofit retrofit;
    private GankIoService service;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        retrofit = new Retrofit.Builder().baseUrl(BaseService.BASE_API).addConverterFactory(GsonConverterFactory.create()).
                addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        service = retrofit.create(GankIoService.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("oncreateview", "onCreateView: ");
        view = inflater.inflate(R.layout.fragment_second, null);

        loadData();
        initView(view);
        initSwipeRefresh(view);
        return view;
    }

    private void initView(View view) {
        recyclerView = (LoadMoreRecyclerView) view.findViewById(R.id.recyclerview);
        gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int type = recyclerView.getAdapter().getItemViewType(position);
                if (type == DataModel.TYPE_ONE) {
                    return gridLayoutManager.getSpanCount();
                } else if (type == DataModel.TYPE_TWO) {
                    return 1;
                } else if (type == DataModel.TYPE_THREE) {
                    return 2;//获取的是gridLayoutManager的列数
                } else if (type == DataModel.TYPE_FOUR) {
                    return 1;
                }
                return 1;
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                GridLayoutManager.LayoutParams layoutManager = (GridLayoutManager.LayoutParams) view.getLayoutParams();
                int spanSize = layoutManager.getSpanSize();
                int spanIndex = layoutManager.getSpanIndex();//代表格子的角标 例如：2列，0代表左边，1代表右侧
                outRect.top = 30;
                if (spanSize != gridLayoutManager.getSpanCount()) {//说明这一行不止一个格子
                    if (spanIndex == 0) {//说明是左侧的格子
                        outRect.right = 20;
                        outRect.left = 30;
                    } else {
                        outRect.left = 20;
                        outRect.right = 30;
                    }
                } else {
                    outRect.top = 30;
                    outRect.left = 30;
                    outRect.right = 30;
//                    outRect.bottom =30;
                }
            }
        });


    }


    private void initSwipeRefresh(View view){
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefreshlayout);
        swipeRefreshLayout.setColorSchemeResources(R.color.refresh_color1, R.color.refresh_color2,
                R.color.refresh_color3, R.color.refresh_color4);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                mCount = 1;
//                recyclerView.setAutoLoadMoreEnable(false);
//                recyclerView.setLoadingMore(true);
//                recyclerView.notifyMoreFinish(true);
//                datas.clear();
//                fourthAdapter.notifyDataSetChanged();
                loadData();
            }
        });
    }

    private void loadData() {
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        list4 = new ArrayList<>();

        final int count = new Random().nextInt(41);
        final Observable<GankModel> observable = service.randomResult();
        loadDataSubscription = observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).filter(new Func1<GankModel, Boolean>() {
            @Override
            public Boolean call(GankModel gankModel) {
                return !gankModel.isError();
            }
        }).subscribe(new Subscriber<GankModel>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(getActivity(), "error:"+e, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(GankModel gankModel) {

               List< GankModel.ResultEntity> res = gankModel.getResults();
                for(int i =0;i<res.size();i++){
                    DataModelOne data = new DataModelOne();
                    data.setImgUrl(res.get(i).getUrl());
                    list1.add(data);
                }
//                    classifyAdapter.notifyDataSetChanged();
//                    for (int i = 0; i < 10; i++) {
//                        list2.add(new DataModelTwo());
//                    }
                Observable<GankModel> observable2 = service.homeResult(BaseType.fuli.getValue(),count);
                observable2.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).filter(new Func1<GankModel, Boolean>() {
                    @Override
                    public Boolean call(GankModel gankModel) {
                        return !gankModel.isError();
                    }
                }).subscribe(new Subscriber<GankModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GankModel gankModel) {
                        List< GankModel.ResultEntity> res = gankModel.getResults();
                        for(int i =0;i<res.size();i++){
                            DataModelTwo data = new DataModelTwo();
                            data.setImgUrl(res.get(i).getUrl());
                            list2.add(data);
//                            classifyAdapter = new ClassifyAdapter(getActivity(), list1, list2, list4);
//                            recyclerView.setAdapter(classifyAdapter);
                        }
                        Observable<GankModel> observable2 = service.homeResult(BaseType.fuli.getValue(),count+1);
                        observable2.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).filter(new Func1<GankModel, Boolean>() {
                            @Override
                            public Boolean call(GankModel gankModel) {
                                return !gankModel.isError();
                            }
                        }).subscribe(new Subscriber<GankModel>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(GankModel gankModel) {
                                List< GankModel.ResultEntity> res = gankModel.getResults();
                                for(int i =0;i<res.size();i++){
                                    DataModelFour data = new DataModelFour();
                                    data.setImgUrl(res.get(i).getUrl());
                                    list4.add(data);
                                    classifyAdapter = new ClassifyAdapter(getActivity(), list1, list2, list4);
                                    recyclerView.setAdapter(classifyAdapter);
                                    swipeRefreshLayout.setRefreshing(false);
                                    classifyAdapter.setItemClick(new ItemClick() {
                                        @Override
                                        public void onItemClick(int type, int position) {
                                            Toast.makeText(getActivity(), "type: "+type+"\npos: "+position, Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }

                            }
                        });

                    }
                });

            }
        });


//        for (int i = 0; i < 1; i++) {
//            DataModelOne data = new DataModelOne();
//            data.setCircleImgUrl("1");
//            data.setContentTitle("1");
//            data.setImgUrl("1");
//            data.setTitle("1");
//            list1.add(data);
//        }


    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onItemClick(int type, int position) {
        Toast.makeText(getActivity(), "type: "+type+"\npos: "+position, Toast.LENGTH_SHORT).show();
    }
}
