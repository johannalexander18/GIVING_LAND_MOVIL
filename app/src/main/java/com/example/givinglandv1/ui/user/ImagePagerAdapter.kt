package com.example.givinglandv1.ui.user

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.givinglandv1.R
import com.example.givinglandv1.databinding.ItemImage2Binding
import com.example.givinglandv1.databinding.ItemImageBinding
import com.example.givinglandv1.ui.posts.FullScreenImageActivity

class ImagePagerAdapter(
    private val context: Context,
    val images: MutableList<String>, // Cambiado a String para manejar tanto URL como Uri
    private val onDelete: (Int) -> Unit
) : RecyclerView.Adapter<ImagePagerAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemImage2Binding.inflate(LayoutInflater.from(context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageUri = images[position]

        if (imageUri.startsWith("http")) {
            // Si es una URL, carga con Glide
            Glide.with(context)
                .load(imageUri)
                .placeholder(R.drawable.producto1) // Asumiendo que tienes un drawable de placeholder
                .error(R.drawable.producto1) // Asumiendo que tienes un drawable de error
                .into(holder.binding.imageView2)
        } else {
            // Si es un URI local, carga directamente
            holder.binding.imageView2.setImageURI(Uri.parse(imageUri))
        }

        holder.binding.btnDelete.setOnClickListener {
            onDelete(position)
        }

        holder.binding.imageView2.setOnClickListener {
            val intent = Intent(context, FullScreenImageActivity::class.java)
            intent.putExtra("imageUri", imageUri)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return images.size
    }

    class ImageViewHolder(val binding: ItemImage2Binding) : RecyclerView.ViewHolder(binding.root)
}