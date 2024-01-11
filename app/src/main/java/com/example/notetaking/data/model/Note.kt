package com.example.notetaking.data.model

import java.util.Date

data class Note(
    var noteId: String = "",
    var title: String,
    var content: String,
    var createdAt: Date = Date(),
    var lastUpdateAt: Date = Date(),
    var userId: String = ""
)
