package com.example.givinglandv1.ui.posts

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.givinglandv1.R
import com.example.givinglandv1.data.model.posts.Post
import com.example.givinglandv1.databinding.ItemCardBinding
import com.squareup.picasso.Picasso

class CardAdapter(
    private var items: List<Post>,
    private val onItemClick: (Post) -> Unit
) : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    private var filteredItems: List<Post> = items

    inner class CardViewHolder(val binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            binding.itemTitle.text = post.name
            binding.itemDescription.text = post.description
            binding.itemAdditional.text = post.purpose
            binding.itemLocation.text = post.location_id.toString()

            // Cargar la primera imagen
            if (!post.images.isNullOrEmpty()) {
                val imageUrl = "http://192.168.0.14:8000/storage/${post.images[0].url}"
                Glide.with(binding.itemImage.context)
                    .load(imageUrl)
                    .error(R.drawable.producto1)
                    .into(binding.itemImage)
            } else {
                binding.itemImage.setImageResource(R.drawable.producto1)
            }

            binding.root.setOnClickListener {
                onItemClick(post)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding = ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(filteredItems[position])
    }

    override fun getItemCount(): Int = filteredItems.size

    fun updateItems(newItems: List<Post>) {
        items = newItems
        filteredItems = newItems
        notifyDataSetChanged()
    }

    fun filter(query: String) {
        filteredItems = if (query.isEmpty()) {
            items
        } else {
            items.filter { it.name.contains(query, ignoreCase = true) || it.description.contains(query, ignoreCase = true) }
        }
        notifyDataSetChanged()
    }
}