package com.example.kushsingh.heady_app.screencontainers

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.ViewGroup
import com.example.kushsingh.heady_app.R
import com.example.kushsingh.heady_app.interaction.ToolBarImgInteractionProvider

class NavigationDrawerScreenContainer(interactionProvider: ToolBarImgInteractionProvider) : ActionImgScreenContainer(interactionProvider) {


    override lateinit var toolbar: Toolbar


    override fun bind(activity: AppCompatActivity): ViewGroup {
        activity.setContentView(R.layout.activity_main)
        val toolbarContainer = activity.findViewById(R.id.toolbar_container) as ViewGroup
        activity.layoutInflater.inflate(R.layout.toolbar_with_image_button, toolbarContainer, true)
        toolbar = activity.findViewById(R.id.toolbar_action) as Toolbar
        val container = activity.findViewById(R.id.fl_container) as ViewGroup
        initToolBar(activity)
        return container
    }
}