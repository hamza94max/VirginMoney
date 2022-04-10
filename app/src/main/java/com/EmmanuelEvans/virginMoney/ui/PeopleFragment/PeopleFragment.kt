package com.EmmanuelEvans.virginMoney.ui.PeopleFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.EmmanuelEvans.virginMoney.data.RemoteData.RetrofitInstance
import com.EmmanuelEvans.virginMoney.databinding.FragmentPeopleBinding
import retrofit2.HttpException
import java.io.IOException

class PeopleFragment : Fragment() {


    private var _binding : FragmentPeopleBinding ?= null
    private val binding get() = _binding!!

    private lateinit var peopleAdapter: PeopleAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentPeopleBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        lifecycleScope.launchWhenCreated {
            binding.ProgressBar.isVisible = true
            handlePeopleResponse()
            binding.ProgressBar.isVisible = false
        }

        setupRecyclerView()


    }



    private suspend fun handlePeopleResponse() {

        val response = try {
            RetrofitInstance.PeopleAPI.getPeople()
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
            peopleAdapter.differ.submitList(response.body()!!)
        } else {
            Log.i("TAG", "Response not successful")
        }

    }

    private fun setupRecyclerView() {
        binding.peopleRecyclerview.apply {
            peopleAdapter = PeopleAdapter()
            layoutManager = LinearLayoutManager(context)
            binding.peopleRecyclerview.adapter = peopleAdapter
        }
    }









}