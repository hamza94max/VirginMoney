package com.EmmanuelEvans.virginMoney.data.model


class RoomItems : ArrayList<RoomItem>()

data class RoomItem(
    val createdAt: String,
    val id: String,
    val isOccupied: Boolean,
    val maxOccupancy: Int
)