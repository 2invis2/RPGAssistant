package com.inviz.list_party.create_party.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.github.razir.progressbutton.*
import com.inviz.domain.entity.findRPGSystemByValue
import com.inviz.domain.entity.getRPGSystemListValues
import com.inviz.list_party.R
import com.inviz.list_party.create_party.presentation.CreatePartyState
import com.inviz.list_party.create_party.presentation.CreatePartyViewModel
import com.inviz.list_party.databinding.FragmentCreatePartyBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class CreatePartyFragment : Fragment() {

    private var _binding: FragmentCreatePartyBinding? = null

    private val binding get() = _binding!!

    private val viewModel by viewModel<CreatePartyViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreatePartyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner, ::onStateScreen)

        binding.createPartyBtn.setOnClickListener {
            viewModel.createParty(
                findRPGSystemByValue(binding.system.selectedItem as String)!!,
                binding.nameParty.text.toString()
            )
        }

        bindProgressButton(binding.createPartyBtn)
        binding.createPartyBtn.attachTextChangeAnimator()

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

    private fun onStateScreen(state: CreatePartyState) {
        when (state) {
            CreatePartyState.Default -> showDefaultScreen()
            CreatePartyState.InProgress -> showInProgressScreen()
            CreatePartyState.Complete -> showCompletedScreen()
            is CreatePartyState.Error -> showErrorDialog(state.e)
        }
    }

    private fun showDefaultScreen() {
        howShowView(View.VISIBLE)
    }

    private fun showInProgressScreen() {
        howShowView(View.VISIBLE)
        binding.apply {
            system.isEnabled = false
            nameParty.isEnabled = false
            createPartyBtn.isEnabled = false
            createPartyBtn.showProgress {
                gravity = DrawableButton.GRAVITY_CENTER
            }
        }
    }

    private fun showCompletedScreen() {
        howShowView(View.VISIBLE)

        binding.apply {
            system.isEnabled = true
            nameParty.isEnabled = true
            createPartyBtn.isEnabled = true
            createPartyBtn.hideProgress(R.string.create_party_btn)
        }

        findNavController().navigate(R.id.action_createPartyFragment_to_listPartyFragment)
    }

    private fun howShowView(visibility: Int) {
        binding.apply {
            systemDescription.visibility = visibility
            system.visibility = visibility
            nameParty.visibility = visibility
            description.visibility = visibility
            createPartyBtn.visibility = visibility
        }
    }

    private fun showErrorDialog(e: Throwable) {
        //TODO сделать диалог ошибки
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}