package com.wzx.dynamiclaunchericon

import android.content.ComponentName
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.wzx.dynamiclaunchericon.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var name: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding:ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.classname = this.componentName.className
        Log.i("MainActivity", this.packageName)
    }
    
    fun onChangeIconDefault(view: View){
        name = "com.wzx.dynamiclaunchericon.MainActivity"
        changeLauncherIcon(this, ComponentName(this, name))
    }

    fun onChangeIcon1111(view: View){
        name = "com.wzx.dynamiclaunchericon.MainActivity1111"
        changeLauncherIcon(this, ComponentName(this, name))
    }

    fun onChangeIcon1212(view: View){
        name = "com.wzx.dynamiclaunchericon.MainActivity1212"
        changeLauncherIcon(this, ComponentName(this, name))
    }
}