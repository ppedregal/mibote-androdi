package com.thinknowa.botin.sdk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.GsonBuilder;
import com.pubnub.api.Callback;
import com.pubnub.api.Pubnub;
import com.pubnub.api.PubnubException;
import com.squareup.okhttp.Interceptor;
import com.thinknowa.botin.sdk.exceptions.SdkException;
import com.thinknowa.botin.sdk.interceptors.LoggingInterceptor;
import com.thinknowa.botin.sdk.interceptors.OAuth2Interceptor;
import com.thinknowa.botin.sdk.model.AccessToken;
import com.thinknowa.botin.sdk.model.Account;
import com.thinknowa.botin.sdk.model.Amount;
import com.thinknowa.botin.sdk.model.Email;
import com.thinknowa.botin.sdk.model.Login;
import com.thinknowa.botin.sdk.model.Movement;
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
    private Pubnub pubnub;

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
        pubnub = new Pubnub("sub-c-7ee3bb6c-8ee1-11e5-8f62-0619f8945a4f", "pub-c-08510f32-29e1-4fed-81ac-6a1bb40746f2");                
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
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
    @SuppressWarnings("unchecked")
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
        try {
			pubnub.subscribe(new String[]{"account."+oauthInterceptor.getAccessToken().getUserId()},new Callback(){
				@Override
				public void successCallback(String channel, Object message) {
					for (MessageListener listener:messageListeners){
						listener.onMessage(message);
					}
				}
			});
		} catch (PubnubException e) {
			throw new SdkException(e);
		}
        return this;
    }
    
    private List<MessageListener> messageListeners = new ArrayList<MessageListener>();
    
    public Sdk addMessageListener(MessageListener<?> listener){
    	messageListeners.add(listener);
    	return this;
    }
    
    public Sdk removeMessageListener(MessageListener<?> listener){
    	messageListeners.remove(listener);
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
    	pubnub.unsubscribe("account."+oauthInterceptor.getAccessToken().getUserId());
        oauthInterceptor.setAccessToken(null);
        return this;
    }

    public List<Tin> tins(){
        return api(AccountApi.class).tins();
    }

    public Tin createTin(Tin tin){
        return api(AccountApi.class).createTin(tin);
    }
    
    public Tin getTin(String id){
    	return api(TinApi.class).get(id);
    }
    
    public Sdk disolveTin(String id){
    	api(TinApi.class).disolve(id);
    	return this;
    }
    
    public Sdk inviteTin(String id,String accountId){
    	api(TinApi.class).invite(id,accountId);
    	return this;
    }
    
    public Sdk joinTin(String id){
    	return joinTin(id,"me");
    }
    
    public Sdk joinTin(String id,String accountId){
    	api(TinApi.class).join(id,accountId);
    	return this;
    }
    
    public Sdk leaveTin(String id){
    	return leaveTin(id,"me");
    }
    
    public Sdk leaveTin(String id,String accountId){
    	api(TinApi.class).leave(id,accountId);
    	return this;
    }
    
    public List<Account> getTinMembers(String id){
    	return api(TinApi.class).getMembers(id);
    }
    
    public List<Movement> getTinMovements(String id){
    	return api(TinApi.class).getMovements(id);
    }
    
    public Sdk payTin(String id,String accountId,Amount amount,String description){
    	api(TinApi.class).pay(id, accountId, amount.getValue(), description);
    	return this;
    }
        
}
