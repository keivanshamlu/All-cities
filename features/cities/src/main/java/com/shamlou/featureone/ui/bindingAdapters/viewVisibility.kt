package com.shamlou.featureone.ui.bindingAdapters

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.shamlou.featureone.R

@BindingAdapter("binding:setEmptyStateText")
fun TextView.setEmptyStateText(size: Int) {

    text = String.format(context.getString(R.string.empty_state_text), size)
}