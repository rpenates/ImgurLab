package com.rpenates.imgurtestlab.ui.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.TextView
import com.rpenates.imgurtestlab.R

class CustomProgressDialog(context: Context): Dialog(context) {

    val dialogMessage = findViewById<TextView>(R.id.dialog_message)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.custom_progress_dialog)
    }

    fun setMessage(message: String) {
        if (message.isEmpty()) {
            dialogMessage.visibility = View.GONE
        } else {
            dialogMessage.text = message
        }
    }
}