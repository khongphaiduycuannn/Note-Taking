package com.example.notetaking.ui.login

import androidx.lifecycle.ViewModelProvider
import com.example.notetaking.base.BaseFragment
import com.example.notetaking.databinding.FragmentGetStartedBinding

class GetStartedFragment :
    BaseFragment<FragmentGetStartedBinding, LoginViewModel>(FragmentGetStartedBinding::inflate) {
    override val viewModel: LoginViewModel
        get() = ViewModelProvider(this)[LoginViewModel::class.java]

    override fun initData() {

    }

    override fun setView() {

    }

    override fun observeData() {

    }

    override fun setOnClick() {

    }
}