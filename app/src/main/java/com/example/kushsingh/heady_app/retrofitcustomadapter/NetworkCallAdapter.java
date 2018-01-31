package com.example.kushsingh.heady_app.retrofitcustomadapter;

import java.io.IOException;
import java.util.concurrent.Executor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NetworkCallAdapter<T> implements NetworkCall<T> {
    private static final String TAG = "NetworkCallAdapter";
    private final Call<T> call;
    private final Executor callbackExecutor;

    public NetworkCallAdapter(Call<T> call, Executor callbackExecutor) {
        this.call = call;
        this.callbackExecutor = callbackExecutor;
    }


    @Override
    public void cancel() {
        call.cancel();
    }

    @Override
    public void enqueue(final NetworkCall.NetworkCallBack<T> callback) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(final Call<T> call, final Response<T> response) {
                callbackExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        int code = response.code();
                        if (code >= 200 && code < 300) {
                            callback.success(response);
                        } else if (code == 401) {
                            callback.unauthenticated(response);
                        } else if (code >= 400 && code < 500) {
                            callback.clientError(response);
                        } else if (code >= 500 && code < 600) {
                            callback.serverError(response);
                        } else {
                            if (call.isCanceled())
                                callback.unexpectedError(new InterruptedException("request was cancel"));
                            else {
                                callback.unexpectedError(new RuntimeException("Unexpected response " + response));
                            }
                        }
                    }
                });
            }

            @Override
            public void onFailure(final Call<T> call, final Throwable t) {
                callbackExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        if (t instanceof IOException) {
                            callback.networkError((IOException) t);
                        } else {
                            callback.unexpectedError(t);
                        }
                    }
                });
            }
        });
    }

    @Override
    public NetworkCall<T> clone() {
        return new NetworkCallAdapter<>(call.clone(), callbackExecutor);
    }


}
