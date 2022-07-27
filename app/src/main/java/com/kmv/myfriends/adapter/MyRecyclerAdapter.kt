package com.kmv.myfriends.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kmv.myfriends.R
import com.kmv.myfriends.databinding.RecycleviewItemBinding
import com.kmv.myfriends.dataclasses.Result
import com.kmv.myfriends.dataclasses.User
import com.squareup.picasso.Picasso

class MyRecyclerAdapter(private val usersList: List<Result>)
    : RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: RecycleviewItemBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RecycleviewItemBinding.inflate(LayoutInflater.from(parent.context))
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = usersList[position]
        val picture = user.picture.large
        val firstName = user.name.first
        val lastName = user.name.last
        val age = user.dob.age
        holder.binding.firstName.text = firstName
        holder.binding.lastName.text = lastName
        holder.binding.age.text = age.toString()
        Glide.with(holder.binding.root)
            .load(picture)
            .into(holder.binding.image)
    }

    override fun getItemCount(): Int {
        return usersList.size
    }


}