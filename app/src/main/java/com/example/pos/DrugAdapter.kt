package com.example.pos

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter

class DrugAdapter : ListAdapter<Drug, DrugAdapter.DrugViewHolder>(DrugComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrugViewHolder {
        val binding = ItemDrugBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DrugViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DrugViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    class DrugViewHolder(private val binding: ItemDrugBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(drug: Drug) {
            binding.apply {
                textViewName.text = drug.name
                textViewPrice.text = drug.price.toString()
                textViewQuantity.text = drug.quantity.toString()
                root.setOnClickListener {
                    // handle the click event
                }
            }
        }
    }

    class DrugComparator : DiffUtil.ItemCallback<Drug>() {
        override fun areItemsTheSame(oldItem: Drug, newItem: Drug): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Drug, newItem: Drug): Boolean {
            return oldItem == newItem
        }
    }
}
