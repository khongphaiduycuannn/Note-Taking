package com.example.notetaking.data.source

import com.example.notetaking.base.DataState
import com.example.notetaking.data.model.User
import com.example.notetaking.exception.UserException
import com.example.notetaking.utils.util.toDataClass
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await

class UserRemoteDataSource {

    private val fireStore = FirebaseFirestore.getInstance()

    suspend fun getUserById(userId: String): DataState<User?> {
        try {
            val data = fireStore.collection("user")
                .document(userId)
                .get()
                .await().data

            var user: User? = null
            data?.let {
                user = it.toDataClass()
            }

            return if (user == null)
                DataState.Error(UserException("User not found."))
            else DataState.Success(user)
        } catch (exception: Exception) {
            return DataState.Error(exception)
        }
    }

    suspend fun createUser(user: User): DataState<String> {
        return try {
            fireStore.collection("user")
                .document(user.userId)
                .set(user, SetOptions.merge())
                .await()
            DataState.Success(user.userId)
        } catch (exception: Exception) {
            DataState.Error(exception)
        }
    }
}