package com.example.movieapp.ui.fragments.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.ItemActorImagesBinding
import com.squareup.picasso.Picasso
import javax.inject.Inject

class ActorImagesAdapter @Inject constructor() :RecyclerView.Adapter<ActorImagesAdapter.ActorImagesViewHolder>(){

    private lateinit var binding :ItemActorImagesBinding

    inner class ActorImagesViewHolder :RecyclerView.ViewHolder(binding.root){

        fun bindViews(itemImage :String){
            Picasso.get().load(itemImage).into(binding.actorsImage)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorImagesViewHolder {
        binding = ItemActorImagesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ActorImagesViewHolder()
    }

    override fun getItemCount() = differ.currentList.size

    override fun onBindViewHolder(holder: ActorImagesViewHolder, position: Int) {
        holder.bindViews(differ.currentList[position])
    }

    private val differCallback = object :DiffUtil.ItemCallback<String>(){
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

}