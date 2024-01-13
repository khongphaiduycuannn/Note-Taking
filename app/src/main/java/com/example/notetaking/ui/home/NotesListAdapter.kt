package com.example.notetaking.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.notetaking.base.BaseAdapter
import com.example.notetaking.data.model.Note
import com.example.notetaking.databinding.ItemNoteBinding

class NotesListAdapter(
    private val onClick: () -> Unit
) : BaseAdapter<Note, ItemNoteBinding>() {
    override fun inflateBinding(inflater: LayoutInflater, parent: ViewGroup): ItemNoteBinding {
        return ItemNoteBinding.inflate(inflater)
    }

    override fun onClickItem(item: Note, position: Int) {
        onClick.invoke()
    }

    override fun bindData(binding: ItemNoteBinding, item: Note, position: Int) {
        binding.tvNoteTitle.text = item.title
        binding.tvNoteContent.text = item.content
    }
}