
package com.cakestwix.sanaplus

import SanaPlusModel
import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import android.os.Bundle
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


class MainActivity : AppCompatActivity(), View.OnClickListener, OnSharedPreferenceChangeListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        val sharedPref = getSharedPreferences("Account", MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString("test", "test")
            apply()
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Update Data
        // TODO: Delete hardcode, :(
        SanaRequest("","", sharedPref).updateData()

        // Switch Listener
        findViewById<MaterialSwitch>(R.id.switch1).setOnClickListener(this)

        // Pref Listener
        sharedPref.registerOnSharedPreferenceChangeListener(this)

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

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        when(key){
            "json" -> {
                val gsonJson = Gson().fromJson(sharedPreferences!!.getString(key,""), SanaPlusModel::class.java)
                findViewById<TextView>(R.id.tVfio).setText(gsonJson.data.pers_section.fio.value)
            }
        }
    }
}