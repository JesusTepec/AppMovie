package com.example.appmovie

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appmovie.adapters.MovieAdapter
import com.example.appmovie.databinding.ActivityMainBinding
import model.Movie
import com.example.appmovie.viewmodel.MainViewModel


class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MovieAdapter

    private var movieList = ArrayList<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = MainViewModel(this)

        initData()
        initRecyclerView()
    }

    private fun initData() {
        if (isConnected()) {
            Toast.makeText(this, "gola" , Toast.LENGTH_SHORT).show()
            viewModel.getPlayNow().observe(this, {
                Log.d("DEBUG", "hola")
                movieList.addAll(it)
                adapter.updateList(movieList)

            })
        }
    }

    private fun initRecyclerView() {
        binding.movieList.layoutManager = LinearLayoutManager(this)
        adapter = MovieAdapter(this, movieList)
        binding.movieList.adapter = adapter
    }
}