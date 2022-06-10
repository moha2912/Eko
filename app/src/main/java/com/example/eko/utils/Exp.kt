package com.example.eko.utils

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

object Exp {


    @SuppressLint("SimpleDateFormat")
    fun convertLongToCalenderString(time: Long?): String {
        var stringTime = ""
        try {
            time?.let {
                val frm = SimpleDateFormat("E d MMM yyyy HH:mm", Locale.ENGLISH)
                val cal = Calendar.getInstance()
                cal.timeInMillis = time
                stringTime = frm.format(cal.time)
            }

        } catch (e: Exception) {
            Log.i("TAG", "convertLongToShowString: ")
        }
        return stringTime
    }
}