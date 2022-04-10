package com.EmmanuelEvans.virginMoney.ui.ViewPager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.EmmanuelEvans.virginMoney.R
import com.EmmanuelEvans.virginMoney.databinding.FragmentViewpagerBinding
import com.EmmanuelEvans.virginMoney.ui.PeopleFragment.PeopleFragment
import com.EmmanuelEvans.virginMoney.ui.RoomsFragment.RoomFragment
import com.google.android.material.tabs.TabLayoutMediator


class ViewpagerFragment : Fragment() {


    private var _binding : FragmentViewpagerBinding ?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentViewpagerBinding.inflate(layoutInflater, container, false)


        val fragmentList = listOf(RoomFragment(), PeopleFragment())

        val adapter = ViewPagerAdapter(
            fragmentList,
            childFragmentManager,
            viewLifecycleOwner.lifecycle
        )

        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.rooms).uppercase()
                1 -> tab.text = getString(R.string.people).uppercase()
            }
        }.attach()



        return binding.root
    }


}