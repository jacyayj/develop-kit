/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.jwell56.usteel.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jwell56.usteel.BR

class CommonRecyclerAdapter<T>(context: Context?, private val layoutId: Int, var data: MutableList<T>?, private val function: (view: View, t: T?, position: Int) -> Unit = { _, _, _ -> }) : RecyclerView.Adapter<CommonRecyclerAdapter<T>.CommonHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }

    fun refresh(data: MutableList<T>?) {
        this.data = data
        notifyDataSetChanged()
    }

    fun remove(position: Int) {
        this.data?.removeAt(position)
        notifyItemRemoved(position)
        if (position != data?.lastIndex)
            notifyItemRangeChanged(position, data?.size ?: 0-position)
    }

    fun insert(t: T, position: Int = -1) {
        if (position == -1) {
            this.data?.add(t)
            notifyItemInserted(data?.lastIndex ?: 0)
        } else {
            this.data?.add(position, t)
            notifyItemInserted(position)
        }
    }

    fun insertData(d: MutableList<T>) {
        val lastP = data?.size
        data?.addAll(d)
        lastP?.let { notifyItemRangeChanged(it, d.size) }
    }

    override fun getItemViewType(position: Int) = position

    fun getItem(position: Int) = data?.get(position)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CommonHolder(DataBindingUtil.inflate(inflater, layoutId, parent, false))

    override fun getItemCount(): Int = data?.size ?: 0

    override fun onBindViewHolder(holder: CommonHolder, position: Int) {
        holder.itemView?.let { function(it, data?.get(position), position) }
        holder.binding?.let {
            it.setVariable(BR.item, data?.get(position))
        }
    }

    inner class CommonHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        val binding: ViewDataBinding = binding
    }
}
