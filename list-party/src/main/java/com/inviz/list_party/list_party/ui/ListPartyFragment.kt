package com.inviz.list_party.list_party.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.inviz.base.ui.BaseFragment
import com.inviz.domain.entity.Party
import com.inviz.list_party.R
import com.inviz.list_party.databinding.FragmentListPartyBinding
import com.inviz.list_party.list_party.presentation.ListPartyState
import com.inviz.list_party.list_party.presentation.ListPartyState.Complete
import com.inviz.list_party.list_party.presentation.ListPartyState.Error
import com.inviz.list_party.list_party.presentation.ListPartyState.InProgress
import com.inviz.list_party.list_party.presentation.ListPartyViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListPartyFragment : BaseFragment() {

    private var _binding: FragmentListPartyBinding? = null

    private val binding get() = _binding!!

    override val viewModel by viewModel<ListPartyViewModel>()

    private lateinit var adapter: ListPartyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = ListPartyAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListPartyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner, ::onStateScreen)

        binding.partyRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.partyRecycler.adapter = adapter

        binding.createPartyFab.setOnClickListener {
            findNavController().navigate(R.id.action_listPartyFragment_to_createPartyFragment)
        }

        viewModel.loadParties()
    }

    private fun onStateScreen(state: ListPartyState) {
        when (state) {
            InProgress -> showInProgressScreen()
            is Complete -> showCompletedScreen(state.parties)
            is Error -> showErrorDialog(state.throwable)
        }
    }

    private fun showInProgressScreen() {
    }

    private fun showCompletedScreen(parties: Set<Party>?) {
        adapter.submitList(parties?.toList())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}