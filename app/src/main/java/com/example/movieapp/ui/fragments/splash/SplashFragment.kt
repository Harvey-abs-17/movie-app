package com.example.movieapp.ui.fragments.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentSplashBinding
import com.example.movieapp.utils.UserDataStore
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding

    @Inject
    lateinit var userDataStore :UserDataStore


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            lifecycleScope.launch {
                val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.logo_animation)
                appLogo.animation = animation
                delay(4000)
                userDataStore.getUserDataStore().collect{
                    if(it.isEmpty()){
                        findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToRegisterFragment())
                        Toast.makeText(requireContext(), "token is empty", Toast.LENGTH_SHORT).show()
                    }else{
                        findNavController().navigate(SplashFragmentDirections.actionHomeFragment())
                        Toast.makeText(requireContext(), "token is $it", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }

}