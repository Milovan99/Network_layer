package com.milovanjakovljevic.networklayer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.milovanjakovljevic.networklayer.model.Result
import com.milovanjakovljevic.networklayer.model.ShoesNetworkResponse
import com.milovanjakovljevic.networklayer.repository.ShoesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ShoesViewModel(private val repository: ShoesRepository) : ViewModel() {

    private val _shoes = MutableStateFlow<Result<List<ShoesNetworkResponse>>>(Result.loading())
    val shoes: StateFlow<Result<List<ShoesNetworkResponse>>> = _shoes

    fun getShoes() {
        viewModelScope.launch {
            repository.getShoes()
                .collect { result ->
                    _shoes.value = result
                }
        }
    }
}