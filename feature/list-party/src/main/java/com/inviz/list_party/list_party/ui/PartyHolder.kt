package com.inviz.list_party.list_party.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.inviz.list_party.databinding.ItemPartyBinding

class PartyHolder private constructor(private val binding: ItemPartyBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(name: String, system: String) {
        binding.nameParty.text = name
        binding.systemParty.text = system
    }

    companion object {
        fun inflate(parent: ViewGroup): PartyHolder {
            val binding =
                ItemPartyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return PartyHolder(binding)
        }
    }
}