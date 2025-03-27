package com.psg

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.psg.Presentation.AllOption.Adpater.ItemAdapter
import com.psg.Presentation.AllOption.Item
import com.psg.Presentation.MaterialDesign.MaterialButtonScrren
import com.psg.Presentation.MaterialDesign.MaterialDesginTextScrren
import com.psg.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var itemAdapter: ItemAdapter
    private val TAG = "ActivityLifecycle"

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

        Log.d(TAG, "onCreate Called")


         Alloption()

    }

    private fun Alloption() {
        recyclerView = binding.alloptionrecycerlview

        val items = listOf(
            Item( 1,"LifeCycle", "Description 1"),
            Item( 2,"Material Button", "Description 2"),
            Item(3,"Material Text", "Description 3")
        )

        itemAdapter = ItemAdapter(items) { item ->
            when (item.id) {
                1 -> openLifeCycleFunction()
                2 -> openMaterialButtonFunction()
                3 -> openMaterialTextFunction()
                else -> Toast.makeText(this, "Unknown Item Clicked", Toast.LENGTH_SHORT).show()
            }
        }

        recyclerView.adapter = itemAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)


    }




private fun MainActivity.openMaterialTextFunction() {
  val MaterailText = Intent(this@MainActivity , MaterialDesginTextScrren::class.java)
    startActivity(MaterailText)
}

private fun MainActivity.openMaterialButtonFunction() {

    val MaterialButton  = Intent(this@MainActivity , MaterialButtonScrren::class.java)
    startActivity(MaterialButton)

}

private fun MainActivity.openLifeCycleFunction() {
    showLifecycleInfo()
}




override fun onStart() {
    super.onStart()
    Log.d(TAG, "onStart Called")
}

override fun onResume() {
    super.onResume()
    Log.d(TAG, "onResume Called")
}

override fun onPause() {
    super.onPause()
    Log.d(TAG, "onPause Called")
}

override fun onStop() {
    super.onStop()
    Log.d(TAG, "onStop Called")
}

override fun onDestroy() {
    super.onDestroy()
    Log.d(TAG, "onDestroy Called")
}

private fun showLifecycleInfo() {
    val lifecycleInfo = """
            onCreate -> Activity is created.
            onStart -> Activity becomes visible.
            onResume -> Activity is now in foreground.
            onPause -> Activity is partially visible.
            onStop -> Activity is not visible.
            onDestroy -> Activity is destroyed.
        """.trimIndent()

    Toast.makeText(this, lifecycleInfo, Toast.LENGTH_LONG).show()
}

}