package com.example.notetaking.ui.home

import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notetaking.R
import com.example.notetaking.base.BaseFragment
import com.example.notetaking.databinding.FragmentHomeBinding
import com.example.notetaking.utils.extension.delayHandler
import com.example.notetaking.utils.extension.hideKeyboard
import com.example.notetaking.view.animation.ResizeWidthAnimation
import java.lang.Integer.max

class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeViewModel>(FragmentHomeBinding::inflate) {

    private val noteListAdapter by lazy {
        NotesListAdapter {
            onNoteItemClick()
        }
    }

    private var searchBarWidth = 0

    override val viewModel: HomeViewModel
        get() = ViewModelProvider(this)[HomeViewModel::class.java]

    override fun initData() {
        viewModel.getNotesList()
    }

    override fun setView() {
        val rclNotesList = binding.layoutNotesList.rclNotesList
        rclNotesList.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        rclNotesList.adapter = noteListAdapter

        binding.tvAllNotes.viewTreeObserver.addOnGlobalLayoutListener {
            searchBarWidth = max(binding.tvAllNotes.width, binding.edtSearchNote.width)
        }
    }

    override fun observeData() {
        viewModel.notesList.observe(viewLifecycleOwner) { data ->
            noteListAdapter.setDataList(data)
        }
    }

    override fun setOnClick() {
        binding.btnMenu.setOnClickListener {
            binding.fragmentHome.openDrawer(GravityCompat.START)
        }

        binding.btnSearch.setOnClickListener {
            resizeSearchBarWidth(0, searchBarWidth)
            it.visibility = View.GONE
            binding.btnClose.visibility = View.VISIBLE
        }

        binding.btnClose.setOnClickListener {
            resizeSearchBarWidth(searchBarWidth, 0)
            it.visibility = View.GONE
            binding.btnSearch.visibility = View.VISIBLE
            binding.edtSearchNote.setText("")
        }

        binding.edtSearchNote.setOnEditorActionListener { view, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                requireContext().hideKeyboard(view)
            }
            true
        }

        binding.nvDrawerView.setNavigationItemSelectedListener { item ->
            onNavigationItemSelected(item.itemId)
        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    private fun onNoteItemClick() {
        findNavController().navigate(R.id.action_homeFragment_to_noteFragment)
    }

    private fun resizeSearchBarWidth(startWidth: Int, endWidth: Int, duration: Long = 300) {
        val animationEdtSearchNote =
            ResizeWidthAnimation(binding.edtSearchNote, startWidth, endWidth)
        animationEdtSearchNote.duration = duration
        binding.edtSearchNote.startAnimation(animationEdtSearchNote)

        val animationTvAllNote =
            ResizeWidthAnimation(binding.tvAllNotes, endWidth, startWidth)
        animationTvAllNote.duration = duration
        binding.tvAllNotes.startAnimation(animationTvAllNote)

        binding.btnSearch.isEnabled = false
        binding.btnClose.isEnabled = false
        delayHandler(duration) {
            binding.btnSearch.isEnabled = true
            binding.btnClose.isEnabled = true
        }
    }

    private fun onNavigationItemSelected(itemId: Int): Boolean {
        when (itemId) {
            R.id.nav_profile -> {
                findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
            }
            R.id.nav_team -> {
                Toast.makeText(requireContext(), "This module has not done yet!", Toast.LENGTH_LONG).show()
            }
        }
        return true
    }
}