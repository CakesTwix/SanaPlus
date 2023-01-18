package com.cakestwix.sanaplus

import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.cakestwix.sanaplus.API.SanaRequest
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity(), View.OnClickListener,
    OnSharedPreferenceChangeListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        findViewById<Button>(R.id.log_in).setOnClickListener(this)
        val sharedPref = getSharedPreferences("Account", MODE_PRIVATE)
        sharedPref?.registerOnSharedPreferenceChangeListener(this)

        // Default
        with(sharedPref.edit()) {
            putBoolean("status", false)
            apply()
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.log_in -> {
                val sharedPref = getSharedPreferences("Account", MODE_PRIVATE)

                val login = findViewById<TextInputLayout>(R.id.login_text).editText?.text.toString()
                val password = findViewById<TextInputLayout>(R.id.password_text).editText?.text.toString()
                if(login.isEmpty() or password.isEmpty()) { return }

                SanaRequest(login,password, sharedPref).tryLogin()
            }
            else -> {
            }
        }
    }


    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        when(key){
            "status" -> {
                if(sharedPreferences!!.getBoolean(key,false)){
                    startActivity(Intent(this, MainActivity::class.java))
                }
            }
        }
    }
}