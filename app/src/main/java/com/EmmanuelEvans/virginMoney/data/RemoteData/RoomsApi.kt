package com.EmmanuelEvans.virginMoney.data.RemoteData

import com.EmmanuelEvans.virginMoney.data.model.PeopleItems
import com.EmmanuelEvans.virginMoney.data.model.RoomItems
import retrofit2.Response
import retrofit2.http.GET

interface RoomsApi {

    @GET("rooms")
    suspend fun getRooms(): Response<RoomItems>




}