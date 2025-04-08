package com.psg.Presentation.Emegency

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.psg.Presentation.Util.PermissionHelper
import com.psg.R
import com.psg.databinding.ActivityEmergencyActivtyBinding
import android.Manifest
import android.widget.Toast
import com.psg.Presentation.Util.PreferenceHelper


class EmergencyActivty : AppCompatActivity() {

  private  lateinit var binding: ActivityEmergencyActivtyBinding
    private val PERMISSION_REQUEST_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityEmergencyActivtyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initview()
        requestPermissions()
        Onclick()

    }


    private fun requestPermissions() {
        val permissions = arrayOf(
            Manifest.permission.RECEIVE_SMS,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_BACKGROUND_LOCATION,
            Manifest.permission.MODIFY_AUDIO_SETTINGS
        )

        PermissionHelper.requestPermissions(this, permissions, PERMISSION_REQUEST_CODE)
    }




    private fun initview() {


    }

    private fun Onclick() {
        val savedTrigger = PreferenceHelper.getTriggerWord(this)
        binding.triggerWordEditText.setText(savedTrigger)

        binding.saveButton.setOnClickListener {
            val triggerWord = binding.triggerWordEditText.text.toString()
            PreferenceHelper.setTriggerWord(this, triggerWord)
            Toast.makeText(this, "Trigger word saved", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PERMISSION_REQUEST_CODE) {
            PermissionHelper.handlePermissionsResult(permissions, grantResults) { granted ->
                if (!granted) {
                    Toast.makeText(this, "Some permissions were not granted!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}