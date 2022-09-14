package com.example.mymvvm.tools

import android.app.Activity
import android.content.Context
import android.content.Intent

infix fun <T> Boolean.then(param: T): T? = if (this) param else null
inline fun <reified Т : Activity> Context.startActivity() {
    val intent = Intent(this, Т::class.java)
    startActivity(intent)
}
