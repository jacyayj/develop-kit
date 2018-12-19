package com.jwell56.usteel.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.jwell56.usteel.BR

/**
 * Created by Administrator on 2017/11/7.
 */
class CommonAdapter<T>(context: Context?, layoutId: Int, data: MutableList<T>? = ArrayList(), val function: (view: View, t: T?, position: Int) -> Unit = { _, _, _ -> }) : BaseAdapter() {

    private var layoutId = layoutId
    var data: MutableList<T>? = data
    private var context = context


    fun refresh(data: MutableList<T>?) {
        this.data = data
        notifyDataSetChanged()
    }


    fun clear() {
        this.data?.clear()
        notifyDataSetChanged()
    }

    fun remove(position: Int) {
        data?.removeAt(position)
        notifyDataSetChanged()
    }

    fun insertSingle(item: T) {
        if (data?.contains(item) == true)
            return
        data?.add(item)
        notifyDataSetChanged()
    }

    fun add(data: MutableList<T>?) {
        data?.let {
            this.data?.addAll(it)
        }
        notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val binding: ViewDataBinding? = if (convertView == null)
            DataBindingUtil.inflate(LayoutInflater.from(context), layoutId, parent, false)
        else
            DataBindingUtil.getBinding(convertView)
        binding?.setVariable(BR.item, data?.get(position))
        binding?.root?.let { function(it, data?.get(position), position) }
        return binding?.root
    }

    override fun getItem(position: Int): T? = data?.get(position)

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount() = data?.size ?: 0
}
