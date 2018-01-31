package com.example.kushsingh.heady_app.retrofitcustomadapter;

import android.support.annotation.Nullable;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.Executor;

import retrofit2.CallAdapter;
import retrofit2.Retrofit;

public class GranularErrorCallAdapterFactory extends CallAdapter.Factory {
    @Nullable
    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {

        if (getRawType(returnType) != NetworkCall.class) {
            return null;
        }
        if (!(returnType instanceof ParameterizedType)) {
            throw new IllegalStateException(
                    "NetworkCall must have generic type (e.g., NetworkCall<ResponseBody>)");
        }
        Type responseType = getParameterUpperBound(0, (ParameterizedType) returnType);
        Executor callbackExecutor = retrofit.callbackExecutor();
        return new GranularCallAdapter<>(responseType, callbackExecutor);
    }
}
