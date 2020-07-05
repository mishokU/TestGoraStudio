package com.example.testgorastudio.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.testgorastudio.databinding.FragmentUsersBinding
import com.example.testgorastudio.domain.viewModel.UsersViewModel
import com.example.testgorastudio.domain.viewModel.UsersViewModelFactory
import com.example.testgorastudio.ui.main.MainApplication


class UsersFragment : Fragment() {

    private lateinit var binding : FragmentUsersBinding
    private lateinit var adapter : UsersRecyclerViewAdapter

    private val viewModel : UsersViewModel by lazy {
        ViewModelProvider(requireActivity(),
            UsersViewModelFactory(requireActivity().application as MainApplication)
        ).get(UsersViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUsersBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        initUsersAdapter()
        observePhotos()

        return binding.root
    }

    private fun observePhotos() {
        viewModel.showUserPhotos.observe(viewLifecycleOwner, Observer {
            it?.let {
                this.findNavController().navigate(UsersFragmentDirections.actionUsersFragmentToAlbumsFragment(it))
                viewModel.onUserPhotosComplete()
            }
        })
    }

    private fun initUsersAdapter() {
        adapter = UsersRecyclerViewAdapter(UsersRecyclerViewAdapter.OnUserClickListener{
            viewModel.showUserPhotos(it)
        })
        binding.usersRv.adapter = adapter
    }
}
