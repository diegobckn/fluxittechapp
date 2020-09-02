package com.diegobckn.fluxit.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class AppViewHolder <T>(itemView: View):RecyclerView.ViewHolder(itemView){
    abstract fun bind(item: T, position: Int)
}