package com.example.givinglandv1.ui.user

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.givinglandv1.databinding.ItemImage2Binding
import com.example.givinglandv1.databinding.ItemImageBinding
import com.example.givinglandv1.ui.posts.FullScreenImageActivity


class ImagePagerAdapter(
    private val context: Context,
    private val images: MutableList<Uri>,
    private val onDelete: (Int) -> Unit
    ) : RecyclerView.Adapter<ImagePagerAdapter.ImageViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
            val binding = ItemImage2Binding.inflate(LayoutInflater.from(context), parent, false)
            return ImageViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
            holder.binding.imageView2.setImageURI(images[position])
            holder.binding.btnDelete.setOnClickListener {
                onDelete(position)
            }
            holder.binding.imageView2.setOnClickListener {
                val intent = Intent(context, FullScreenImageActivity::class.java)
                intent.putExtra("imageUri", images[position].toString())
                context.startActivity(intent)
            }
        }

        override fun getItemCount(): Int {
            return images.size
        }

        class ImageViewHolder(val binding: ItemImage2Binding) : RecyclerView.ViewHolder(binding.root)
    }