package com.psg

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.psg.Presentation.AllOption.Adpater.ItemAdapter
import com.psg.Presentation.AllOption.Item
import com.psg.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var itemAdapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = binding.alloptionrecycerlview

        val items = listOf(
            Item( 1,"LifeCycle", "Description 1"),
            Item( 2,"Material Button", "Description 2"),
            Item(3,"Material Text", "Description 3")
        )

        // Setting up the adapter and passing the click listener
        itemAdapter = ItemAdapter(items) { item ->

            Toast.makeText(this, "Clicked: ${item.title}", Toast.LENGTH_SHORT).show()
        }

        recyclerView.adapter = itemAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)




    }


}