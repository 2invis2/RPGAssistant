package com.inviz.list_party.create_party.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.inviz.domain.entity.RPGSystem
import com.inviz.domain.entity.getRPGSystemListValues
import com.inviz.list_party.R
import com.inviz.list_party.create_party.presentation.CreatePartyState
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

        viewModel.state.observe(viewLifecycleOwner, ::onStateViews)

        binding.createPartyBtn.setOnClickListener {
            findNavController().navigate(R.id.action_createPartyFragment_to_listPartyFragment)
        }

        binding.createPartyBtn.setOnClickListener {
            binding.system.onItemSelected { position ->
                viewModel.createParty(
                    RPGSystem.valueOf(binding.system.adapter.getItem(position) as String),
                    binding.nameParty.text.toString()
                )
            }
        }

        onSpinnerCreated()
    }

    private fun onSpinnerCreated() {
        val spinner = binding.system
        spinner.onItemSelectedListener

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            getRPGSystemListValues()
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

    private fun onStateViews(state: CreatePartyState) {
        when (state) {
            CreatePartyState.Complete -> showCompletedScreen()
            is CreatePartyState.Error -> showErrorDialog(state.e)
        }
    }

    private fun showCompletedScreen() {
        binding.apply {
            systemDescription.visibility = View.VISIBLE
            system.visibility = View.VISIBLE
            nameParty.visibility = View.VISIBLE
            description.visibility = View.VISIBLE
            createPartyBtn.visibility = View.VISIBLE
        }
    }


    private fun showErrorDialog(e: Throwable) {

    }
}

fun Spinner.onItemSelected(listener: (Int) -> Unit) {
    onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {}
    }
}