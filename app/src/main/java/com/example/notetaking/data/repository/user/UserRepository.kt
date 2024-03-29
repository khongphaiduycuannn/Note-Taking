package com.example.notetaking.data.repository.user

import com.example.notetaking.base.BaseRepository
import com.example.notetaking.base.DataState
import com.example.notetaking.data.model.User
import com.example.notetaking.data.source.UserRemoteDataSource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UserRepository :BaseRepository() {

    private val fireStore = FirebaseFirestore.getInstance()

    private val fireAuth = FirebaseAuth.getInstance()

    private val userRemoteDataSource = UserRemoteDataSource()

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

    suspend fun register(email: String, password: String, username: String): DataState<String> {
        return try {
            val data = fireAuth.createUserWithEmailAndPassword(email, password)
                .await()
            var userId = ""
            data.user?.let {
                userId = it.uid
            }
            getResult {
                userRemoteDataSource.createUser(
                    User(userId, email, username)
                )
            }
        } catch (exception: Exception) {
            DataState.Error(exception)
        }
    }
}