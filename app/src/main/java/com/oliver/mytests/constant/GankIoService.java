package com.oliver.mytests.constant;

import com.oliver.mytests.model.FoodModel;
import com.oliver.mytests.model.GankModel;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by android_1 on 2016/12/27.
 */

public interface GankIoService {
    //动态
    @GET("data/{type}/10/{count}")
    Observable<GankModel> homeResult(@Path("type") String type, @Path("count") int count);

    @GET("random/data/福利/1")
    Observable<GankModel> randomResult();

    @GET("http://home.meishichina.com/show-top-type-recipe.html")
    Observable<FoodModel> footResult();


}
