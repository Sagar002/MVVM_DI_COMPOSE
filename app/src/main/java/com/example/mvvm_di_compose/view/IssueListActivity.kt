package com.example.mvvm_di_compose.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm_di_compose.GitApplication
import com.example.mvvm_di_compose.R
import com.example.mvvm_di_compose.RecycleViewItemClick
import com.example.mvvm_di_compose.databinding.ActivityIssueListBinding
import com.example.mvvm_di_compose.repository.Response
import com.example.mvvm_di_compose.viewmodels.MainViewModel
import com.example.mvvm_di_compose.viewmodels.MainViewModelFactory

import com.example.mvvm_di_compose.model.GitIssueItem
import com.example.mvvm_di_compose.utils.Constants

class IssueListActivity : AppCompatActivity() , RecycleViewItemClick {
    lateinit var mainViewModel: MainViewModel
    lateinit var binding: ActivityIssueListBinding
    var issueList:ArrayList<GitIssueItem> =ArrayList<GitIssueItem>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_issue_list)
        setViewDefaultProperties()
        setData()

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        title = "Issue List"
        return super.onCreateOptionsMenu(menu)
    }

    /**
     * set data to view
     */
    private fun setData() {
        val repository = (application as GitApplication).quoteRepository

        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)

        mainViewModel.gitIssueItem.observe(this, Observer {
            when(it){
                is Response.Loading ->{
                    binding.loader.show()
                }
                is Response.Success ->{
                    binding.loader.hide()
                    issueList.clear()
                    issueList.addAll(it.data!!)
                    binding.recycleView.adapter!!.notifyDataSetChanged()
                }
                is Response.Error ->{
                    binding.loader.hide()
                }
            }

        })
    }

    /**
     * initialise default properties of view
     */
    private fun setViewDefaultProperties() {
        binding.recycleView.layoutManager = LinearLayoutManager(this)
        binding.recycleView.adapter = IssueListAdapter(issueList,this)

    }

    /**
     * to handle click Event from RecycleView
     */
    override fun onItemClick(position:Int){
        val intent = Intent(this, DetailsViewActivity::class.java)
        intent.putExtra(Constants.issueID, issueList.get(position).id)
        intent.putExtra(Constants.issueNumber, issueList.get(position).number)
        startActivity(intent)

    }
}