package com.sameer.internetchecker

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    lateinit var alertDialog: AlertDialog

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(isOnline(applicationContext)){
            Toast.makeText(applicationContext,"You are Connected",Toast.LENGTH_LONG).show()
        }else{
            showAlertDialog()
        }

    }


    protected fun showAlertDialog(){

        val factory = LayoutInflater.from(this)
        val internetDialogView: View = factory.inflate(R.layout.alert_dialog, null)
        alertDialog =
            AlertDialog.Builder(this).create()
        alertDialog?.setView(internetDialogView)
    //    deleteDialogView.tvDialogTitle.text = title
    //    deleteDialogView.tvDialogBody.text = message
        alertDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        internetDialogView.findViewById<Button>(R.id.checkInternet).setOnClickListener {
            alertDialog?.dismiss()
        }

        alertDialog?.show()
    }
//    else {
//
//        val factory = LayoutInflater.from(this)
//        val deleteDialogView: View = factory.inflate(R.layout.dialog_warning, null)
//        alertDialog =
//            AlertDialog.Builder(this).create()
//        alertDialog?.setView(deleteDialogView)
//        deleteDialogView.tvDialogTitle.text = title
//        deleteDialogView.tvDialogBody.text = message
//        alertDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        deleteDialogView.tvDialogOk.setOnClickListener {
//            alertDialog?.dismiss()
//        }
//
//        alertDialog?.show()
//    }



    @RequiresApi(Build.VERSION_CODES.M)
    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }
}