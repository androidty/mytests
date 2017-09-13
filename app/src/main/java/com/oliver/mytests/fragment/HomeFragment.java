package com.oliver.mytests.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oliver.mytests.R;
import com.oliver.mytests.adapter.FourthAdapter;
import com.oliver.mytests.base.BaseFragment;
import com.oliver.mytests.constant.BaseService;
import com.oliver.mytests.constant.BaseType;
import com.oliver.mytests.constant.GankIoService;
import com.oliver.mytests.model.GankModel;
import com.oliver.mytests.view.LoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * Created by android_1 on 2016/12/27.
 */

public class HomeFragment extends BaseFragment {
    private View view;
    int i = 3;
    private LoadMoreRecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private FourthAdapter fourthAdapter;
    private int mCount = 1;
    private List<GankModel.ResultEntity> datas = new ArrayList<>();
    private Retrofit retrofit;
    private GankIoService gankIoService;
    private Subscription mySubscription;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fourth, null);
        retrofit = new Retrofit.Builder().baseUrl(BaseService.BASE_API)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        gankIoService = retrofit.create(GankIoService.class);
        initRecyclerView(view);
        initSwipeRefresh(view);
        loadData();
        return view;
    }

    private void loadData() {
        Observable<GankModel> observable = gankIoService.homeResult(BaseType.all.getValue(), mCount);
        Observable<GankModel> observableFuli = gankIoService.homeResult(BaseType.fuli.getValue(), mCount);
        if (mySubscription != null && mySubscription.isUnsubscribed()) {
            mySubscription.unsubscribe();
        }
        mySubscription = Observable.combineLatest(observable, observableFuli, new Func2<GankModel, GankModel, GankModel>() {
            @Override
            public GankModel call(GankModel gankIoModel, GankModel gankIoModel2) {
                List<GankModel.ResultEntity> resultsEntities = gankIoModel.getResults();
                int size = gankIoModel2.getResults().size();
                Log.d("onnext", "call: " + size);
                for (int i = 0; i < resultsEntities.size(); i++) {
                    if (!resultsEntities.get(i).getType().equals(BaseType.fuli.getValue()) && size > i) {
                        resultsEntities.get(i).setImageUrl(gankIoModel2.getResults().get(i).getUrl());
                    } else {
                        resultsEntities.get(i).setImageUrl(gankIoModel.getResults().get(i).getUrl());
                    }
                }
                return gankIoModel;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).filter(new Func1<GankModel, Boolean>() {
            @Override
            public Boolean call(GankModel gankIoModel) {
                return !gankIoModel.isError() && gankIoModel != null;
            }
        }).flatMap(new FilterMap()).subscribe(new LoadDataSubscriber());
        mCount++;
    }


    private void initRecyclerView(View view) {
        recyclerView = (LoadMoreRecyclerView) view.findViewById(R.id.myrecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        fourthAdapter = new FourthAdapter(getActivity(),datas);
        recyclerView.setAdapter(fourthAdapter);

        recyclerView.setAutoLoadMoreEnable(true);
        recyclerView.setLoadMoreListener(new LoadMoreRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                recyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        swipeRefreshLayout.setRefreshing(false);
//                        fourthAdapter.addDatas(DummyContent.generateData(++page));

                        loadData();
                        recyclerView.notifyMoreFinish(true);
                    }
                }, 1000);
            }
        });
        fourthAdapter.notifyDataSetChanged();
    }

    private void initSwipeRefresh(View view) {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefreshlayout);
        swipeRefreshLayout.setColorSchemeResources(R.color.refresh_color1, R.color.refresh_color2,
                R.color.refresh_color3, R.color.refresh_color4);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mCount = 1;
//                recyclerView.setAutoLoadMoreEnable(false);
//                recyclerView.setLoadingMore(true);
//                recyclerView.notifyMoreFinish(true);
                datas.clear();
                fourthAdapter.notifyDataSetChanged();
                loadData();
            }
        });
    }

    /**
     * 获得请求结果
     */
    class LoadDataSubscriber extends Subscriber<GankModel.ResultEntity> {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(GankModel.ResultEntity resultEntity) {
            Log.d("onnext", "onNext: " + resultEntity.getImageUrl());

//            datas.add(resultEntity);
//            mHomeAdapter.notifyAdapterItemInserted(datas.size());
            datas.add(resultEntity);
            fourthAdapter.notifyDataSetChanged();
        }
    }

    /**
     *
     */
    class FilterMap implements Func1<GankModel, Observable<GankModel.ResultEntity>> {
        @Override
        public Observable<GankModel.ResultEntity> call(GankModel gankIoModel) {
            Log.d("whathappened", "call: " + gankIoModel.getResults().size());
            if (gankIoModel.getResults().size() >= 10) {
//                recyclerView.set();
            } else {
//                recyclerView.disableLoadMore();
            }
            recyclerView.setLoadingMore(false);
            swipeRefreshLayout.setRefreshing(false);
            return Observable.from(gankIoModel.getResults());
        }
    }
}
