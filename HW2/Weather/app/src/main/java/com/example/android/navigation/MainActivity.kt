/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.navigation

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.android.navigation.databinding.*

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    val adapter = WeatherAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("UNUSED_VARIABLE")
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val navController = this.findNavController(R.id.myNavHostFragment)
        drawerLayout=binding.drawerLayout
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(binding.navView, navController)

    }

    fun onCreate2(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        @Suppress("UNUSED_VARIABLE")
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val navController = this.findNavController(R.id.myNavHostFragment)
        drawerLayout=binding.drawerLayout
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(binding.navView, navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        val infoF: InfoFragment? =
            supportFragmentManager.findFragmentById(R.id.infoFragment) as InfoFragment?
        // Checks the orientation of the screen
        if (infoF != null) {
            if(infoF.isVisible) {
                if (newConfig.orientation === Configuration.ORIENTATION_LANDSCAPE) {
                    val binding = DataBindingUtil.setContentView<InfoBindingLandImpl>(this, R.layout.info)
                } else if (newConfig.orientation === Configuration.ORIENTATION_PORTRAIT) {
                    val binding = DataBindingUtil.setContentView<InfoBinding>(this, R.layout.info)
                }
            }
            else{
                if (newConfig.orientation === Configuration.ORIENTATION_LANDSCAPE) {
                    val binding = DataBindingUtil.setContentView<FragmentTitleBindingLandImpl>(this, R.layout.fragment_title)
                } else if (newConfig.orientation === Configuration.ORIENTATION_PORTRAIT) {
                    val binding = DataBindingUtil.setContentView<FragmentTitleBinding>(this, R.layout.fragment_title)
                }
            }
        }
    }

}
