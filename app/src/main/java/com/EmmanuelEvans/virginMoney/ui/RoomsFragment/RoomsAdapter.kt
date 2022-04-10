package com.EmmanuelEvans.virginMoney.ui.RoomsFragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.EmmanuelEvans.virginMoney.data.model.PeopleItem
import com.EmmanuelEvans.virginMoney.data.model.RoomItem
import com.EmmanuelEvans.virginMoney.databinding.PeopleItemBinding
import com.EmmanuelEvans.virginMoney.databinding.RoomItemBinding
import com.bumptech.glide.Glide

class RoomsAdapter() : RecyclerView.Adapter<RoomsAdapter.RoomsViewHolder>(){
    inner class RoomsViewHolder(val binding: RoomItemBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<RoomItem>() {

        override fun areItemsTheSame(oldItem: RoomItem, newItem: RoomItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RoomItem, newItem: RoomItem): Boolean {
            return oldItem == newItem
        }
    }

    var differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomsViewHolder {
        val view = RoomItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return RoomsViewHolder(view)
    }

    private var onItemClickListener: ((RoomItem) -> Unit)? = null


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RoomsViewHolder, position: Int) {
        val currentItem = differ.currentList[position]

        holder.itemView.apply {

            holder.binding.roomID.text = "Id of Room : ${currentItem.id}"
            holder.binding.maxOccupancy.text = "maxOccupancy of Room : ${currentItem.maxOccupancy}"
            holder.binding.isOccupied.text = "isOccupied of Room : ${currentItem.id}"
            holder.binding.createdAt.text = "createdAt : ${currentItem.createdAt}"


        }

    }

    fun setOnItemClickListener(listener: (RoomItem) -> Unit) {
        onItemClickListener = listener
    }

    override fun getItemCount() = differ.currentList.size

}