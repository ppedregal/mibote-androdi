package com.thinknowa.botin.sdk;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Interceptor;
import com.thinknowa.botin.sdk.interceptors.LoggingInterceptor;
import com.thinknowa.botin.sdk.interceptors.OAuth2Interceptor;
import com.thinknowa.botin.sdk.model.AccessToken;
import com.thinknowa.botin.sdk.model.Account;
import com.thinknowa.botin.sdk.model.Amount;
import com.thinknowa.botin.sdk.model.Email;
import com.thinknowa.botin.sdk.model.Login;
import com.thinknowa.botin.sdk.model.Tin;
import com.thinknowa.botin.sdk.util.SdkCallAdapterFactory;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class Sdk {

    private Map<Class<?>,Object> api = new HashMap<Class<?>,Object>();
    private LoggingInterceptor loggingInterceptor = new LoggingInterceptor();
    private OAuth2Interceptor oauthInterceptor = new OAuth2Interceptor();
    private SdkCallAdapterFactory callAdapter = new SdkCallAdapterFactory();
    private Retrofit retrofit;

    public Sdk(String baseUri){
        GsonBuilder builder = new GsonBuilder();
        configure(builder);
        GsonConverterFactory factory = GsonConverterFactory.create(builder.create());
        retrofit = new Retrofit.Builder()
                .addConverterFactory(factory)
                .addCallAdapterFactory(callAdapter)
                .baseUrl(baseUri).build();
        List<Interceptor> interceptors = retrofit.client().interceptors();
        interceptors.add(oauthInterceptor);
        interceptors.add(loggingInterceptor);
        register(AccountApi.class);
        register(TinApi.class);
    }

    private Sdk register(Class type){
        api.put(type, retrofit.create(type));
        return this;
    }

    public Sdk logLevel(LoggingInterceptor.Level level){
        loggingInterceptor.setLevel(level);
        return this;
    }

    public LoggingInterceptor.Level logLevel(){
        return loggingInterceptor.getLevel();
    }

    public Sdk accessToken(AccessToken accessToken){
        oauthInterceptor.setAccessToken(accessToken);
        return this;
    }

    public AccessToken accessToken(){
        return oauthInterceptor.getAccessToken();
    }

    protected void configure(GsonBuilder builder){
        builder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    }

    /**
     * Retrieves registered apis
     * @return the accounts
     */
    public <T>T api(Class<T> type) {
        return (T)api.get(type);
    }

    /**
     * Registers a new user
     * @param password the password
     * @param email the email
     * @return the sdk for method chaining
     */
    public Sdk register(String email,String password){
        Account user = new Account();
        user.setEmail(email);
        user.setPassword(password);
        api(AccountApi.class).create(user);
        return this;
    }

    /**
     * Logs in the provided user
     * @param username the username
     * @param password the password
     * @return the sdk for method chaining
     */
    public Sdk login(String username,String password) {
        oauthInterceptor.setAccessToken(api(AccountApi.class).login(new Login(username, password)));
        return this;
    }

    /**
     * Retrieves current user
     * @return the retrieved user
     */
    public Account me(){
        return api(AccountApi.class).get();
    }

    /**
     * Resets password for the provided email
     * @param email
     * @return the sdk for method chaining
     */
    public Sdk reset(String email){
        api(AccountApi.class).reset(new Email(email));
        return this;
    }

    /**
     * Logs out current user
     * @return the sdk for method chaining
     */
    public Sdk logout(){
        api(AccountApi.class).logout();
        oauthInterceptor.setAccessToken(null);
        return this;
    }

    public List<Tin> tins(){
        return api(AccountApi.class).tins();
    }

    public Tin createTin(String name){
        Tin tin = new Tin();
        tin.setName(name);
        tin.setAmount(new Amount());
        return api(AccountApi.class).createTin(tin);
    }

}
