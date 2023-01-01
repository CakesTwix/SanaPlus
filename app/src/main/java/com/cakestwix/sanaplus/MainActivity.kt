
package com.cakestwix.sanaplus

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import com.cakestwix.sanaplus.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationBarView
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // NavigationBar
        var bottonNavigationBarView = findViewById<NavigationBarView>(R.id.bottom_navigation)
        bottonNavigationBarView.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.menu_internet -> replaceFragment(InternetFragment())
                R.id.menu_tv -> replaceFragment(TvFragment())
                R.id.menu_etc -> replaceFragment(OtherFragment())
                else -> false
            }
        }

        val JSON: MediaType? = "application/json; charset=utf-8".toMediaTypeOrNull()

        // TODO: Remove this hardcode
        val JSONObjectString = "{\"apikey\":\"\",\"action\":\"get_userdata\",\"data\":[\"fin_section\", \"serv_section\", \"pers_section\", \"prov_contacts\"],\"sess_id\":\"\"}"
        var body:RequestBody = JSONObjectString.toRequestBody(JSON)


        var request = Request.Builder()
            .url("https://api.odessa.tv/userside/api.php")
            .post(body)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("CakesTwix-Debug", e.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                val jsonString = response.body!!.string()
                Log.d("CakesTwix-Debug", jsonString)

                // UI
                val Jobject = JSONObject(jsonString)
                runOnUiThread {
                    // FIO
                    findViewById<TextView>(R.id.tVfio).text =
                        Jobject.getJSONObject("data").getJSONObject("pers_section")
                            .getJSONObject("fio").getString("value")
                }
            }
        })

    }
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.switch1 -> {
                Log.d("CakesTwix", findViewById<Switch>(R.id.switch1).isChecked.toString())
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