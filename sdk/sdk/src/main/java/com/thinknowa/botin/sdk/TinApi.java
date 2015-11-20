package com.thinknowa.botin.sdk;

import java.util.List;

import com.thinknowa.botin.sdk.model.Account;
import com.thinknowa.botin.sdk.model.Movement;
import com.thinknowa.botin.sdk.model.Tin;

import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by ppedregal on 18/11/15.
 */
public interface TinApi {

    @GET("tins/{id}")
    Tin get(@Path("id") String id);
    
    @POST("tins/{id}/disolve")
    Void disolve(@Path("id") String id);

    @POST("tins/{id}/invite")
    Void invite(@Path("id") String id,@Query("accountId")String accountId);
    
    @POST("tins/{id}/join")
    Void join(@Path("id") String id,@Query("accountId")String accountId);
    
    @POST("tins/{id}/leave")
    Void leave(@Path("id") String id,@Query("accountId")String accountId);
    
    @GET("tins/{id}/members")
    List<Account> getMembers(@Path("id") String id);
    
    @GET("tins/{id}/movements")
    List<Movement> getMovements(@Path("id")String id);
    
    @POST("tins/{id}/pay")
    Void pay(@Path("id")String id,@Query("accountId")String accountId,@Query("amount")Number amount,@Query("description")String description);
    
}
