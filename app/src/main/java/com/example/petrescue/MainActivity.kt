package com.example.petrescue

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import com.example.petrescue.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            setupGraph()
        }
    }

    private fun setupGraph(){
        val navController = findNavController(R.id.main_nav_host)
        val graph = navController.navInflater.inflate(R.navigation.pet_rescue_nav_graph)

        navController.graph = graph
    }
}