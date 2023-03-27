package com.inviz.list_party.create_party.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.inviz.list_party.R
import com.inviz.list_party.create_party.presentation.CreatePartyViewModel
import com.inviz.list_party.databinding.FragmentCreatePartyBinding

class CreatePartyFragment : Fragment() {

    private var _binding: FragmentCreatePartyBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: CreatePartyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[CreatePartyViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreatePartyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.createPartyBtn.setOnClickListener {
            findNavController().navigate(R.id.action_createPartyFragment_to_listPartyFragment)
        }
    }
}