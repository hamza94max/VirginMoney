package com.EmmanuelEvans.virginMoney.data.model


 class PeopleItems : ArrayList<PeopleItem>()


data class PeopleItem(
    val avatar: String,
    val createdAt: String,
    val email: String,
    val favouriteColor: String,
    val firstName: String,
    val id: String,
    val jobtitle: String,
    val lastName: String
)