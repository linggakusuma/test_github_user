package com.test.githubuser.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.githubuser.core.domain.model.Search
import com.test.githubuser.databinding.ListKeywordBinding

class SearchAdapter(private val data: (Search) -> Unit) :
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    private var keywords = ArrayList<Search>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchAdapter.SearchViewHolder {
        return SearchViewHolder(
            ListKeywordBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchAdapter.SearchViewHolder, position: Int) {
        val keyword = keywords[position]
        holder.bind(keyword)
    }

    override fun getItemCount(): Int = keywords.size

    inner class SearchViewHolder(private val binding: ListKeywordBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(search: Search) {
            binding.apply {
                data = search
                executePendingBindings()
            }
            itemView.setOnClickListener { data(search) }
        }
    }

    internal fun submitData(keywords: List<Search>) {
        this.keywords.clear()
        this.keywords.addAll(keywords)
        notifyDataSetChanged()
    }
}