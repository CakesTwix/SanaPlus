package com.cakestwix.sanaplus

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.cakestwix.sanaplus.databinding.FragmentInternetBinding
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException

class InternetFragment : Fragment() {
    lateinit var binding: FragmentInternetBinding
    private val client = OkHttpClient()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInternetBinding.inflate(inflater, container,false)
        val JSON: MediaType? = "application/json; charset=utf-8".toMediaTypeOrNull()

        // TODO: Remove this hardcode
        val JSONObjectString = "{\"apikey\":\"RtVHDG1#fGJF324\",\"action\":\"get_userdata\",\"data\":[\"fin_section\", \"serv_section\", \"pers_section\", \"prov_contacts\"],\"sess_id\":\"be2e4e99425aca3122a7b18f6649a466\"}"
        var body: RequestBody = JSONObjectString.toRequestBody(JSON)


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
                    // Balance
                    binding.tVBalance.text =
                        Jobject.getJSONObject("data").getJSONObject("fin_section")
                            .getJSONObject("balance_curdate").getString("value")

                    // Status
                    binding.tVStatus.text =
                        Jobject.getJSONObject("data").getJSONObject("serv_section")
                            .getJSONObject("inet").getJSONObject("status").getString("value")

                    // Speed
                    binding.tVSpeed.text =
                        Jobject.getJSONObject("data").getJSONObject("serv_section")
                            .getJSONObject("inet").getJSONObject("packet").getString("value")

                    // Date Stop
                    binding.tVDateStop.text =
                        Jobject.getJSONObject("data").getJSONObject("fin_section")
                            .getJSONObject("date_stop").getString("value")

                    // Credit
                    binding.tVCredit.text =
                        Jobject.getJSONObject("data").getJSONObject("fin_section")
                            .getJSONObject("credit").getString("value")
                }
            }
        })
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = InternetFragment()
    }

    fun Fragment?.runOnUiThread(action: () -> Unit) {
        this ?: return
        if (!isAdded) return // Fragment not attached to an Activity
        activity?.runOnUiThread(action)
    }
}