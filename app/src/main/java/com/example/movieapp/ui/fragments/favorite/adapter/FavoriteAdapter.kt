package com.example.movieapp.ui.fragments.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.databinding.ItemFavoriteBinding
import com.example.movieapp.databinding.ItemMoviesBinding
import com.example.movieapp.model.FavoriteEntity
import javax.inject.Inject

class FavoriteAdapter @Inject constructor() :RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>(){

    private lateinit var binding :ItemFavoriteBinding

    inner class FavoriteViewHolder :RecyclerView.ViewHolder(binding.root){

        fun bindViews(itemMovies :FavoriteEntity){
            binding.apply {
                favorite = itemMovies
            }

            binding.root.setOnClickListener {
                itemClickListener?.let {
                    it(itemMovies.id)
                }
            }
        }

    }

    private var itemClickListener :((Int) -> Unit) ?= null

    fun onItemClickListener( listener :((Int) -> Unit) ) {
        itemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_favorite, parent, false)
        return FavoriteViewHolder()
    }

    override fun getItemCount() = differ.currentList.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindViews(differ.currentList[position])
    }

    private val differCallback = object :DiffUtil.ItemCallback<FavoriteEntity>(){
        override fun areItemsTheSame(oldItem: FavoriteEntity, newItem: FavoriteEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FavoriteEntity, newItem: FavoriteEntity): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

}