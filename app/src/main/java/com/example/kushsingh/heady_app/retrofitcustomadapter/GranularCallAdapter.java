package com.example.kushsingh.heady_app.retrofitcustomadapter;

import java.lang.reflect.Type;
import java.util.concurrent.Executor;

import retrofit2.Call;
import retrofit2.CallAdapter;


public class GranularCallAdapter<R> implements CallAdapter<R, NetworkCall<R>> {
    private final Type responseType;
    private final Executor callbackExecutor;

    public GranularCallAdapter(Type responseType, Executor callbackExecutor) {
        this.responseType = responseType;
        this.callbackExecutor = callbackExecutor;
    }

    @Override
    public Type responseType() {
        return responseType;
    }

    @Override
    public NetworkCall<R> adapt(Call<R> call) {
        return new NetworkCallAdapter<>(call, callbackExecutor);
    }
}
