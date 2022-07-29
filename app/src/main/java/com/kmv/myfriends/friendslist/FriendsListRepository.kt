package com.kmv.myfriends.friendslist

import com.kmv.myfriends.friendslist.RetrofitServices.retrofitClient
import com.kmv.myfriends.friendslist.dataclasses.Result

class FriendsListRepository {
    suspend fun getFriends(results: Int): List<Result> {
        return retrofitClient.getUsersList(results).results
    }
}