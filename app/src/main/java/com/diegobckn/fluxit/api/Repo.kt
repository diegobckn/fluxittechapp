package com.diegobckn.fluxit.api

import com.diegobckn.fluxit.domain.model.Pet

interface Repo {
    suspend fun getPets(): Request<List<Pet>>

    suspend fun getPetById(id: Long): Request<Pet>

}