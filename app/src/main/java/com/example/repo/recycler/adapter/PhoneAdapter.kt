package com.example.repo.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.repo.databinding.PhoneItemLayoutBinding

class PhoneAdapter :
    RecyclerView.Adapter<PhoneAdapter.PhoneViewHolder>() {
    lateinit var phoneNumbers: List<String>

    class PhoneViewHolder(val binding: PhoneItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneViewHolder {
        return PhoneViewHolder(
            PhoneItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PhoneViewHolder, position: Int) {
        val phoneNumber = phoneNumbers[position]

        holder.binding.detailsPhone.text = phoneNumber

    }

    override fun getItemCount() = phoneNumbers.size
}