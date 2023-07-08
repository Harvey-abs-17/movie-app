package com.example.movieapp.ui.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.movieapp.databinding.FragmentHomeBinding
import com.example.movieapp.ui.fragments.home.adapter.TopMoviesAdapter
import com.example.movieapp.utils.initRec
import com.example.movieapp.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()
    private val pagerSnapHelper by lazy { PagerSnapHelper() }

    @Inject
    lateinit var adapter: TopMoviesAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //make Top movies request
        homeViewModel.getTopMoviesViewModel()


        binding.apply {
            homeViewModel.topMoviesList.observe(viewLifecycleOwner) { movie ->
                adapter.differ.submitList(movie.data)

                topMoviesRec.initRec(
                    LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false),
                    adapter
                )

                pagerSnapHelper.attachToRecyclerView(topMoviesRec)
                movieIndicator.attachToRecyclerView(topMoviesRec, pagerSnapHelper)
            }

        }
    }

}