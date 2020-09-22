package com.example.testrecycleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testrecycleview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var rvListText: RecyclerView
    lateinit var viewModel: ListTextViewModel
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: ListTextAdapter
    val listTextObserver = Observer<List<TextItemView>> {
        adapter.submitList(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        adapter = ListTextAdapter() { position ->
            adapter.submitList(viewModel.selectItem(position))
        }
        binding.listTextRv.layoutManager = LinearLayoutManager(this)
        binding.listTextRv.adapter = adapter
        viewModel = ViewModelProvider(this).get(ListTextViewModel::class.java)
        viewModel.getListText().observe(this, listTextObserver)
    }
}