package com.example.kushsingh.heady_app.data


import com.example.kushsingh.heady_app.BuildConfig
import com.example.kushsingh.heady_app.Utils.ApiConstants
import com.example.kushsingh.heady_app.data.local.LocalDataSource
import com.example.kushsingh.heady_app.data.network.DataSource
import com.example.kushsingh.heady_app.data.network.NetworkDataSource
import com.example.kushsingh.heady_app.data.network.RetroFitService
import com.example.kushsingh.heady_app.helper.ErrorWrapperHelper
import com.example.kushsingh.heady_app.injection.ApplicationScoped
import com.example.kushsingh.heady_app.retrofitcustomadapter.GranularErrorCallAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

@Module
class DataRespositoryModule {

    @Provides
    fun providesRetroFitService(): RetroFitService {
        val httpClient = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }

        httpClient.connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .addNetworkInterceptor(loggingInterceptor)
                .addInterceptor { chain ->
                    val original = chain.request()

                    val request = original.newBuilder()
                            .header("Content-Type", "application/json")
                            .header("AppVersion", BuildConfig.VERSION_CODE.toString() + "")
                            .method(original.method(), original.body())
                            .build()

                    chain.proceed(request)
                }
        val client = httpClient.build()
        // OkHttpClient client = getUnsafeOkHttpClient();
        Timber.d(TAG + " : providesRetroFitService: init")
        return Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL_API)
                .addCallAdapterFactory(GranularErrorCallAdapterFactory())
                .addConverterFactory(MoshiConverterFactory.create())
                .client(client)
                .build()
                .create(RetroFitService::class.java)

        //return retroFitService;
    }


    @Provides
    fun providesMoshi(): Moshi {
        return Moshi.Builder()
                .build()
    }

    @Provides
    @ApplicationScoped
    @Local
    fun providesLocalDataSource(): DataSource {
        return LocalDataSource()
    }


    @ApplicationScoped
    @Provides
    @Remote
    fun providesNetworkDataSource(retroFitService: RetroFitService, errorWrapperHelper: ErrorWrapperHelper): DataSource {
        return NetworkDataSource(retroFitService, errorWrapperHelper)
    }

    companion object {


        private const val TAG = "DataRepositoryModule"
    }


}
