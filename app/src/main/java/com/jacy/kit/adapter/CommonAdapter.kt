package com.jacy.kit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.jacy.develop.kit.BR

/**
 * Created by Administrator on 2017/11/7.
 */
class CommonAdapter<T>(
    private val layoutInflater: LayoutInflater,
    private val layoutId: Int,
    var data: MutableList<T> = ArrayList(),
    val function: (view: View, t: T, position: Int) -> Unit = { _, _, _ -> }
) : BaseAdapter() {


    fun refresh(data: MutableList<T>) {
        this.data = data
        notifyDataSetChanged()
    }


    fun clear() {
        this.data.clear()
        notifyDataSetChanged()
    }

    fun remove(position: Int) {
        data.removeAt(position)
        notifyDataSetChanged()
    }

    fun insertSingle(item: T) {
        if (data.contains(item))
            return
        data.add(item)
        notifyDataSetChanged()
    }

    fun add(data: MutableList<T>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val binding: ViewDataBinding? = if (convertView == null)
            DataBindingUtil.inflate(layoutInflater, layoutId, parent, false)
        else
            DataBindingUtil.getBinding(convertView)
        binding?.setVariable(BR.item, data[position])
        binding?.root?.let { function(it, data[position], position) }
        return binding?.root
    }

    override fun getItem(position: Int): T = data[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount() = data.size
}
