package com.example.mvvm_di_compose.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "issue")
data class GitIssueItem(
    @PrimaryKey() val id: Int?,
    val body: String?,
    val closed_at: String?,
    val comments: Int?,
    val comments_url: String?,
    val created_at: String?,
    val events_url: String?,
    val title: String?,
    val number: Int?,
    val updated_at: String?,
    val url: String?,
     val user: User?
)