package com.example.movieapp.ui.fragments.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.movieapp.databinding.FragmentRegisterBinding
import com.example.movieapp.model.UserRegisterBody
import com.example.movieapp.utils.UserDataStore
import com.example.movieapp.utils.makeVisible
import com.example.movieapp.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private val registerViewModel: RegisterViewModel by viewModels()

    @Inject
    lateinit var dataStore: UserDataStore

    @Inject
    lateinit var userBody: UserRegisterBody

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            submitButton.setOnClickListener {
                val userName = nameEdt.text.toString()
                val userEmail = emailEdt.text.toString()
                val userPassword = passwordEdt.text.toString()

                if (userName.isNotEmpty() && userEmail.isNotEmpty() && userPassword.isNotEmpty()) {
                    userBody.apply {
                        name = userName
                        email = userEmail
                        password = userPassword
                    }
                    registerViewModel.getUserDataViewModel(userBody)
                }
            }
            registerViewModel.loadingViewModel.observe(viewLifecycleOwner) { visible ->
                progressBar.makeVisible(visible)
                submitButton.makeVisible(!visible)
            }

            registerViewModel.userDataViewModel.observe(viewLifecycleOwner) { data ->
                lifecycleScope.launch {
                    dataStore.setUserToken(data.name!!)
                }

            }
        }

    }
}