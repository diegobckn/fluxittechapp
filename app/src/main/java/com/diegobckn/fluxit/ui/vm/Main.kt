package com.diegobckn.fluxit.ui.vm

import androidx.lifecycle.*
import com.diegobckn.fluxit.api.Repo
import com.diegobckn.fluxit.api.Request
import kotlinx.coroutines.Dispatchers

class Main (private val repo: Repo): ViewModel() {

    private val petRefresh = MutableLiveData<Boolean>()
    private val petId = MutableLiveData<Long>()

    fun refresh(){
        petRefresh.value = petRefresh.value !=  true
    }

    fun setPetId(id: Long){
        petId.value = id
    }

    init{
        refresh()
    }

    val fetchPetList = petRefresh.distinctUntilChanged().switchMap { refresh->
        liveData(Dispatchers.IO){
            emit(Request.Loading())
            try {
                emit(repo.getPets())
            }
            catch (e:Exception){
                emit(Request.Failure(e))
            }
        }
    }

    val fetchPet = petId.distinctUntilChanged().switchMap { id->
        liveData(Dispatchers.IO){
            emit(Request.Loading())
            try {
                emit(repo.getPetById(id))
            }
            catch (e:Exception){
                emit(Request.Failure(e))
            }
        }
    }

}