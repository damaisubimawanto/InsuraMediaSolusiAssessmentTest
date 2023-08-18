package com.damai.base

import android.annotation.SuppressLint
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by damai007 on 18/August/2023
 */
abstract class BaseRecyclerViewAdapter<VB: ViewDataBinding, DATA> constructor(
    protected var data: List<DATA>
) : RecyclerView.Adapter<BaseViewHolder<VB, DATA>>() {

    override fun onBindViewHolder(holder: BaseViewHolder<VB, DATA>, position: Int) {
        holder.bind(data = data[position])
    }

    override fun getItemCount(): Int = data.size

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(newData: List<DATA>) {
        data = newData
        notifyDataSetChanged()
    }
}