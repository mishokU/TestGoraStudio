package com.example.testgorastudio.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testgorastudio.databinding.UserItemBinding
import com.example.testgorastudio.domain.models.UserUIModel

class UsersRecyclerViewAdapter(private val onClickListener : OnUserClickListener) : ListAdapter<UserUIModel,
        UsersRecyclerViewAdapter.UserViewHolder>(DiffCallback) {

    companion object DiffCallback: DiffUtil.ItemCallback<UserUIModel>()     {

        override fun areItemsTheSame(oldItem: UserUIModel, newItem: UserUIModel): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: UserUIModel, newItem: UserUIModel): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : UserViewHolder {
        return UserViewHolder(
            UserItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int){
        val user = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(user)
        }
        holder.bind(user)
    }

    class UserViewHolder(private val binding: UserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UserUIModel?) {
            binding.user = user
            binding.executePendingBindings()
        }
    }

    class OnUserClickListener(val clickListener: (user: UserUIModel?) -> Unit) {
        fun onClick(user: UserUIModel) = clickListener(user)
    }
}