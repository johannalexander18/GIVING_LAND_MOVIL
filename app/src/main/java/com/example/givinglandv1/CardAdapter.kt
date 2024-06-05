package com.example.givinglandv1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.givinglandv1.databinding.ItemCardBinding

class CardAdapter(private var items: List<Item>, private val onFavoriteClick: (Item) -> Unit) :
    RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCardBinding.inflate(inflater, parent, false)
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, onFavoriteClick)
    }

    override fun getItemCount(): Int = items.size

    fun updateItems(newItems: List<Item>) {
        items = newItems
        notifyDataSetChanged()
    }

    inner class CardViewHolder(private val binding: ItemCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item, onFavoriteClick: (Item) -> Unit) {
            binding.itemImage.setImageResource(item.imageResId)
            binding.itemTitle.text = item.title
            binding.itemDescription.text = item.description
            binding.itemAdditional.text = item.additionalInfo
            binding.itemLocation.text = item.location
            binding.favoriteButton.setImageResource(
                if (item.isFavorite) R.drawable.ic_favorite else R.drawable.ic_favorite
            )
            binding.favoriteButton.setOnClickListener {
                onFavoriteClick(item)
            }
        }
    }
}