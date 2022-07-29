package com.kmv.myfriends.friendslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kmv.myfriends.databinding.FriendsItemBinding
import com.kmv.myfriends.friendslist.dataclasses.Result

class FriendsListAdapter : RecyclerView.Adapter<FriendsViewHolder>() {

    private var data: List<Result> = emptyList()
    fun setData(data: List<Result>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendsViewHolder {
        val binding = FriendsItemBinding.inflate(LayoutInflater.from(parent.context))
        return FriendsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FriendsViewHolder, position: Int) {
        val item = data.getOrNull(position)
        with(holder.binding) {
            firstName.text = item?.name?.first
            lastName.text = item?.name?.last
            age.text = item?.dob?.age.toString()
            item?.let {
                Glide
                    .with(image.context)
                    .load(it.picture.large)
                    .into(image)
            }
        }
    }
}

class FriendsViewHolder(val binding: FriendsItemBinding)
    : RecyclerView.ViewHolder(binding.root)