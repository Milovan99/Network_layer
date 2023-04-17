package com.milovanjakovljevic.networklayer

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.milovanjakovljevic.networklayer.network.NetworkModule.shoesApi
import com.milovanjakovljevic.networklayer.model.Result
import com.milovanjakovljevic.networklayer.viewmodel.ShoesViewModel
import com.milovanjakovljevic.networklayer.model.ShoesNetworkResponse
import com.milovanjakovljevic.networklayer.repository.ShoesRepository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ShoesActivity : AppCompatActivity() {

    private lateinit var viewModel: ShoesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = ShoesRepository(shoesApi)
        viewModel = ShoesViewModel(repository)

        viewModel.shoes.onEach { result ->
            when (result) {
                is Result.Loading -> showLoading()
                is Result.Success -> showShoes(result.data)
                is Result.Error -> showError(result.exception)
            }
        }.launchIn(lifecycleScope)

        viewModel.getShoes()
    }

    private fun showLoading() {
        Log.d("TAG", "Ucitavaa")
    }

    private fun showShoes(shoes: List<ShoesNetworkResponse>) {
        Log.d("TAG", "Received ${shoes.size} shoes:")
        for (shoe in shoes) {
            Log.d("TAG", "Name: ${shoe.name}, Price: ${shoe.price}")
        }
    }

    private fun showError(exception: Throwable) {
        Log.d("TAG", exception.toString())
    }
}