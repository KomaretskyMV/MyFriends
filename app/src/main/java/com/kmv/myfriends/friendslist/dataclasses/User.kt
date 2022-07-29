package com.kmv.myfriends.friendslist.dataclasses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "results") val results: List<Result>,
    @Json(name = "info") val info: Info
)