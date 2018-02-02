package com.example.kushsingh.heady_app.screencontainers

import android.support.graphics.drawable.VectorDrawableCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.kushsingh.heady_app.R
import com.example.kushsingh.heady_app.ScreenContainer
import com.example.kushsingh.heady_app.interaction.ToolBarImgInteractionProvider

open class ActionImgScreenContainer(private val interactionProvider: ToolBarImgInteractionProvider) : ScreenContainer {

    override lateinit var toolbar: Toolbar

    override fun bind(activity: AppCompatActivity): ViewGroup {
        activity.setContentView(R.layout.base_layout)
        val toolbarContainer = activity.findViewById(R.id.toolbar_container) as ViewGroup
        activity.layoutInflater.inflate(R.layout.toolbar_with_image_button, toolbarContainer, true)
        toolbar = activity.findViewById(R.id.toolbar_action) as Toolbar
        val container = activity.findViewById(R.id.fl_container) as ViewGroup
        initToolBar(activity)
        return container
    }


    internal fun initToolBar(activity: AppCompatActivity) {
        activity.setSupportActionBar(toolbar)
        val actionBar = activity.supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setDisplayShowTitleEnabled(true)
            actionBar.setTitle(interactionProvider.getToolBarTitle())
            if (interactionProvider.getNavigationImage() !== 0) {
                toolbar.navigationIcon = VectorDrawableCompat.create(activity.resources, interactionProvider.getNavigationImage(), null)
            }
        }
        val actionView = activity.findViewById(R.id.iv_toolbar_action_one) as ImageView
        val secondaryActionView = activity.findViewById(R.id.iv_toolbar_action_two) as ImageView
        val parentLayoutView = activity.findViewById(R.id.ll_parent_toolbar_click_primary) as LinearLayout
        val secondaryLayoutView = activity.findViewById(R.id.ll_parent_toolbar_click_secondary) as LinearLayout

        if (interactionProvider.getActionBarImage() !== 0) {
            actionView.setImageDrawable(VectorDrawableCompat.create(activity.resources, interactionProvider.getActionBarImage(), null))
            parentLayoutView.setOnClickListener { interactionProvider.onActionBtnClick() }
        }
        if (interactionProvider.getSecondaryActionBarImage() !== 0) {
            secondaryActionView.setImageDrawable(VectorDrawableCompat.create(activity.resources, interactionProvider.getSecondaryActionBarImage(), null))
            secondaryLayoutView.visibility = View.VISIBLE
            secondaryLayoutView.setOnClickListener { interactionProvider.onSecondaryActionBtnClick() }
        }


    }

}