package com.example.notetaking.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

open class BaseRepository {

    suspend fun <T> getResult(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        transformError: (suspend (Exception) -> Exception)? = null,
        request: suspend CoroutineScope.() -> T,

        ): DataState<T> {
        return withContext(dispatcher) {
            try {
                DataState.Success(request())
            } catch (e: Exception) {
                DataState.Error(if (transformError != null) transformError(e) else e)
            }
        }
    }
}