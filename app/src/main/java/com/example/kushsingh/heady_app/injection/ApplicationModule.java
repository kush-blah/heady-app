package com.example.kushsingh.heady_app.injection;

import android.content.Context;

import dagger.Module;
import dagger.Provides;


@Module
public class ApplicationModule {

    private final Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    @Provides
    Context providesApplicationContext() {
        return context;
    }

}
