package com.milovanjakovljevic.networklayer.model

data class ShoesNetworkResponse(
    val id: Int,
    val name: String,
    val price: Double,
    val image: String,
    val description: String,
    val quantity: Int,
    val rating: Rating
) {
    data class Rating(
        val rate: Double,
        val count: Int
    )
}
