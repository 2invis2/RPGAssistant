package com.inviz.list_party.create_party.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.github.razir.progressbutton.DrawableButton
import com.github.razir.progressbutton.attachTextChangeAnimator
import com.github.razir.progressbutton.bindProgressButton
import com.github.razir.progressbutton.hideProgress
import com.github.razir.progressbutton.showProgress
import com.inviz.base.ui.BaseFragment
import com.inviz.domain.entity.findRPGSystemByValue
import com.inviz.domain.entity.getRPGSystemListValues
import com.inviz.list_party.R
import com.inviz.list_party.create_party.presentation.CreatePartyState
import com.inviz.list_party.create_party.presentation.CreatePartyState.Complete
import com.inviz.list_party.create_party.presentation.CreatePartyState.Default
import com.inviz.list_party.create_party.presentation.CreatePartyState.Error
import com.inviz.list_party.create_party.presentation.CreatePartyState.InProgress
import com.inviz.list_party.create_party.presentation.CreatePartyTextValidation
import com.inviz.list_party.create_party.presentation.CreatePartyViewModel
import com.inviz.list_party.databinding.FragmentCreatePartyBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class CreatePartyFragment : BaseFragment() {

    private var _binding: FragmentCreatePartyBinding? = null

    private val binding get() = _binding!!

    override val viewModel by viewModel<CreatePartyViewModel>()

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
        viewModel.validateText.observe(viewLifecycleOwner, ::showValidation)

        binding.createPartyBtn.setOnClickListener {
            viewModel.isValidText(
                binding.namePartyEditText.text.toString(),
                binding.systemSpinner.text.toString()
            )
        }

        bindProgressButton(binding.createPartyBtn)
        binding.createPartyBtn.attachTextChangeAnimator()

        onSpinnerCreated()
    }

    private fun onSpinnerCreated() {
        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.item_system,
            getRPGSystemListValues()
        )

        binding.systemSpinner.setAdapter(adapter)
    }

    private fun onStateScreen(state: CreatePartyState) {
        when (state) {
            Default -> showDefaultScreen()
            InProgress -> showInProgressScreen()
            Complete -> showCompletedScreen()
            is Error -> showErrorDialog(state.throwable)
        }
    }

    private fun showValidation(valid: CreatePartyTextValidation) {
        if (validation(valid)) {
            viewModel.createParty(
                findRPGSystemByValue(binding.systemSpinner.text.toString())!!,
                binding.namePartyEditText.text.toString()
            )
        } else {
            if (!valid.nameSystemValid) {
                binding.systemLayout.error = getString(R.string.edit_text_not_empty)
            }

            if (!valid.namePartyValid) {
                binding.namePartyLayout.error = getString(R.string.edit_text_not_empty)
            }
        }
    }

    private fun validation(valid: CreatePartyTextValidation) =
        valid.namePartyValid && valid.nameSystemValid

    private fun showDefaultScreen() {
        howShowView(View.VISIBLE)
    }

    private fun showInProgressScreen() {
        howShowView(View.VISIBLE)
        howEnabledView(false)
        binding.createPartyBtn.showProgress {
            gravity = DrawableButton.GRAVITY_CENTER
        }
    }

    private fun showCompletedScreen() {
        howShowView(View.VISIBLE)
        howEnabledView(true)
        binding.createPartyBtn.hideProgress(R.string.create_party_btn)

        findNavController().navigate(R.id.action_createPartyFragment_to_listPartyFragment)
    }

    private fun howShowView(visibility: Int) {
        binding.apply {
            systemLayout.visibility = visibility
            namePartyEditText.visibility = visibility
            description.visibility = visibility
            createPartyBtn.visibility = visibility
        }
    }

    private fun howEnabledView(enabled: Boolean) {
        binding.apply {
            systemLayout.isEnabled = enabled
            namePartyEditText.isEnabled = enabled
            createPartyBtn.isEnabled = enabled
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}