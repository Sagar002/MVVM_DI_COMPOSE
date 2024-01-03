package com.example.mvvm_di_compose.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_di_compose.RecycleViewItemClick
import com.example.mvvm_di_compose.databinding.ItemIssueBinding
import com.example.mvvm_di_compose.model.GitIssueItem

class IssueListAdapter(val list:List<GitIssueItem>, val listener: RecycleViewItemClick) : RecyclerView.Adapter<IssueListAdapter.ViewHolder>() {

    lateinit var binding: ItemIssueBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemIssueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = list[position]
        holder.bind(model)
        holder.itemView.rootView.setOnClickListener(View.OnClickListener {
            listener.onItemClick(position)
        })
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(
        private val binding: ItemIssueBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: GitIssueItem) {
            binding.model = model
        }
    }
}