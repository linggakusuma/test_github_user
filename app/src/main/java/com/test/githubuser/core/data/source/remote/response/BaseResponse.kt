package com.test.githubuser.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("items") val items: List<T>
)