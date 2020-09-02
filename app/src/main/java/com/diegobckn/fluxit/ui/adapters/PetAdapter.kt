package com.diegobckn.fluxit.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.diegobckn.fluxit.R
import com.diegobckn.fluxit.domain.model.Pet
import com.diegobckn.fluxit.ui.AppViewHolder
import kotlinx.android.synthetic.main.pet_rows.view.*

class PetAdapter(private val context: Context, private val petList: List<Pet>, private val itemClickListener: OnPetClickListener) :
    RecyclerView.Adapter<AppViewHolder<*>>() {

    interface OnPetClickListener{
        fun onPetClick(pet: Pet)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder<*> {
        return MainViewHolder(
            LayoutInflater.from(context).inflate(R.layout.pet_rows, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return petList.size
    }

    override fun onBindViewHolder(holder: AppViewHolder<*>, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(petList[position], position)
        }
    }

    inner class MainViewHolder(itemView: View) : AppViewHolder<Pet>(itemView) {
        override fun bind(item: Pet, position: Int) {
            itemView.text_view_petName.text = item.name
            itemView.image_view_petImage.setImageResource(R.drawable.dog_side)
            itemView.setOnClickListener{itemClickListener.onPetClick(item)}
        }
    }
}