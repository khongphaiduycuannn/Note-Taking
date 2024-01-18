package com.example.notetaking.ui.login

import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.notetaking.MyApplication
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

        binding.btnCreateAccount.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()
            register(email, password)
        }
    }

    private fun register(email: String, password: String) {
        viewModel.register(email, password) {
            Toast.makeText(
                MyApplication.getAppContext(),
                "Register Success!",
                Toast.LENGTH_LONG
            ).show()
            findNavController().popBackStack()
        }
    }
}