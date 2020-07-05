package com.example.testgorastudio.ui.photos

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.testgorastudio.databinding.FragmentPhotosBinding
import com.example.testgorastudio.domain.viewModel.PhotosViewModel
import com.example.testgorastudio.domain.viewModel.PhotosViewModelFactory
import com.example.testgorastudio.ui.main.MainActivity
import com.example.testgorastudio.ui.main.MainApplication

class PhotosFragment : Fragment() {

    private lateinit var binding : FragmentPhotosBinding

    private val viewModel : PhotosViewModel by lazy {
        ViewModelProvider(requireActivity(),
            PhotosViewModelFactory(requireActivity().application as MainApplication)
        ).get(PhotosViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.onBackPressedDispatcher?.addCallback(this,
            object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                closeFragment()
            }
        })
    }

    private fun closeFragment() {
        viewModel.clearPhotos()
        findNavController().navigateUp()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhotosBinding.inflate(inflater)

        getUser()
        initToolbar()
        initAdapter()

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    private fun getUser() {
        val user = PhotosFragmentArgs
            .fromBundle(requireArguments()).selectedProperty
        viewModel.fetchPhotos(user.id)
    }

    private fun initAdapter() {
        val adapter = PhotosRecyclerViewAdapter()
        binding.photosRv.adapter = adapter
    }

    private fun initToolbar() {
        (activity as MainActivity).setSupportActionBar(binding.photosToolbar)
        (activity as MainActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.photosToolbar.setNavigationOnClickListener {
            closeFragment()
        }
    }
}
