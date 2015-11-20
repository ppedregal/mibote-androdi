package com.thinknowa.botin.sdk;

import java.util.List;
import java.util.Map;

import com.thinknowa.botin.sdk.model.AccessToken;
import com.thinknowa.botin.sdk.model.Account;
import com.thinknowa.botin.sdk.model.Email;
import com.thinknowa.botin.sdk.model.Login;
import com.thinknowa.botin.sdk.model.Tin;

import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by ppedregal on 17/11/15.
 */
public interface AccountApi {

    @POST("accounts/login")
    AccessToken login(@Body Login login);

    @POST("accounts/logout")
    Void logout();

    @GET("accounts/me")
    Account get();

    @GET("accounts/{id}")
    Account get(@Path("id") String userId);

    @POST("accounts")
    Account create(@Body Account user);

    @POST("accounts/reset")
    Void reset(@Body Email email);

    @GET("accounts/confirm")
    Void confirm(@Query("uid") String userId,@Query("token") String token,@Query("redirect") String redirect);

    @POST("accounts/{id}")
    Account save(@Path("id") String userId,@Body Account user);

    @PUT("accounts/{id}")
    Account update(@Path("id") String userId,@Body Map<String,Object> user);

    @DELETE("accounts/{id}")
    Void delete(@Path("id") String userId);

    @GET("accounts/me/tins")
    List<Tin> tins();

    @POST("accounts/me/tins")
    Tin createTin(@Body Tin tin);
}
