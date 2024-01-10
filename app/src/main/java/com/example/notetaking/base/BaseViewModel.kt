package com.example.notetaking.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    private var _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private fun showLoading() {
        _isLoading.value = true
    }

    fun hideLoading() {
        _isLoading.value = false
    }

    protected fun <T> executeTask(
        request: suspend CoroutineScope.() -> DataState<T>,
        onSuccess: (T) -> Unit,
        onError: (Exception) -> Unit = {},
        showLoading: Boolean = true
    ) {
        if (showLoading) {
            showLoading()
        }
        viewModelScope.launch {
            when (val response = request(this)) {
                is DataState.Success -> {
                    onSuccess(response.data)
                    hideLoading()
                }

                is DataState.Error -> {
                    onError(response.exception)
                    hideLoading()
                }
            }
        }
    }

    protected fun <T> executeTaskFlow(
        request: suspend CoroutineScope.() -> Flow<DataState<T>>,
        onSuccess: (T) -> Unit,
        onError: (Exception) -> Unit = {},
        showLoading: Boolean = true
    ) {
        if (showLoading) {
            showLoading()
        }
        viewModelScope.launch {
            request(this).collect {
                when (it) {
                    is DataState.Success -> {
                        hideLoading()
                        onSuccess(it.data)
                    }

                    is DataState.Error -> {
                        hideLoading()
                        onError(it.exception)
                    }
                }
            }
        }
    }
}