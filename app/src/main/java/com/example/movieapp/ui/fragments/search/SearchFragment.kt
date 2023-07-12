package com.example.movieapp.ui.fragments.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentSearchBinding
import com.example.movieapp.model.MoviesResponse
import com.example.movieapp.ui.fragments.home.adapter.MoviesAdapter
import com.example.movieapp.utils.initRec
import com.example.movieapp.utils.makeVisible
import com.example.movieapp.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding

    private val searchViewModel: SearchViewModel by viewModels()

    @Inject
    lateinit var searchAdapter: MoviesAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            searchEditText.addTextChangedListener {
                searchViewModel.getSearchResultViewModel(it.toString())
            }

            searchViewModel.apply {

                searchListResult.observe(viewLifecycleOwner) {
                    searchAdapter.setData(it!!.data!! as List<MoviesResponse.Data>)
                    searchRec.initRec(
                        LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false),
                        searchAdapter
                    )
                }
                isEmpty.observe(viewLifecycleOwner) {
                    if (it) {
                        notFoundImage.makeVisible(it)
                        notFountTxt.makeVisible(it)
                    } else {
                        notFoundImage.makeVisible(it)
                        notFountTxt.makeVisible(it)
                    }
                }
                loading.observe(viewLifecycleOwner) {
                    if (it) {
                        searchProgress.makeVisible(it)
                        searchRec.makeVisible(!it)
                    } else {
                        searchProgress.makeVisible(it)
                        searchRec.makeVisible(!it)
                    }
                }

            }
        }

    }

}