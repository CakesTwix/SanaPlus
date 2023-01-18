package com.cakestwix.sanaplus

import SanaPlusModel
import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.cakestwix.sanaplus.databinding.FragmentInternetBinding
import com.google.gson.Gson

class InternetFragment : Fragment(), OnSharedPreferenceChangeListener {
    lateinit var binding: FragmentInternetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInternetBinding.inflate(inflater, container, false)

        // Pref Listener
        val sharedPreferences = activity?.getSharedPreferences("Account", AppCompatActivity.MODE_PRIVATE)
        sharedPreferences?.registerOnSharedPreferenceChangeListener(this)

        // Update text
        updateUI(Gson().fromJson(sharedPreferences!!.getString("json",""), SanaPlusModel::class.java))

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

    fun updateUI(gsonJson: SanaPlusModel) {
        runOnUiThread {
            // Balance
            binding.tVBalance.text = gsonJson.data.fin_section.balance_curdate.value

            // Status
            binding.tVStatus.text = gsonJson.data.serv_section.inet.status.value

            // Speed
            binding.tVSpeed.text = gsonJson.data.serv_section.inet.packet.value

            // Date Stop
            binding.tVDateStop.text = gsonJson.data.fin_section.date_stop.value

            // Credit
            binding.tVCredit.text = gsonJson.data.fin_section.credit.value.toString()

            // SummaAll
            binding.tVSummaAll.text = gsonJson.data.serv_section.summa_all.toString()
        }
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        when(key){
            "json" -> {
                // UI
                updateUI(Gson().fromJson(sharedPreferences!!.getString(key,""), SanaPlusModel::class.java))

            }
        }
    }
}