package com.cakestwix.sanaplus.API

import SanaPlusModel
import android.content.SharedPreferences
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.cakestwix.sanaplus.R
import com.google.gson.Gson
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException

class SanaRequest (login: String, password: String, pref: SharedPreferences){
    private val client = OkHttpClient()
    private val api_url = "https://api.odessa.tv/userside/api.php"
    private val login = login
    private val password = password
    private val sharedPreferences = pref

    fun updateData() {
        val jsonObjectString = String.format(
            "{\"apikey\":\"%s\",\"action\":\"get_userdata\",\"data\":[\"fin_section\", \"serv_section\", \"pers_section\", \"prov_contacts\"],\"sess_id\":\"%s\"}",
            login,
            password
        )
        val body: RequestBody =
            jsonObjectString.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
        var jsonString = "No Request"

        var request = Request.Builder()
            .url(api_url)
            .post(body)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("CakesTwix-Request", e.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                with(sharedPreferences.edit()) {
                    putString("json", response.body!!.string())
                    apply()
                }
                Log.d("CakesTwix-Request", jsonString)
            }
        })
    }
}