package com.example.kushsingh.heady_app

import android.app.ProgressDialog
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import com.example.kushsingh.heady_app.interaction.ToolBarImgInteractionProvider
import com.example.kushsingh.heady_app.screencontainers.ActionImgScreenContainer
import com.example.kushsingh.heady_app.screencontainers.NavigationDrawerScreenContainer
import com.example.kushsingh.heady_app.screencontainers.ScreenContainerActivity
import org.jetbrains.anko.find

class MainActivity : ScreenContainerActivity(), ToolBarImgInteractionProvider, NavigationView.OnNavigationItemSelectedListener {

    private lateinit var progressBar: ProgressDialog
    protected lateinit var interactionProvider: ActionImgScreenContainer
    private lateinit var navigationView: NavigationView
    private lateinit var container: FrameLayout
    private lateinit var drawer: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        progressBar = ProgressDialog(this)
        drawer = find(R.id.drawer_layout)
        container = find(R.id.fl_container)
        val toggle = ActionBarDrawerToggle(
                this, drawer, interactionProvider.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        navigationView = find(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        val drawer = find(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        return true
    }

    override fun getNavigationImage(): Int {
        return 0
    }

    override fun onActionBtnClick() {

    }

    override fun onSecondaryActionBtnClick() {
    }

    override fun getActionBarImage(): Int {
        return R.drawable.ic_search_black_24dp
    }

    override fun getSecondaryActionBarImage(): Int {
        return R.drawable.ic_filter_list_black_24dp
    }

    override fun getToolBarTitle(): String {
        return "Main Page"
    }

    override val layout: Int
        get() = R.layout.activity_main

    override fun createScreenContainer(): ScreenContainer? {
        interactionProvider = NavigationDrawerScreenContainer(this)
        return interactionProvider
    }

    override val progressView: View? = null

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        when (id) {
            R.id.nav_products -> {

            }
        }

        val drawer = find(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    private fun setDefaultFragment(){

    }
}
