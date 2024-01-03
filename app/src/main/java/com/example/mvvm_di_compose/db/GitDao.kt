package com.example.mvvm_di_compose.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvm_di_compose.model.GitIssueItem
import com.example.mvvm_di_compose.model.comment.Comment

@Dao
interface GitDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGitIssue(quotes: List<GitIssueItem>)

    @Query("SELECT * FROM issue")
    suspend fun getGitIssueList() : List<GitIssueItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addComment(quotes: List<Comment>)

    @Query("SELECT * FROM comment where issue_number=:issueNumber")
    suspend fun getCommentList(issueNumber:Int) : List<Comment>

    @Query("SELECT * FROM issue where id=:issueId")
    suspend fun getIssue(issueId:Int) : GitIssueItem

}