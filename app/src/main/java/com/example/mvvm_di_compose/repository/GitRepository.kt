package com.example.mvvm_di_compose.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvm_di_compose.api.GitService
import com.example.mvvm_di_compose.db.GitDatabase
import com.example.mvvm_di_compose.utils.NetworkUtils
import com.example.mvvm_di_compose.model.GitIssueItem
import com.example.mvvm_di_compose.model.comment.Comment
import java.lang.Exception

class GitRepository(
    private val gitService: GitService,
    private val gitDatabase: GitDatabase,
    private val applicationContext: Context
) {

    private val gitLiveData = MutableLiveData<Response<List<GitIssueItem>>>()
    val issues: LiveData<Response<List<GitIssueItem>>>
    get() = gitLiveData


    private val commentLiveData = MutableLiveData<Response<List<Comment>>>()
    val comments: LiveData<Response<List<Comment>>>
        get() = commentLiveData

    private val issueModel = MutableLiveData<GitIssueItem>()
    val issue: LiveData<GitIssueItem>
        get() = issueModel


    /**
     * getIssuefrom server
     */
    suspend fun getGitIssue(){

        if(NetworkUtils.isInternetAvailable(applicationContext)){
            gitLiveData.postValue(Response.Loading())
            try {

                val result = gitService.getGitIssue()
                if (result?.body() != null) {
                    gitDatabase.gitDao().addGitIssue(result.body()!!)
                    gitLiveData.postValue(Response.Success(result.body()))
                }
            }catch (e:Exception){
                gitLiveData.postValue(Response.Error(e.message))
            }
        }
        else{
            val comments = gitDatabase.gitDao().getGitIssueList()
            gitLiveData.postValue(Response.Success(comments))
        }

    }


    /**
     * get comment by Comment list by issue number
     */
    suspend fun getComments(issueNumber:Int,issueId:Int){
        if(NetworkUtils.isInternetAvailable(applicationContext)){
            commentLiveData.postValue(Response.Loading())
            try {
                val result = gitService.getComments(issueNumber)
                if (result?.body() != null) {
                    result.body()!!.forEach { comment ->comment.issue_number = issueNumber  }
                    gitDatabase.gitDao().addComment(result.body()!!)
                    commentLiveData.postValue(Response.Success(result.body()))
                }
            }catch (e:Exception){
                commentLiveData.postValue(Response.Error(e.message))
            }
        }
        else{
            val comments = gitDatabase.gitDao().getCommentList(issueNumber)
            commentLiveData.postValue(Response.Success(comments))
        }

    }


    /**
     * getIssue from database
     */
    suspend fun getIssue(issueId:Int){
        val issue = gitDatabase.gitDao().getIssue(issueId)
        issueModel.postValue(issue)
    }
}







