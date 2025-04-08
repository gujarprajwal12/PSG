package com.psg.Presentation.Palour.Presentation

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.psg.R
import com.psg.databinding.ActivityContactMeScrrenBinding
import androidx.core.net.toUri

class ContactMeScrren : AppCompatActivity() {

    private lateinit var binding: ActivityContactMeScrrenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityContactMeScrrenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initview()
        onClick()

    }


    private fun initview() {

        binding.toolbarDefault.toolbarDefault.setTitle("Contact Me")

        binding.toolbarDefault.toolbarDefault.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun isWhatsAppInstalled(): Boolean {
        val pm = packageManager
        return try {
            pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }



    private fun onClick() {

        binding.btndirection.setOnClickListener {
            openDirections()
        }
        // Instagram Button Click
        binding.instagramButton.setOnClickListener {
            openInstagram()
        }

        // Facebook Button Click
        binding.facebookButton.setOnClickListener {
            openFacebook()
        }

        // WhatsApp Button Click
        binding.whatsappButton.setOnClickListener {
            if (isWhatsAppInstalled()) {
                openWhatsApp()
            } else {
                Toast.makeText(this, "WhatsApp not installed", Toast.LENGTH_SHORT).show()
            }

        }

        // Call Button Click
        binding.CallButton.setOnClickListener {
            openCall()
        }


        binding.LoactionButton.setOnClickListener {
            openLocation()
        }
    }

    private fun openInstagram() {
        val username = "Super__Device"
        val uri = "http://instagram.com/_u/$username".toUri()
        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.setPackage("com.instagram.android")

        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            startActivity(Intent(Intent.ACTION_VIEW, "https://instagram.com/$username".toUri()))
        }
    }

    private fun openFacebook() {
        val userId = "gujarprajwal"
        val facebookIntent = Intent(Intent.ACTION_VIEW)

        try {
            // Try opening in Facebook app
            packageManager.getPackageInfo("com.facebook.katana", 0)
            facebookIntent.data = "fb://page/$userId".toUri()
        } catch (e: PackageManager.NameNotFoundException) {
            // Fallback to browser
            facebookIntent.data = "https://www.facebook.com/$userId".toUri()
        }

        startActivity(facebookIntent)
    }

    private fun openWhatsApp() {
        val phone = "+917040452312"
        val url = "https://wa.me/${phone.replace("+", "")}"
        val intent = Intent(Intent.ACTION_VIEW, url.toUri())
        startActivity(intent)
    }


    private fun openCall() {
        val phoneNumber = "tel:7040452312"
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = phoneNumber.toUri()
        startActivity(intent)
    }

    private fun openLocation() {
      val latitude = 19.3503
        val longitude = 75.2183
        val label = "Sahli Beauty Parlour"

        val uri = Uri.parse("geo:$latitude,$longitude?q=$latitude,$longitude($label)")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.setPackage("com.google.android.apps.maps")
        startActivity(intent)
    }

    private fun openDirections() {
        val destinationLat = 19.3560
        val destinationLng = 75.2132
        val gmmIntentUri = Uri.parse("google.navigation:q=$destinationLat,$destinationLng&mode=d")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")

        try {
            startActivity(mapIntent)
        } catch (e: ActivityNotFoundException) {
            val browserUri = Uri.parse("https://www.google.com/maps/dir/?api=1&destination=$destinationLat,$destinationLng")
            startActivity(Intent(Intent.ACTION_VIEW, browserUri))
        }
    }



}