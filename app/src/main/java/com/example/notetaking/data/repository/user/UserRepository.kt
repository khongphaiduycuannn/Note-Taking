package com.example.notetaking.data.repository.user

import com.example.notetaking.base.DataState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UserRepository {

    private val fireStore = FirebaseFirestore.getInstance()

    private val fireAuth = FirebaseAuth.getInstance()

    suspend fun login(email: String, password: String): DataState<String> {
        return try {
            val data = fireAuth.signInWithEmailAndPassword(email, password)
                .await()
            var userId = ""
            data.user?.let {
                userId = it.uid
            }
            DataState.Success(userId)
        } catch (exception: Exception) {
            DataState.Error(exception)
        }
    }

    suspend fun register(email: String, password: String): DataState<String> {
        return try {
            val data = fireAuth.createUserWithEmailAndPassword(email, password)
                .await()
            var userId = ""
            data.user?.let {
                userId = it.uid
            }
            DataState.Success(userId)
        } catch (exception: Exception) {
            DataState.Error(exception)
        }
    }
}