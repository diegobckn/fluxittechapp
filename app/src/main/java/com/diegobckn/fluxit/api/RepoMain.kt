package com.diegobckn.fluxit.api

import com.diegobckn.fluxit.domain.Resource
import com.diegobckn.fluxit.domain.model.Pet

class RepoMain(private val dataSource: Resource) : Repo {
    override suspend fun getPets(): Request<List<Pet>> {
        return dataSource.getPets()
    }

    override suspend fun getPetById(id: Long): Request<Pet>{
        return dataSource.getPetById(id)
    }

}