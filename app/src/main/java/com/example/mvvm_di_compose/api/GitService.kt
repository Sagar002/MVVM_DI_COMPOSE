package com.example.mvvm_di_compose.api

import com.example.mvvm_di_compose.model.GitIssueItem
import com.example.mvvm_di_compose.model.comment.Comment
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GitService {

    @GET("repos/square/okhttp/issues")
    suspend fun getGitIssue() : Response<List<GitIssueItem>>

    @GET("repos/square/okhttp/issues/{issue_id}/comments")
    suspend fun getComments(@Path(value = "issue_id", encoded = true)issueId:Int) : Response<List<Comment>>

}