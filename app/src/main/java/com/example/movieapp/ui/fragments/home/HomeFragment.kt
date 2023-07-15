package com.example.movieapp.ui.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.movieapp.databinding.FragmentHomeBinding
import com.example.movieapp.model.MoviesResponse
import com.example.movieapp.ui.fragments.detail.DetailFragmentDirections
import com.example.movieapp.ui.fragments.home.adapter.GenreAdapter
import com.example.movieapp.ui.fragments.home.adapter.MoviesAdapter
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
    lateinit var topMoviesAdapter: TopMoviesAdapter

    @Inject
    lateinit var genreAdapter: GenreAdapter

    @Inject
    lateinit var moviesAdapter: MoviesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //make Top movies request
        homeViewModel.apply {
            getTopMoviesViewModel()
            getGenresViewModel()
            getMoviesViewModel()
        }

    }

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
        binding.apply {

            //Top Movies
            homeViewModel.apply {
                topMoviesList.observe(viewLifecycleOwner) { movie ->
                    topMoviesAdapter.differ.submitList(movie.data)
                    topMoviesRec.initRec(
                        LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false),
                        topMoviesAdapter
                    )
                    pagerSnapHelper.attachToRecyclerView(topMoviesRec)
                    movieIndicator.attachToRecyclerView(topMoviesRec, pagerSnapHelper)
                }

                //Genres
                genresList.observe(viewLifecycleOwner) { genre ->
                    genreAdapter.differ.submitList(genre)
                    genreRec.initRec(
                        LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false),
                        genreAdapter
                    )
                }

                //Movies
                moviesList.observe(viewLifecycleOwner) {
                    moviesAdapter.setData(it?.data!! as List<MoviesResponse.Data>)
                    lastMoviesRec.initRec(
                        LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false),
                        moviesAdapter
                    )
                }

                //item click listener
                moviesAdapter.onItemClickListener {
                    findNavController().navigate(DetailFragmentDirections.actionDetailFragment(it))
                }
            }
        }
    }

}