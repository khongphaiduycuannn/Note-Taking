package com.example.notetaking.ui.note

import androidx.lifecycle.ViewModelProvider
import com.example.notetaking.base.BaseFragment
import com.example.notetaking.databinding.FragmentNoteBinding

class NoteFragment :
    BaseFragment<FragmentNoteBinding, NoteViewModel>(FragmentNoteBinding::inflate) {
    override val viewModel: NoteViewModel
        get() = ViewModelProvider(this)[NoteViewModel::class.java]

    override fun initData() {

    }

    override fun setView() {
        binding.edtNoteTitle.requestFocus()
    }

    override fun observeData() {

    }

    override fun setOnClick() {

    }
}