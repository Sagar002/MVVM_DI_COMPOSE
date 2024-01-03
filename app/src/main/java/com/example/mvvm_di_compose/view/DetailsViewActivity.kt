package com.example.mvvm_di_compose.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm_di_compose.GitApplication
import com.example.mvvm_di_compose.R
import com.example.mvvm_di_compose.databinding.ActivityDetailsViewBinding
import com.example.mvvm_di_compose.model.comment.Comment
import com.example.mvvm_di_compose.repository.Response
import com.example.mvvm_di_compose.utils.Constants
import com.example.mvvm_di_compose.viewmodels.DetailsViewModel
import com.example.mvvm_di_compose.viewmodels.DetailsViewModelFactory

class DetailsViewActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailsViewBinding
    lateinit var viewModel: DetailsViewModel
    var comments:ArrayList<Comment> =ArrayList<Comment>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details_view)
        setViewDefaultProperties()
        setData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        title = "Issue Details"
        return super.onCreateOptionsMenu(menu)
    }



    private fun setData() {
        val repository = (application as GitApplication).quoteRepository

        viewModel = ViewModelProvider(this,
            DetailsViewModelFactory(repository,intent.getIntExtra(Constants.issueID, 0)
                ,intent.getIntExtra(Constants.issueNumber, 0))
        )
            .get(DetailsViewModel::class.java)

        viewModel.comments.observe(this, Observer {
            when(it){
                is Response.Loading ->{
                    binding.loader.show()
                }
                is Response.Success ->{
                    binding.loader.hide()
                    comments.clear()
                    comments.addAll(it.data!!)
                    binding.rvComment.adapter!!.notifyDataSetChanged()

                    if(it.data.size ==0){
                        Toast.makeText(this,resources.getString(R.string.no_comments_found),Toast.LENGTH_SHORT)
                    }
                }
                is Response.Error ->{
                    binding.loader.hide()
                }
            }

        })
        viewModel.issue.observe(this, Observer {
            binding.model =it
        })


    }

    private fun setViewDefaultProperties() {
        binding.rvComment.layoutManager = LinearLayoutManager(this)
        binding.rvComment.adapter = CommentListAdapter(comments)

    }
}