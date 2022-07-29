package com.kmv.myfriends.friendslist.dataclasses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Id(
    @Json(name = "name") val name: String,
    @Json(name = "value") val value: String?
)