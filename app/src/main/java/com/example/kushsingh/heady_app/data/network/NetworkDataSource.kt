package com.example.kushsingh.heady_app.data.network

import com.example.kushsingh.heady_app.Utils.ApiConstants
import com.example.kushsingh.heady_app.helper.ErrorWrapperHelper
import com.example.kushsingh.heady_app.retrofitcustomadapter.GranularErrorCallAdapterFactory
import com.example.kushsingh.heady_app.retrofitcustomadapter.NetworkCall
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import java.security.cert.CertificateException
import java.util.*
import javax.inject.Inject
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

class NetworkDataSource @Inject constructor(retroFitService: RetroFitService, var errorWrapperHelper: ErrorWrapperHelper) : DataSource {
    private val TAG = "NetworkDataSource"
    private lateinit var retroFitService: RetroFitService
    private var imageReqCache: MutableMap<String, NetworkCall<Any>>? = null

    private fun getUnsafeOkHttpClient(): OkHttpClient {
        try {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                @Throws(CertificateException::class)
                override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                }

                @Throws(CertificateException::class)
                override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                }

                override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
                    return arrayOf()
                }
            })

            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, java.security.SecureRandom())
            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory = sslContext.socketFactory

            val builder = OkHttpClient.Builder()
            builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            builder.hostnameVerifier { hostname, session -> true }
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(loggingInterceptor)
            builder.addInterceptor { chain ->
                val original = chain.request()

                val request = original.newBuilder()
                        .header("Content-Type", "application/json")
                        .method(original.method(), original.body())
                        .build()

                chain.proceed(request)
            }
            return builder.build()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }

    init {
        val client = getUnsafeOkHttpClient()
        this.retroFitService = Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL_API)
                .addCallAdapterFactory(GranularErrorCallAdapterFactory())
                .addConverterFactory(MoshiConverterFactory.create())
                .client(client)
                .build()
                .create(RetroFitService::class.java)
        imageReqCache = HashMap()
        Timber.d(TAG, "NetworkDataSource: " + retroFitService)
    }
}