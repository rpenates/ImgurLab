package com.rpenates.imgurtestlab.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.rpenates.imgurtestlab.R
import com.rpenates.imgurtestlab.core.DI
import com.rpenates.imgurtestlab.data.models.Photo
import com.rpenates.imgurtestlab.ui.adapters.PhotoItemAdapter
import com.rpenates.imgurtestlab.ui.adapters.PhotoItemSelectAdapter
import com.rpenates.imgurtestlab.ui.cart.CartActivity
import com.rpenates.imgurtestlab.ui.dialogs.CustomProgressDialog
import info.androidhive.fontawesome.FontDrawable
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.content_home.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.schedule


class Home : AppCompatActivity() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var progressDialog: CustomProgressDialog

    private var listAdapter = PhotoItemAdapter()
    private var selectorListAdapter = PhotoItemSelectAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        progressDialog = CustomProgressDialog(this)

        viewModel = ViewModelProviders.of(this,
            DI.provideHomeViewModelFactory(this))
            .get(HomeViewModel::class.java)

        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        action_fab.hide()

        viewModel.photosLiveData.observe(this, Observer {
            photo_list.layoutManager = LinearLayoutManager(this)
            listAdapter.photoList = it as ArrayList<Photo>
            photo_list.adapter = listAdapter
            listAdapter.notifyDataSetChanged()
            if (progressDialog.isShowing){
                progressDialog.dismiss()
            }
            if (it.isEmpty()) {
                Toast.makeText(this, resources.getString(R.string.no_results),Toast.LENGTH_SHORT).show()
            }
        })

        val searchDrawable = FontDrawable(this, R.string.fa_search_solid, true, false)
        searchDrawable.setTextColor(resources.getColor(R.color.colorAccent))
        searchDrawable.textSize = 22f
        search_button.setImageDrawable(searchDrawable)

        val plusDrawable = FontDrawable(this, R.string.fa_plus_solid, true, false)
        plusDrawable.setTextColor(resources.getColor(R.color.imgurWhite))
        action_fab.setImageDrawable(plusDrawable)

        search_src_text.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (search_src_text.text.isEmpty()) {
                    Toast.makeText(this, resources.getString(R.string.empty_textfield), Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.searchPhotos(search_src_text.text.toString())
                    progressDialog.show()
                    action_fab.hide()
                }
            }
            return@OnEditorActionListener false
        })

        search_button.setOnClickListener {
            if (search_src_text.text.isEmpty()) {
                Toast.makeText(this, resources.getString(R.string.empty_textfield), Toast.LENGTH_SHORT).show()
            } else {
                viewModel.searchPhotos(search_src_text.text.toString())
                progressDialog.show()
                action_fab.hide()
            }
        }

        action_fab.setOnClickListener { view ->
            if (viewModel.addToCart(selectorListAdapter.photoList)) {
                println("Items added to cart")

                Snackbar.make(view, resources.getString(R.string.home_cart_photos_added), Snackbar.LENGTH_LONG)
                    .setAction(resources.getString(R.string.show_message)) {
                        val cartIntent = Intent(this@Home,  CartActivity::class.java)
                        startActivity(cartIntent)
                    }.show()
                Timer("launchCart", false).schedule(500) {

                }
            } else {
                println("No items")
                Toast.makeText(this, resources.getString(R.string.cart_no_selection), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_cart_mode -> {
                renderInCartMode()
            }
            R.id.action_show_cart -> {
                val cartIntent = Intent(this@Home, CartActivity::class.java)
                startActivity(cartIntent)
            }
            else -> println("No action found")
        }
        return true
    }

    private fun renderInCartMode() {
        if (!viewModel.photosLiveData.value.isNullOrEmpty()) {
            selectorListAdapter.photoList = viewModel.photosLiveData.value as ArrayList<Photo>
            photo_list.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
            photo_list.adapter = selectorListAdapter
            selectorListAdapter.notifyDataSetChanged()
            action_fab.show()
        } else {
            Toast.makeText(this, resources.getString(R.string.no_results), Toast.LENGTH_SHORT).show()
        }
    }


}
