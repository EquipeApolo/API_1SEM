package com.cgmdigitalhouse.cinelist.data.model

import androidx.annotation.Keep

@Keep
data class ResponseModel<T> (
    val info: PageInfoModel,
    val total_pages: Int,
    val results: List<T>
)