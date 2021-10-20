/*
 * Copyright (c) 2021 by Arsalan Shakil.
 * Wolt task project.
 *
 */

package com.example.wolttask.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wolttask.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_WoltTask)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}