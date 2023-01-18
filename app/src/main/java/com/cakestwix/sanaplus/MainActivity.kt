
package com.cakestwix.sanaplus

import SanaPlusModel
import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import com.cakestwix.sanaplus.API.SanaRequest
import com.cakestwix.sanaplus.databinding.ActivityMainBinding
import com.google.android.material.materialswitch.MaterialSwitch
import com.google.android.material.navigation.NavigationBarView
import com.google.gson.Gson


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        val sharedPref = getSharedPreferences("Account", MODE_PRIVATE)

        // Check is have login
        if (sharedPref.getBoolean("status", false)){
            // Yes login ^_^
            // Update Data
            SanaRequest(sharedPref.getString("login", "")!!,sharedPref.getString("password", "")!!, sharedPref).updateData()
        }
        else {
            // No login, then go to LoginActivity
            startActivity(Intent(this, LoginActivity::class.java))
            return
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Main card
        val gsonJson = Gson().fromJson(sharedPref!!.getString("json",null), SanaPlusModel::class.java)
        findViewById<TextView>(R.id.tVfio).text = gsonJson.data.pers_section.fio.value

        // Switch Listener
        findViewById<MaterialSwitch>(R.id.switch1).setOnClickListener(this)

        // NavigationBar
        var bottonNavigationBarView = findViewById<NavigationBarView>(R.id.bottom_navigation)
        bottonNavigationBarView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_internet -> replaceFragment(InternetFragment())
                R.id.menu_tv -> replaceFragment(TvFragment())
                R.id.menu_etc -> replaceFragment(OtherFragment())
                else -> false
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.switch1 -> {
                startActivity(Intent(this, LoginActivity::class.java))
            }
            else -> {
            }
        }
    }

    private fun replaceFragment(fragment : Fragment): Boolean {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment,fragment)
            .commit()
        return true
    }
}