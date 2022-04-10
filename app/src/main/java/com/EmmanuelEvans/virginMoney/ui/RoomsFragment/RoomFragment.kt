package com.EmmanuelEvans.virginMoney.ui.RoomsFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.EmmanuelEvans.virginMoney.R
import com.EmmanuelEvans.virginMoney.data.RemoteData.RetrofitInstance
import com.EmmanuelEvans.virginMoney.databinding.FragmentRoomBinding
import com.EmmanuelEvans.virginMoney.ui.PeopleFragment.PeopleAdapter
import retrofit2.HttpException
import java.io.IOException


class RoomFragment : Fragment() {

    private var _binding : FragmentRoomBinding ?= null
    private val binding get() = _binding!!

    private lateinit var RoomsAdapter: RoomsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRoomBinding.inflate(layoutInflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenCreated {
            binding.ProgressBar.isVisible = true
            handleRoomResponse()
            binding.ProgressBar.isVisible = false
        }
        setupRecyclerView()


    }


    private suspend fun handleRoomResponse() {
        val response = try {
            RetrofitInstance.RoomAPI.getRooms()
        } catch (e: IOException) {
            Log.i("TAG", " IOException : don't have internet connection")
            binding.ProgressBar.isVisible = false

            return
        } catch (e: HttpException) {
            Log.i("TAG", "HttpException : unexpected response")
            binding.ProgressBar.isVisible = false
            return
        }

        if (response.isSuccessful && response.body() != null) {
            RoomsAdapter.differ.submitList(response.body()!!)
        } else {
            Log.i("TAG", "Response not successful")
        }

    }



    private fun setupRecyclerView() {
        binding.roomsRecyclerview.apply {
            RoomsAdapter = RoomsAdapter()
            layoutManager = LinearLayoutManager(context)
            binding.roomsRecyclerview.adapter = RoomsAdapter


        }
    }
}