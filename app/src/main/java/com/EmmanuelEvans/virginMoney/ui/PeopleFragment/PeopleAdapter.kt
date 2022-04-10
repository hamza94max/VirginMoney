package com.EmmanuelEvans.virginMoney.ui.PeopleFragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.EmmanuelEvans.virginMoney.data.model.PeopleItem
import com.EmmanuelEvans.virginMoney.databinding.PeopleItemBinding
import com.bumptech.glide.Glide

class PeopleAdapter() : RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder>(){
    inner class PeopleViewHolder(val binding: PeopleItemBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<PeopleItem>() {

        override fun areItemsTheSame(oldItem: PeopleItem, newItem: PeopleItem): Boolean {
            return oldItem.email == newItem.email
        }

        override fun areContentsTheSame(oldItem: PeopleItem, newItem: PeopleItem): Boolean {
            return oldItem == newItem
        }
    }
    var differ = AsyncListDiffer(this, diffCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        val view = PeopleItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return PeopleViewHolder(view)
    }

    private var onItemClickListener: ((PeopleItem) -> Unit)? = null


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        val currentItem = differ.currentList[position]

        holder.itemView.apply {
            Glide.with(this).load(currentItem.avatar).into(holder.binding.avaterimg)
            holder.binding.createdAt.text = "created at : ${currentItem.createdAt}"
            holder.binding.email.text = currentItem.email
            holder.binding.jobtitle.text = "Job Title : ${currentItem.email}"
            holder.binding.firstname.text = "Name : ${currentItem.firstName} ${currentItem.lastName} "

            holder.binding.favouriteColor.text = "favouriteColor : ${currentItem.favouriteColor}"

//            setOnClickListener {
//                onItemClickListener?.let { it(currentItem) }
//            }

        }

    }

    fun setOnItemClickListener(listener: (PeopleItem) -> Unit) {
        onItemClickListener = listener
    }

    override fun getItemCount() = differ.currentList.size

}