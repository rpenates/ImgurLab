package com.rpenates.imgurtestlab.ui.cart

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.rpenates.imgurtestlab.R
import com.rpenates.imgurtestlab.core.DI
import com.rpenates.imgurtestlab.data.models.Photo
import com.rpenates.imgurtestlab.ui.adapters.PhotoItemDeleteIAdapter
import kotlinx.android.synthetic.main.activity_cart.*

class CartActivity : AppCompatActivity() {

    private lateinit var viewModel: CartActivityViewModel
    private lateinit var listAdapter: PhotoItemDeleteIAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this,
            DI.provideCartActivityViewModelFactory(this))
            .get(CartActivityViewModel::class.java)
        listAdapter = PhotoItemDeleteIAdapter()
        listAdapter.setViewModel(viewModel)

        setContentView(R.layout.activity_cart)
        toolbar.apply {
            title = resources.getString(R.string.cart_title)
            setSupportActionBar(this)
            supportActionBar!!.apply {
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowHomeEnabled(true)
            }
        }

        viewModel.photoLiveData.observe (this, Observer {
            cart_list.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            listAdapter.photoList = it as ArrayList<Photo>
            cart_list.adapter = listAdapter
        })
        viewModel.showCartPhotos()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> {
                this.finish()
            }
            else -> println("No action found")
        }
        return true
    }
}
