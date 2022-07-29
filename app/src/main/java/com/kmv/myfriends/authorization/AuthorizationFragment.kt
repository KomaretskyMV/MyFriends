package com.kmv.myfriends.authorization

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.kmv.myfriends.R
import com.kmv.myfriends.databinding.FragmentAuthorizationBinding
import com.kmv.myfriends.databinding.FragmentPageBinding
import com.kmv.myfriends.friendslist.FriendsListFragment
import kotlinx.coroutines.flow.filterNotNull

class AuthorizationFragment : Fragment() {

    private var _binding: FragmentAuthorizationBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthorizationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signInButton.setOnClickListener {
            val login = binding.login.text.toString()
            val password = binding.password.text.toString()
            viewModel.onSignInClick(login, password)
        }

        viewLifecycleOwner.lifecycleScope
            .launchWhenStarted {
                viewModel.state.filterNotNull()
                    .collect { state ->
                        when (state) {
                            State.Loading -> {
                                binding.progress.isVisible = true
                                binding.loginLayout.error = null
                                binding.passwordLayout.error = null
                                binding.signInButton.isEnabled = false
                            }
                            State.Success -> {
                                binding.progress.isVisible = false
                                binding.loginLayout.error = null
                                binding.passwordLayout.error = null
                                binding.signInButton.isEnabled = true

                                parentFragmentManager.beginTransaction()
                                    .replace(R.id.container,
                                        FriendsListFragment.newInstance(binding.login.text.toString()))
                                    .addToBackStack(null)
                                    .commit()
                            }
                            is State.Error -> {
                                binding.progress.isVisible = false
                                binding.loginLayout.error = state.loginError
                                binding.passwordLayout.error = state.passwordError
                                binding.signInButton.isEnabled = true
                            }
                        }
                    }
            }

        viewLifecycleOwner.lifecycleScope
            .launchWhenStarted {
                viewModel.error
                    .collect { message ->
                        Snackbar.make(
                            requireView(), message, Snackbar.LENGTH_SHORT
                        ).show()
                    }

            }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance() = AuthorizationFragment()
    }
}