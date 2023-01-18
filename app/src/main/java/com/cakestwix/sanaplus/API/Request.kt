package com.cakestwix.sanaplus.API

import android.content.SharedPreferences
import android.util.Log
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

class SanaRequest (login: String, password: String, pref: SharedPreferences){
    private val client = OkHttpClient()
    private val api_url = "https://api.odessa.tv/userside/api.php"
    private val login = login
    private val password = password
    private val sharedPreferences = pref
    private val jsonObjectString = String.format(
        "{\"apikey\":\"%s\",\"action\":\"get_userdata\",\"data\":[\"fin_section\", \"serv_section\", \"pers_section\", \"prov_contacts\"],\"sess_id\":\"%s\"}",
        login,
        password
    )
    private val body: RequestBody =
        jsonObjectString.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())

    fun updateData() {
        var request = Request.Builder()
            .url(api_url)
            .post(body)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("CakesTwix-Request", e.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                val stringResponse = response.body!!.string()
                Log.d("CakesTwix-Request", stringResponse)
                if(stringResponse.isBlank()) { return }

                with(sharedPreferences.edit()) {
                    putString("json", stringResponse)
                    apply()
                }
            }
        })
    }

    fun tryLogin() {
        var request = Request.Builder()
            .url(api_url)
            .post(body)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("CakesTwix-Request", e.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                val stringResponse = response.body!!.string()
                if(stringResponse.isBlank() or stringResponse.contains("{'result': 'fail'")) { return }

                with(sharedPreferences.edit()) {
                    putBoolean("status", true)
                    putString("login", login)
                    putString("password", password)
                    apply()
                }
            }
        })
    }
}