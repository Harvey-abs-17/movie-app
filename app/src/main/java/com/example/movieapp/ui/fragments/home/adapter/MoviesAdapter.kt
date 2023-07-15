package com.example.movieapp.ui.fragments.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.databinding.ItemMoviesBinding
import com.example.movieapp.model.MoviesResponse.Data
import javax.inject.Inject

class MoviesAdapter @Inject constructor() :RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    private lateinit var binding :ItemMoviesBinding
    private var movieData = emptyList<Data>()

    inner class MoviesViewHolder :RecyclerView.ViewHolder(binding.root){

        fun bindView(movieItem :Data){
            binding.movie = movieItem

            //item click listener
            binding.root.setOnClickListener {
                itemClickListener?.let {
                    it(movieItem.id!!)
                }
            }

        }

    }

    fun setData(movie: List<Data>){
        val differ = MoviesDifferCallback(movieData, movie)
        val differCallback = DiffUtil.calculateDiff(differ)
        movieData = movie
        differCallback.dispatchUpdatesTo(this)
    }

    private var itemClickListener: ((Int) -> Unit) ?= null
    fun onItemClickListener( listener :((Int) -> Unit) ){
        itemClickListener = listener
    }

    inner class MoviesDifferCallback(private val oldItem :List<Data>, private val newItem :List<Data>) :DiffUtil.Callback(){
        override fun getOldListSize() = oldItem.size

        override fun getNewListSize() = newItem.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldItem[oldItemPosition] === newItem[newItemPosition]

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldItem[oldItemPosition] == newItem[newItemPosition]

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_movies, parent, false)
        return MoviesViewHolder()
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bindView(movieData[position])
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount() = movieData.size

}