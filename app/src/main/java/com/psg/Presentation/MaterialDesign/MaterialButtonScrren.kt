package com.psg.Presentation.MaterialDesign

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.psg.Presentation.Util.PermissionHelper
import com.psg.Presentation.Util.SnackbarUtil
import com.psg.R
import com.psg.databinding.ActivityMaterialButtonScrrenBinding

class MaterialButtonScrren : AppCompatActivity() {

    private lateinit var binding: ActivityMaterialButtonScrrenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMaterialButtonScrrenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Initview()

        Button()
    }

    private fun Initview() {

        val permissions = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_CONTACTS
        )

        PermissionHelper.requestPermissions(this, permissions, 100)

    }

    private fun Button() {


        binding.btncustom.setOnClickListener {

            SnackbarUtil.showShort(binding.root ,"ON Click")
        }


    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        PermissionHelper.handlePermissionsResult(permissions, grantResults) { granted ->
            if (granted) {
                Toast.makeText(this, "All permissions granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Some permissions denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}