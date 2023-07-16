package com.example.movieapp.ui.fragments.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentFavoriteBinding
import com.example.movieapp.ui.fragments.detail.DetailFragmentDirections
import com.example.movieapp.ui.fragments.favorite.adapter.FavoriteAdapter
import com.example.movieapp.ui.fragments.home.adapter.MoviesAdapter
import com.example.movieapp.ui.fragments.search.SearchFragmentDirections
import com.example.movieapp.utils.initRec
import com.example.movieapp.utils.makeVisible
import com.example.movieapp.viewmodel.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private lateinit var binding :FragmentFavoriteBinding
    private val favoriteViewModel :FavoriteViewModel by viewModels()

    @Inject
    lateinit var favoriteAdapter :FavoriteAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //get all data
        favoriteViewModel.getAllMoviesViewModel()

        binding.apply {


            //observe fav list
            favoriteViewModel.favoriteList.observe(viewLifecycleOwner){
                favoriteAdapter.differ.submitList(it)
                favRec.initRec(
                    GridLayoutManager(requireContext(), 2),
                    favoriteAdapter
                )
            }

            //observe loading
            favoriteViewModel.loading.observe(viewLifecycleOwner){

                    progressBar.makeVisible(it)
            }

            // on item click listener
            favoriteAdapter.onItemClickListener {
                findNavController().navigate(DetailFragmentDirections.actionDetailFragment(it))
            }
        }
    }
}