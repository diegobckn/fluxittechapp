package com.diegobckn.fluxit.api

import com.diegobckn.fluxit.domain.model.Pet
import retrofit2.http.*

interface WebService {
    @GET("pet/findByStatus?status=available")
    suspend fun getPets() : List<Pet>

    @GET("pet/{petId}")
    suspend fun getPetById(@Path("petId")id: Long) : Pet

}