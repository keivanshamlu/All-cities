package com.shamlou.bases_android.bindingAdapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shamlou.bases_android.recyclerview.adapter.AdapterBase

object RecyclerViewBindingAdapter {

    @Suppress("UNCHECKED_CAST")
    @JvmStatic
    @BindingAdapter("binding:setData")
    fun <T> RecyclerView.setRecyclerViewData(data: List<T>?) {
            data?.let {
                when(adapter){
                    is AdapterBase<*> -> (adapter as AdapterBase<T>).submitList(it)
                    else -> {}
                }
            }

    }
}