package com.example.notetaking.ui.home

import androidx.lifecycle.ViewModelProvider
import com.example.notetaking.base.BaseFragment
import com.example.notetaking.databinding.FragmentHomeBinding

class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeViewModel>(FragmentHomeBinding::inflate) {
    override val viewModel: HomeViewModel
        get() = ViewModelProvider(this)[HomeViewModel::class.java]

    override fun initData() {

    }

    override fun setView() {

    }

    override fun observeData() {

    }

    override fun setOnClick() {

    }
}