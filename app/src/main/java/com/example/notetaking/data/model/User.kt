package com.example.notetaking.data.model

data class User(
    var userId: String,
    var email: String,
    var username: String,
    var address: String = "",
    var avatar: String = ""
)
