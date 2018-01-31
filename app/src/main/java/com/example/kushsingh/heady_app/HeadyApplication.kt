package com.example.kushsingh.heady_app

import android.app.Application
import android.content.Context
import com.example.kushsingh.heady_app.data.DaggerDataRepositoryComponent
import com.example.kushsingh.heady_app.data.DataRepositoryComponent
import com.example.kushsingh.heady_app.injection.ApplicationComponent
import com.example.kushsingh.heady_app.injection.ApplicationModule
import com.example.kushsingh.heady_app.injection.DaggerApplicationComponent
import dagger.internal.DaggerCollections

class HeadyApplication : Application() {


    lateinit var sContext: Context
    private val TAG = "HeadyApplication"
     private var component: ApplicationComponent? = null
     private var repositoryComponent: DataRepositoryComponent? = null

    override fun onCreate() {
        super.onCreate()
        sContext = this

        component = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()

        repositoryComponent = DaggerDataRepositoryComponent
                .builder()
                .applicationComponent(getApplicationComponent())
                .build()
    }

    fun getApplicationComponent(): ApplicationComponent? {
        return component
    }



    companion object {
        @JvmStatic
        fun contextProvider(): HeadyApplication {
            return HeadyApplication()
        }
    }

}

