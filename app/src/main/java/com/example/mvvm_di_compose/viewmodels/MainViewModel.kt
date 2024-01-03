package com.example.mvvm_di_compose.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_di_compose.repository.GitRepository
import com.example.mvvm_di_compose.repository.Response
import com.example.mvvm_di_compose.model.GitIssueItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: GitRepository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO){
            repository.getGitIssue()
        }

    }

    val gitIssueItem : LiveData<Response<List<GitIssueItem>>>
    get() = repository.issues


    fun refreshData(){
        viewModelScope.launch(Dispatchers.IO){
            repository.getGitIssue()
        }
    }
}