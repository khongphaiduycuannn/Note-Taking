package com.example.notetaking.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.projectbase.utils.extension.showLoading

abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel>(
    private val bindingInflater: (LayoutInflater) -> VB
) : Fragment() {

    private var _binding: VB? = null
    protected val binding: VB
        get() = _binding as VB

    protected abstract val viewModel: VM

    private val dialogLoading by lazy { context?.let { Dialog(it) } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
    }

    abstract fun initData()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.isLoading.observe(viewLifecycleOwner) {
            if (it) {
                if (dialogLoading?.isShowing == false)
                    dialogLoading?.showLoading(viewLifecycleOwner)
            } else {
                dialogLoading?.dismiss()
            }
        }
        setView()
        observeData()
        setOnClick()
    }

    abstract fun setView()

    abstract fun observeData()

    abstract fun setOnClick()

    override fun onDestroyView() {
        dialogLoading?.dismiss()
        super.onDestroyView()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}