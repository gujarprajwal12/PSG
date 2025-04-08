package com.psg.Presentation.Palour.Presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.psg.R
import com.psg.databinding.ActivityContactMeScrrenBinding

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
    }

    private fun initview() {

        binding.toolbarDefault.toolbarDefault.setTitle("Contact Me")

        binding.toolbarDefault.toolbarDefault.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}