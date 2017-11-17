package com.ammyt.examplesmasterclass.example_02

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ammyt.examplesmasterclass.example_01.Model_mago

class Activity_example : AppCompatActivity() {

    // Lo que metamos aquí dentro se compartirá entre las diferentes instancias de este objeto
    companion object {
        private var mageSelected: Model_mago? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
