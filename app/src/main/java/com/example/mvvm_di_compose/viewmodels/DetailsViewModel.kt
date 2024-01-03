package com.example.mvvm_di_compose.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_di_compose.repository.GitRepository
import com.example.mvvm_di_compose.repository.Response
import com.example.mvvm_di_compose.model.GitIssueItem
import com.example.mvvm_di_compose.model.comment.Comment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel(private val repository: GitRepository, private val issueId:Int, private val issueNumber:Int) : ViewModel() {


    private val issueModel = MutableLiveData<GitIssueItem>()

    val comments : LiveData<Response<List<Comment>>>
        get() = repository.comments

    val issue:LiveData<GitIssueItem>
        get() = repository.issue


    init {
        viewModelScope.launch(Dispatchers.IO){
            repository.getComments(issueNumber,issueId)
        }
        viewModelScope.launch(Dispatchers.IO) {
            repository.getIssue(issueId)
        }
    }

}