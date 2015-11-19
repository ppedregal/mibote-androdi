package com.thinknowa.botin.sdk.interceptors;

import java.io.IOException;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.thinknowa.botin.sdk.model.AccessToken;

/**
 * Created by ppedregal on 17/11/15.
 */
public class OAuth2Interceptor implements Interceptor {

    private AccessToken accessToken;

    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (accessToken!=null){
            request = request.newBuilder().url(request.httpUrl().newBuilder().setQueryParameter("access_token",accessToken.getId()).build()).build();
        }
        return chain.proceed(request);
    }

    public AccessToken getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(AccessToken accessToken) {
        this.accessToken = accessToken;
    }
}
