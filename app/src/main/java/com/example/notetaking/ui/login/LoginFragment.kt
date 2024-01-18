package com.example.notetaking.ui.login

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.notetaking.R
import com.example.notetaking.base.BaseFragment
import com.example.notetaking.databinding.FragmentLoginBinding

class LoginFragment :
    BaseFragment<FragmentLoginBinding, LoginViewModel>(FragmentLoginBinding::inflate) {

    override val viewModel: LoginViewModel
        get() = ViewModelProvider(this)[LoginViewModel::class.java]

    override fun initData() {

    }

    override fun setView() {

    }

    override fun observeData() {
        viewModel.userId.observe(viewLifecycleOwner) { id ->
            if (!id.isNullOrEmpty()) {
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }
        }
    }

    override fun setOnClick() {
        binding.btnLogin.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()
            login(email, password)
        }

        binding.tvCreateAccount.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_createAccountFragment)
        }
    }

    private fun login(email: String, password: String) {
        viewModel.login(email, password)
    }
}