package com.diegobckn.fluxit.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Pet (
    val id: Long = 0,
    val name: String = "",
    val tags: List<PetTag>
) : Parcelable{
    fun getTagNames() : String {
        return if(tags.isNotEmpty()){
            val onlyNames = tags.map {
                it.name
            }
            onlyNames.joinToString(", ")
        }else{
            " - "
        }
    }
}

@Parcelize
class PetTag(
    val id: Long,
    val name: String
) : Parcelable {
}
