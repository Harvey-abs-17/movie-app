package com.example.movieapp.ui.fragments.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.databinding.ItemTopMoviesBinding
import com.example.movieapp.model.MoviesResponse
import javax.inject.Inject

class TopMoviesAdapter @Inject constructor(): RecyclerView.Adapter<TopMoviesAdapter.TopMoviesViewHolder>() {

    private lateinit var binding: ItemTopMoviesBinding

    inner class TopMoviesViewHolder : RecyclerView.ViewHolder(binding.root) {
        fun bindViews(data: MoviesResponse.Data) {
            binding.movie = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopMoviesViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_top_movies,
            parent,
            false
        )
        return TopMoviesViewHolder()
    }

    override fun onBindViewHolder(holder: TopMoviesViewHolder, position: Int) {
        holder.bindViews(differ.currentList[position])
    }

    override fun getItemCount() = 5

    override fun getItemViewType(position: Int): Int {
        return position
    }

    private val differCallback = object : DiffUtil.ItemCallback<MoviesResponse.Data>() {
        override fun areItemsTheSame(oldItem: MoviesResponse.Data, newItem: MoviesResponse.Data): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MoviesResponse.Data, newItem: MoviesResponse.Data): Boolean {
            return oldItem == newItem
        }
    }

    var differ = AsyncListDiffer(this, differCallback)


}