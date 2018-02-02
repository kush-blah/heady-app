package com.example.kushsingh.heady_app

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.ViewGroup

interface ScreenContainer {

    val toolbar: Toolbar

    fun bind(activity: AppCompatActivity): ViewGroup

}