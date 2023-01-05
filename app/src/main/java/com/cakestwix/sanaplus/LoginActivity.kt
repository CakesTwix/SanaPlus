package com.cakestwix.sanaplus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.textfield.TextInputEditText
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private val client = OkHttpClient()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val sharedPref = getSharedPreferences("Account", MODE_PRIVATE)
        findViewById<TextInputEditText>(R.id.login_text).setText(sharedPref.getString("test", "defaultValue"))
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.log_in -> {
                val login = findViewById<TextInputEditText>(R.id.login_text).text
                val password = findViewById<TextInputEditText>(R.id.password_text).text

                val jsonObjectString = String.format("{\"apikey\":\"%s\",\"action\":\"get_userdata\",\"data\":[\"fin_section\", \"serv_section\", \"pers_section\", \"prov_contacts\"],\"sess_id\":\"%s\"}", login, password)
                val body: RequestBody = jsonObjectString.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
            }
            else -> {
            }
        }
    }
}