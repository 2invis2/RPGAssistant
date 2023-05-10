package com.inviz.auth.auth.ui

import android.os.Bundle
import com.inviz.auth.auth.presentation.AuthViewModel
import com.inviz.auth.databinding.FragmentAuthBinding
import com.inviz.base.ui.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class AuthFragment : BaseFragment() {

    private var _binding: FragmentAuthBinding? = null

    private val binding get() = _binding!!

    override val viewModel by viewModel<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}