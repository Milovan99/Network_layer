package com.milovanjakovljevic.networklayer.repository

import com.milovanjakovljevic.networklayer.network.ShoesApi
import com.milovanjakovljevic.networklayer.model.Result
import com.milovanjakovljevic.networklayer.model.ShoesNetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class ShoesRepository(private val shoesApi: ShoesApi) {

    suspend fun getShoes(): Flow<Result<List<ShoesNetworkResponse>>> = flow {
        emit(Result.loading())
        try {
            val shoes = shoesApi.getShoes()
            emit(Result.success(shoes))
        } catch (e: Exception) {
            emit(Result.error(e))
        }
    }.catch { e ->
        emit(Result.error(e))
    }.flowOn(Dispatchers.IO)
}