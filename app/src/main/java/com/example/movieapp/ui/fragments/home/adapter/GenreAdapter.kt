package com.example.movieapp.ui.fragments.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.databinding.ItemGenreBinding
import com.example.movieapp.model.GenresResponse.GenresResponseItem
import javax.inject.Inject

class GenreAdapter @Inject constructor() :RecyclerView.Adapter<GenreAdapter.GenresViewHolder>() {

    private lateinit var binding :ItemGenreBinding

    inner class GenresViewHolder :RecyclerView.ViewHolder(binding.root){

        fun setData(item :GenresResponseItem){
            binding.genre = item
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_genre, parent, false)
        return GenresViewHolder()
    }

    override fun onBindViewHolder(holder: GenresViewHolder, position: Int) {
        holder.setData(differ.currentList[position])
    }

    override fun getItemCount() = differ.currentList.size

    override fun getItemViewType(position: Int): Int {
        return position
    }

    private val differCallback = object :DiffUtil.ItemCallback<GenresResponseItem>(){
        override fun areItemsTheSame(
            oldItem: GenresResponseItem,
            newItem: GenresResponseItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: GenresResponseItem,
            newItem: GenresResponseItem
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

}