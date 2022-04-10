package com.EmmanuelEvans.virginMoney.data.RemoteData

import com.EmmanuelEvans.virginMoney.utill.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit by lazy {

        val logging = HttpLoggingInterceptor()
        logging.level = (HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()


        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    val PeopleAPI: PeopleApi by lazy {
        retrofit.create(PeopleApi::class.java)
    }

    val RoomAPI: RoomsApi by lazy {
        retrofit.create(RoomsApi::class.java)
    }







}