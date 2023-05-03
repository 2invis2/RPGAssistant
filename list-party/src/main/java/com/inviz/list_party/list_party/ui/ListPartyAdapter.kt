package com.inviz.list_party.list_party.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.inviz.domain.entity.Party

class ListPartyAdapter(private var parties: List<Party>? = null) :
    RecyclerView.Adapter<PartyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartyHolder {
        return PartyHolder.inflate(parent)
    }

    override fun onBindViewHolder(holder: PartyHolder, position: Int) {
        val party = parties?.get(position)
        if (party != null) {
            holder.bind(party.name, party.system.value)
        }
    }

    override fun getItemCount(): Int {
        return parties?.size ?: 0
    }

    fun submitList(newParties: List<Party>?) {
        parties = newParties
        notifyDataSetChanged()
    }
}