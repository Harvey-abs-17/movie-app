package com.example.movieapp.ui.fragments.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentDetailBinding
import com.example.movieapp.model.FavoriteEntity
import com.example.movieapp.ui.fragments.detail.adapter.ActorImagesAdapter
import com.example.movieapp.utils.initRec
import com.example.movieapp.utils.makeVisible
import com.example.movieapp.viewmodel.DetailViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val navArgument: DetailFragmentArgs by navArgs()
    private val detailViewModel: DetailViewModel by viewModels()

    @Inject
    lateinit var imageAdapter: ActorImagesAdapter

    @Inject
    lateinit var favoriteEntity: FavoriteEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // send request
        detailViewModel.getDetailsMovieViewModel(navArgument.movieId)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            detailViewModel.apply {

                //init detail movie data
                detail.observe(viewLifecycleOwner) { data ->
                    Picasso.get().load(data.poster).into(mainMovieImage)
                    Picasso.get().load(data.poster).into(secondaryMovieImage)
                    movieName.text = data.title
                    movieRate.text = data.imdbRating
                    movieDate.text = data.released
                    movieLength.text = data.runtime
                    summaryTxtInfo.text = data.plot
                    actorsTxtInfo.text = data.actors
                    imageAdapter.differ.submitList(data.images)
                    actorsRec.initRec(
                        LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false),
                        imageAdapter
                    )

                    //manage loading
                    loading.observe(viewLifecycleOwner) { visible ->
                        progressBar.makeVisible(visible)
                    }


                    //manage fav button
                    lifecycleScope.launch {
                        if(checkIsExistViewModel(navArgument.movieId)){
                            favButton.setColorFilter(ContextCompat.getColor(requireContext(), R.color.red))
                        }else{
                            favButton.setColorFilter(ContextCompat.getColor(requireContext(), R.color.silver))
                        }
                    }
                    favButton.setOnClickListener {
                        favoriteEntity.apply {
                            id = navArgument.movieId
                            movie_poster = data.poster.toString()
                            movie_name = data.title.toString()
                        }
                        insertMovieToFavListViewModel(favoriteEntity)
                    }
                    isExist.observe(viewLifecycleOwner){ e ->
                        if(!e){
                            favButton.setColorFilter(ContextCompat.getColor(requireContext(), R.color.red))
                        }else{
                            favButton.setColorFilter(ContextCompat.getColor(requireContext(), R.color.silver))
                        }
                    }
                }
            }

            //back button
            backButton.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

}