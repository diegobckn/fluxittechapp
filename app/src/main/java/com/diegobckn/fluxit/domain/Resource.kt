package com.diegobckn.fluxit.domain

import com.diegobckn.fluxit.api.Request
import com.diegobckn.fluxit.api.RetrofitConnector
import com.diegobckn.fluxit.domain.model.Pet

class Resource {
    suspend fun getPets(): Request<List<Pet>>{
        return Request.Success(RetrofitConnector.webService.getPets())
    }

    suspend fun getPetById(id: Long): Request<Pet>{
        return Request.Success(RetrofitConnector.webService.getPetById(id))
    }

}