package com.example.mvvm_di_compose.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_di_compose.repository.GitRepository

class DetailsViewModelFactory(private val repository: GitRepository, private val issueID:Int
                              , private val issueNumber:Int)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailsViewModel(repository,issueID,issueNumber) as T
    }
}