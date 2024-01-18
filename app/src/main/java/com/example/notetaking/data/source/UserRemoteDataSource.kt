package com.example.notetaking.data.source

import com.example.notetaking.data.model.User
import com.example.notetaking.exception.UserException
import com.example.notetaking.utils.util.toDataClass
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await

class UserRemoteDataSource {

    private val fireStore = FirebaseFirestore.getInstance()

    suspend fun getUserById(userId: String): User? {
        val data = fireStore.collection("user")
            .document(userId)
            .get()
            .await().data

        var user: User? = null
        data?.let {
            user = it.toDataClass()
        }

        if (user == null)
            throw UserException("User not found.")
        else return user
    }

    suspend fun createUser(user: User): String {
        fireStore.collection("user")
            .document(user.userId)
            .set(user, SetOptions.merge())
            .await()
        return user.userId
    }
}