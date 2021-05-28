package com.example.appmovie.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appmovie.API_KEY
import com.example.appmovie.TOKEN
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import model.Movie
import model.MovieResponse
import com.example.appmovie.repository.MovieRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable

class MainViewModel(context: Context) : ViewModel() {

    private var repository: MovieRepository = MovieRepository(context)
    private val disposables = CompositeDisposable()

    fun getPlayNow() : MutableLiveData<List<Movie>> {

        val liveDataResponse = MutableLiveData<List<Movie>>()

        disposables.add(
            repository.getPlayNow(TOKEN, API_KEY, "en-US", 1).subscribeWith(object : DisposableSingleObserver<MovieResponse>(){
                override fun onSuccess(response: MovieResponse?) {
                    response?.let {
                        if(it.results.isNotEmpty()) {
                            liveDataResponse.postValue(it.results)
                        }
                    }
                }

                override fun onError(e: Throwable?) {
                    e?.printStackTrace()
                }

            })
        )
        return liveDataResponse
    }

}