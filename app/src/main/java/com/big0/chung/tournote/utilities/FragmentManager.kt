package com.big0.chung.tournote.utilities

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction

//import android.app.FragmentManager
//import android.app.FragmentTransaction

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commit()
}
