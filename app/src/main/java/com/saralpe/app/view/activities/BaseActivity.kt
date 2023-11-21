package com.saralpe.app.view.activities

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.Gravity
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textview.MaterialTextView
import com.saralpe.app.R
import com.shashank.sony.fancytoastlib.FancyToast
import java.text.SimpleDateFormat
import java.util.*


abstract class BaseActivity : AppCompatActivity() {

    init {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    companion object {
        var alert: AlertDialog? = null

        // THIS FUNCTION WILL CHECK THE INTERNET CONNECTION AVAILABLE OR NOT
        fun isNetworkAvailable(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val capabilities = connectivityManager.getNetworkCapabilities(
                    connectivityManager.activeNetwork
                )
                if (capabilities != null) {
                    when {
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                            return true
                        }
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                            return true
                        }
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                            return true
                        }
                    }
                }
            } else {
                val activeNetworkInfo = connectivityManager.activeNetworkInfo
                if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                    return true
                }
            }
            return false
        }

        fun getCurrentDigitalTime(): String {
            val c: Date = Calendar.getInstance().time
            val df = SimpleDateFormat("h:mm:ss a", Locale.getDefault())
            return df.format(c).uppercase(Locale.ENGLISH)
        }

        fun todayCurrentDate(): String {
            val c: Date = Calendar.getInstance().time
            val df = SimpleDateFormat("E, dd MMM yyyy", Locale.getDefault())
            return df.format(c).uppercase(Locale.ENGLISH)
        }

        @SuppressLint("HardwareIds")
        fun getAndroidDeviceId(context: Context): String {
            return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        }

        fun showAlert(context: Context, message: String) {
            val view = LayoutInflater.from(context).inflate(R.layout.custom_dialog, null)
            val builder = AlertDialog.Builder(context)
            builder.setView(view)
            builder.setCancelable(false)

            val textView = view.findViewById<MaterialTextView>(R.id.text_view)
            textView.text = message
            val btnOk = view.findViewById<MaterialButton>(R.id.ok_btn)
            val alert = builder.create()
            alert.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            alert.show()
            btnOk.setOnClickListener {
                alert.dismiss()
            }
        }

        fun defaultToast(context: Context,message: String){
            val toast =  FancyToast.makeText(context,message,FancyToast.LENGTH_SHORT,FancyToast.DEFAULT,false)
            toast.setGravity(Gravity.CENTER,0,0)
            toast.show()
        }

        fun successToast(context: Context,message: String){
            val toast =   FancyToast.makeText(context,message,FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false)
            toast.setGravity(Gravity.CENTER,0,0)
            toast.show()
        }

        fun infoToast(context: Context,message: String){
           val toast = FancyToast.makeText(context,message,FancyToast.LENGTH_SHORT,FancyToast.INFO,false)
            toast.setGravity(Gravity.CENTER,0,0)
            toast.show()
        }

        fun warningToast(context: Context,message: String){
            val toast = FancyToast.makeText(context,message,FancyToast.LENGTH_SHORT,FancyToast.WARNING,false)
            toast.setGravity(Gravity.CENTER,0,0)
            toast.show()
        }

        fun errorToast(context: Context,message: String){
           val toast = FancyToast.makeText(context,message,FancyToast.LENGTH_SHORT,FancyToast.ERROR,false)
            toast.setGravity(Gravity.CENTER,0,0)
            toast.show()

        }

        fun startLoading(context: Context) {
            val builder = MaterialAlertDialogBuilder(context)
            val layout = LayoutInflater.from(context).inflate(R.layout.custom_loading, null)
            builder.setView(layout)
            builder.setCancelable(false)
            alert = builder.create()
            alert!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            alert!!.show()
        }

        fun dismiss() {
            if (alert != null) {
                alert!!.dismiss()
            }
        }
    }

}