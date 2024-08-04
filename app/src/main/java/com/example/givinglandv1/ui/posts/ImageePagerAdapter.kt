package com.example.givinglandv1.ui.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.givinglandv1.R
import com.example.givinglandv1.databinding.ItemImageBinding

class ImageePagerAdapter(private val imageUrls: List<String>) : RecyclerView.Adapter<ImageePagerAdapter.ImageViewHolder>() {

    inner class ImageViewHolder(val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(imageUrl: String) {
            Glide.with(binding.imageView.context)
                .load(imageUrl)
                .placeholder(R.drawable.producto1) // Imagen de reemplazo en caso de error
                .error(R.drawable.producto1) // Imagen en caso de que la carga falle
                .into(binding.imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(imageUrls[position])
    }

    override fun getItemCount(): Int = imageUrls.size
}