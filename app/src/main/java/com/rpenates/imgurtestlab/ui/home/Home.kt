package com.rpenates.imgurtestlab.ui.home

import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.rpenates.imgurtestlab.R
import com.rpenates.imgurtestlab.core.DI
import com.rpenates.imgurtestlab.data.models.Photo
import com.rpenates.imgurtestlab.ui.adapters.PhotoItemAdapter

import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.content_home.*

class Home : AppCompatActivity() {

    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this,
            DI.provideHomeViewModelFactory(this))
            .get(HomeViewModel::class.java)

        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        var listAdapter = PhotoItemAdapter()
        photo_list.layoutManager = LinearLayoutManager(this)

        viewModel.photosLiveData.observe(this, Observer {
            listAdapter.photoList = it as ArrayList<Photo>
            photo_list.adapter = listAdapter
            listAdapter.notifyDataSetChanged()
            if (it.isEmpty()) {
                Toast.makeText(this, resources.getString(R.string.no_results),Toast.LENGTH_SHORT).show()
            }
        })

        search_button.setOnClickListener {
            if (search_src_text.text.isEmpty()) {
                Toast.makeText(this, resources.getString(R.string.empty_textfield), Toast.LENGTH_SHORT).show()
            } else {
                viewModel.searchPhotos(search_src_text.text.toString())
            }
        }

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

}
