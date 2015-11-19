package com.thinknowa.botin.sdk;

import com.thinknowa.botin.sdk.model.Tin;

import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by ppedregal on 18/11/15.
 */
public interface TinApi {

    @GET("tins/{id}")
    Tin get(@Path("id") String id);

}
