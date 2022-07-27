package com.kmv.myfriends.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.kmv.myfriends.R
import com.kmv.myfriends.adapter.MyRecyclerAdapter
import com.kmv.myfriends.databinding.FragmentPageBinding
import com.kmv.myfriends.retrofit.RetrofitServices

class PageFragment : Fragment() {

    private lateinit var binding: FragmentPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.userText.text = arguments?.getString(USER_NAME) ?: ""
        binding.recycleView.layoutManager = LinearLayoutManager(activity)

        lifecycleScope.launchWhenStarted {
            val userList = RetrofitServices.retrofitClient.getUsersList(10)
            binding.recycleView.adapter = MyRecyclerAdapter(userList.results)
        }
    }
    companion object {
        private const val USER_NAME = "USER_NAME"
        fun newInstance(userName: String) = PageFragment().apply {
            arguments = bundleOf(USER_NAME to userName)
        }
    }
}