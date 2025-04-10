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
import com.google.android.material.appbar.MaterialToolbar
import com.psg.Presentation.AllOption.Adpater.ItemAdapter
import com.psg.Presentation.AllOption.Item
import com.psg.Presentation.Emegency.EmergencyActivty
import com.psg.Presentation.Palour.Presentation.ContactMeScrren
import com.psg.Presentation.Util.SnackbarUtil
import com.psg.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var itemAdapter: ItemAdapter
    private val TAG = "ActivityLifecycle"
    private    val apiKey = "9b36de759e6d947adc9d0f7aba64bdad"


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


         InitView()
         Alloption()


    }

    private fun InitView() {

        binding.toolbar.toolbarDefault.setTitle("Main Screen")

        binding.toolbar.toolbarDefault.setOnMenuItemClickListener{ menuItem ->
            when (menuItem.itemId) {
                R.id.menu_Contact -> {
                    val intent = Intent(this@MainActivity , ContactMeScrren::class.java)
                    startActivity(intent)
                    true
                }
                R.id.menu_help -> {
                    SnackbarUtil.showShort(binding.root , "In Progress..")
                    true
                }
                else -> false
            }
        }
    }

    private fun Alloption() {
        recyclerView = binding.alloptionrecycerlview

        val items = listOf(
            Item( 1,"LifeCycle", "Description 1"),
            Item( 2,"Palour", "Description 2"),
            Item(3,"other", "Description 3")
        )

        itemAdapter = ItemAdapter(items) { item ->
            when (item.id) {
                1 -> openLifeCycleFunction()
                2 -> openPalour()
                3 -> openOther()
                else -> Toast.makeText(this, "Unknown Item Clicked", Toast.LENGTH_SHORT).show()
            }
        }

        recyclerView.adapter = itemAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)


    }




private fun MainActivity.openPalour() {
    val MaterialButton  = Intent(this@MainActivity , ContactMeScrren::class.java)
    startActivity(MaterialButton)
}

private fun MainActivity.openOther() {

   val EmergencyActivty = Intent(this@MainActivity , EmergencyActivty::class.java)
    startActivity(EmergencyActivty)

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