package com.example.mvvm_di_compose.model.comment

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mvvm_di_compose.model.User

@Entity(tableName = "comment")
data class Comment(
    val author_association: String?,
    val body: String?,
    val created_at: String?,
    val html_url: String?,
    @PrimaryKey val id: Int?,
    var issue_number: Int?,
    val issue_url: String?,
    val node_id: String?,/*
    val reactions: Reactions?,*/
    val updated_at: String?,
    val url: String?,
    val user: User?
)