package com.rpenates.imgurtestlab.ui.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
import com.rpenates.imgurtestlab.ui.dialogs.CustomProgressDialog
import info.androidhive.fontawesome.FontDrawable
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.content_home.*

class Home : AppCompatActivity() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var progressDialog: CustomProgressDialog
    private val selectedItems = ArrayList<Photo>()

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
        search_button.setImageDrawable(searchDrawable)

        search_button.setOnClickListener {
            if (search_src_text.text.isEmpty()) {
                Toast.makeText(this, resources.getString(R.string.empty_textfield), Toast.LENGTH_SHORT).show()
            } else {
                viewModel.searchPhotos(search_src_text.text.toString())
                progressDialog.show()

            }
        }

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_cart_mode) {
            renderInCartMode()
        }
        return true
    }

    private fun renderInCartMode() {
        photo_list.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        selectorListAdapter.photoList = viewModel.photosLiveData.value as ArrayList<Photo>
        photo_list.adapter = selectorListAdapter
        selectorListAdapter.notifyDataSetChanged()
    }


}
