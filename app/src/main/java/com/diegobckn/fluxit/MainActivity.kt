package com.diegobckn.fluxit

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import java.util.*

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Objects.requireNonNull(getSupportActionBar())?.setDisplayShowHomeEnabled(true);
        getSupportActionBar()?.setIcon(R.mipmap.ic_launcher);
        getSupportActionBar()?.setTitle("");
    }
}