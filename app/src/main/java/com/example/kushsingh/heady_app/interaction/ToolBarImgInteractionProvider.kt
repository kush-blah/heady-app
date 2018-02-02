package com.example.kushsingh.heady_app.interaction

import android.support.annotation.DrawableRes

interface ToolBarImgInteractionProvider {

    @DrawableRes
    fun getNavigationImage(): Int

    fun onActionBtnClick()

    fun onSecondaryActionBtnClick()

    @DrawableRes
    fun getActionBarImage(): Int

    @DrawableRes
    fun getSecondaryActionBarImage(): Int

    fun getToolBarTitle(): String

}