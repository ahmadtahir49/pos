package com.example.pos

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val drugViewModel: DrugViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = DrugAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        drugViewModel.allDrugs.observe(this, { drugs ->
            drugs?.let { adapter.submitList(it) }
        })

        binding.fab.setOnClickListener {
            // start a new activity to add or edit a drug
        }
    }
}
