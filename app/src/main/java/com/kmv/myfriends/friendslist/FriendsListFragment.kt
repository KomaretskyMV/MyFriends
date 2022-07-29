package com.kmv.myfriends.friendslist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.kmv.myfriends.databinding.FragmentPageBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class FriendsListFragment : Fragment() {

    private var _binding: FragmentPageBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FriendsListViewModel by viewModels()
    private val friendsListAdapter = FriendsListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPageBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.userName.text = arguments?.getString(USER_NAME) ?: ""

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.recycleView.adapter = friendsListAdapter

        viewModel.friends.onEach {
            friendsListAdapter.setData(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val USER_NAME = "USER_NAME"
        fun newInstance(userName: String) = FriendsListFragment().apply {
            arguments = bundleOf(USER_NAME to userName)
        }
    }
}