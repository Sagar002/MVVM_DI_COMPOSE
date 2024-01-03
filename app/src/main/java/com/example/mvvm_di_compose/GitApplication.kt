package com.example.mvvm_di_compose

import android.app.Application
import com.example.mvvm_di_compose.api.GitService
import com.example.mvvm_di_compose.api.RetrofitHelper
import com.example.mvvm_di_compose.db.GitDatabase
import com.example.mvvm_di_compose.repository.GitRepository
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GitApplication : Application() {
    lateinit var quoteRepository: GitRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val quoteService = RetrofitHelper.getInstance().create(GitService::class.java)
        val database = GitDatabase.getDatabase(applicationContext)
        quoteRepository = GitRepository(quoteService, database, applicationContext)
    }
}