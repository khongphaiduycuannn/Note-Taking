package com.example.notetaking.ui.login

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.notetaking.MyApplication
import com.example.notetaking.base.BaseViewModel
import com.example.notetaking.data.repository.user.UserRepository

class LoginViewModel : BaseViewModel() {

    private val userRepository = UserRepository()

    private val _userId = MutableLiveData("")
    val userId: LiveData<String> get() = _userId

    fun login(email: String, password: String) {
        executeTask(
            request = {
                userRepository.login(email, password)
            },
            onSuccess = { data ->
                _userId.value = data
            },
            onError = { exception ->
                Toast.makeText(MyApplication.getAppContext(), exception.message, Toast.LENGTH_LONG)
                    .show()
            }
        )
    }

    fun register(email: String, password: String) {
        executeTask(
            request = {
                userRepository.register(email, password)
            },
            onSuccess = {
                Toast.makeText(
                    MyApplication.getAppContext(),
                    "Register Success!",
                    Toast.LENGTH_LONG
                )
                    .show()
            },
            onError = { exception ->
                Toast.makeText(MyApplication.getAppContext(), exception.message, Toast.LENGTH_LONG)
                    .show()
            }
        )
    }
}