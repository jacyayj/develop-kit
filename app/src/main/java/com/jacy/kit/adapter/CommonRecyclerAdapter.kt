package com.jacy.kit.adapter

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jacy.develop.kit.BR

class CommonRecyclerAdapter<T>(
    private val layoutInflater: LayoutInflater,
    private val layoutId: Int,
    var data: MutableList<T> = ArrayList(),
    private val function: (view: View, t: T, position: Int) -> Unit = { _, _, _ -> }
) : androidx.recyclerview.widget.RecyclerView.Adapter<CommonRecyclerAdapter<T>.Holder>() {

    fun refresh(data: MutableList<T>) {
        this.data = data
        notifyDataSetChanged()
    }

    fun remove(position: Int) {
        this.data.removeAt(position)
        notifyItemRemoved(position)
        if (position != data.lastIndex)
            notifyItemRangeChanged(position, data.size)
    }

    fun insert(t: T, position: Int = -1) {
        if (position == -1) {
            this.data.add(t)
            notifyItemInserted(data.lastIndex)
        } else {
            this.data.add(position, t)
            notifyItemInserted(position)
        }
    }

    fun insertData(d: MutableList<T>) {
        val lastP = data.size
        data.addAll(d)
        notifyItemRangeChanged(lastP, d.size)
    }

    override fun getItemViewType(position: Int) = position

    fun getItem(position: Int) = data[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = Holder(
        DataBindingUtil.inflate(layoutInflater, layoutId, parent, false)
    )

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView?.let { function(it, data[position], position) }
        holder.binding.setVariable(BR.item, data[position])
    }

    inner class Holder(val binding: ViewDataBinding) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root)
}
