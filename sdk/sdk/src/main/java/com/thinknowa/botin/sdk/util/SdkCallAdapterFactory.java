package com.thinknowa.botin.sdk.util;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import com.thinknowa.botin.sdk.exceptions.NetworkException;
import com.thinknowa.botin.sdk.exceptions.ResponseException;

import retrofit.Call;
import retrofit.CallAdapter;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by ppedregal on 18/11/15.
 */
public class SdkCallAdapterFactory implements CallAdapter.Factory {

	public CallAdapter<?> get(final Type returnType, Annotation[] annotations, Retrofit retrofit) {
        if (returnType.equals(Call.class)) return null;
        return new CallAdapter() {

            public Type responseType() {
                return returnType;
            }

            public Object adapt(Call call) {
                Response response = null;
                try {
                    response = call.execute();
                    if (response.isSuccess()){
                        return response.body();
                    } else {
                        // TODO parse errorBody with gson and provide error object
                        throw new ResponseException(response.code(),response.message(),response.errorBody().string());
                    }
                } catch (IOException e) {
                    throw new NetworkException(e);
                }
            }
        };
    }
}
