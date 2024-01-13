package com.example.notetaking.ui.login

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.notetaking.base.BaseFragment
import com.example.notetaking.databinding.FragmentCreateAccountBinding

class CreateAccountFragment :
    BaseFragment<FragmentCreateAccountBinding, LoginViewModel>(FragmentCreateAccountBinding::inflate) {
    override val viewModel: LoginViewModel
        get() = ViewModelProvider(this)[LoginViewModel::class.java]

    override fun initData() {

    }

    override fun setView() {

    }

    override fun observeData() {

    }

    override fun setOnClick() {
        binding.tvLogin.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}