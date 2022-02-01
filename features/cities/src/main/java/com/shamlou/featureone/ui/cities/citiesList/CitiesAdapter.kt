package com.shamlou.featureone.ui.cities.citiesList

import com.shamlou.bases_android.recyclerview.adapter.AdapterBase
import com.shamlou.featureone.R
import com.shamlou.featureone.model.posts.ResponseCityView


class CitiesAdapter : AdapterBase<ResponseCityView>(
    areItemsTheSame = { oldItem: ResponseCityView, newItem: ResponseCityView -> oldItem._id == newItem._id },
    areContentsTheSame = { oldItem: ResponseCityView, newItem: ResponseCityView -> oldItem == newItem }
) {

    override fun getItemViewType(position: Int): Int = R.layout.city_item
}