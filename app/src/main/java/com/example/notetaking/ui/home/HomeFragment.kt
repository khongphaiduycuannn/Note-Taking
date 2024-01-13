package com.example.notetaking.ui.home

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notetaking.R
import com.example.notetaking.base.BaseFragment
import com.example.notetaking.databinding.FragmentHomeBinding

class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeViewModel>(FragmentHomeBinding::inflate) {

    private val noteListAdapter by lazy {
        NotesListAdapter {
            onNoteItemClick()
        }
    }

    override val viewModel: HomeViewModel
        get() = ViewModelProvider(this)[HomeViewModel::class.java]

    override fun initData() {
        viewModel.getNotesList()
    }

    override fun setView() {
        val rclNotesList = binding.layoutNotesList.rclNotesList
        rclNotesList.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        rclNotesList.adapter = noteListAdapter
    }

    override fun observeData() {
        viewModel.notesList.observe(viewLifecycleOwner) { data ->
            noteListAdapter.setDataList(data)
        }
    }

    override fun setOnClick() {

    }

    private fun onNoteItemClick() {
        findNavController().navigate(R.id.action_homeFragment_to_noteFragment)
    }
}