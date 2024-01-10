package com.example.notetaking.base

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseAdapter<T, VB : ViewBinding>(
    private var dataList: MutableList<T> = mutableListOf()
) : RecyclerView.Adapter<BaseViewHolder<VB>>() {

    private var binding: VB? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<VB> {
        binding = inflateBinding(LayoutInflater.from(parent.context), parent)
        return BaseViewHolder(requireNotNull(binding))
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: BaseViewHolder<VB>, position: Int) {
        bindData(holder.binding, dataList[position], position)
        holder.itemView.setOnClickListener {
            onClickItem(dataList[position], position)
        }
    }

    protected abstract fun inflateBinding(inflater: LayoutInflater, parent: ViewGroup): VB

    protected abstract fun bindData(binding: VB, item: T, position: Int)

    protected abstract fun onClickItem(item: T, position: Int)

    fun setDataAt(position: Int, item: T) {
        if (position < 0 || position >= this.dataList.size) {
            return
        }
        this.dataList[position] = item
        notifyItemChanged(position)
    }

    fun removeAt(position: Int) {
        if (position < 0 || position >= dataList.size) {
            return
        }
        this.dataList.removeAt(position)
        notifyItemRemoved(position)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setDataList(dataList: MutableList<T>) {
        this.dataList.clear()
        this.dataList.addAll(dataList)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearDataList() {
        dataList.clear()
        notifyDataSetChanged()
    }
}