package com.vicert.his.utils

import android.content.Context
import android.widget.Toast
import com.vicert.his.utils.Constant.EMPTY

fun Context.toast(message: String?) {
    Toast.makeText(this, message ?: EMPTY, Toast.LENGTH_LONG).show()
}
