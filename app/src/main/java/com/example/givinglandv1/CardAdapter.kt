package com.example.givinglandv1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.givinglandv1.databinding.ItemCardBinding

class CardAdapter(private var itemList: List<Item>, private val listener: (Item) -> Unit) :
    RecyclerView.Adapter<CardAdapter.ViewHolder>() {

    private var itemListFiltered: List<Item> = itemList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = itemListFiltered[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return itemListFiltered.size
    }

    inner class ViewHolder(private val binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.apply {
                itemTitle.text = item.title
                itemDescription.text = item.description
                itemAdditional.text = item.additionalInfo
                itemLocation.text = item.location

                root.setOnClickListener { listener(item) }

                favoriteButton.setImageResource(if (item.isFavorite) R.drawable.ic_favorite else R.drawable.ic_favorite)
                favoriteButton.setOnClickListener {
                    listener(item)
                }
            }
        }
    }

    fun updateItems(newItems: List<Item>) {
        itemList = newItems
        itemListFiltered = newItems
        notifyDataSetChanged()
    }

    fun filter(query: String) {
        itemListFiltered = if (query.isEmpty()) {
            itemList
        } else {
            itemList.filter { it.title.contains(query, ignoreCase = true) }
        }
        notifyDataSetChanged()
    }
}