package com.test.githubuser.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("login") val login:String?,
    @SerializedName("id") val id:Int?,
    @SerializedName("avatar_url") val avatar:String?
)