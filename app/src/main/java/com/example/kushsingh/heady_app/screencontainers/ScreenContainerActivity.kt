package com.example.kushsingh.heady_app.screencontainers

import android.os.Bundle
import android.view.MenuItem
import com.example.kushsingh.heady_app.ScreenContainer
import com.example.kushsingh.heady_app.base.BaseActivity

abstract class ScreenContainerActivity : BaseActivity() {

    protected abstract val layout: Int


    protected abstract fun createScreenContainer(): ScreenContainer?


    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val screenContainer = createScreenContainer()
        if (screenContainer != null) {
            val container = screenContainer.bind(this)
            layoutInflater.inflate(layout, container)
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return true
    }

}
