package com.EmmanuelEvans.virginMoney.data.RemoteData

import com.EmmanuelEvans.virginMoney.data.model.PeopleItems
import retrofit2.Response
import retrofit2.http.GET

interface PeopleApi {

    @GET("people")
    suspend fun getPeople(): Response<PeopleItems>




}