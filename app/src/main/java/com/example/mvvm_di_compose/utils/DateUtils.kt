package com.example.mvvm_di_compose.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.lang.Exception
import java.text.SimpleDateFormat
//2021-12-29T13:46:14Z
@BindingAdapter("formatedDate")
fun formatedDate(view: TextView, text: String?) {
    if (!text.isNullOrEmpty()) {
        try {
            view.setText(DateUtils.mmddyyyy.format(DateUtils.serverDateFormat.parse(text)))
        }catch (ignore :Exception){}
    }
}
class DateUtils {
    companion object{
        var serverDateFormat : SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX")
        var mmddyyyy : SimpleDateFormat = SimpleDateFormat("mm-dd-yyyy")
    }


}