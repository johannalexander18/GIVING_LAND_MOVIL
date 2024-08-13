package com.example.givinglandv1.ui.user


import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.givinglandv1.R
import com.example.givinglandv1.data.model.user.posts.Post
import com.example.givinglandv1.databinding.ItemCard2Binding
import com.example.givinglandv1.ui.MainActivity
import com.example.givinglandv1.util.SharedPrefs

class PublicationAdapter(
    private val posts: List<Post>,
    private val sharedPrefs: SharedPrefs
) : RecyclerView.Adapter<PublicationAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemCard2Binding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.editButton.setOnClickListener {
                val fragment = EditpublicFragment()

                // Obtener el post asociado a este ViewHolder
                val post = posts[adapterPosition]

                // Pasar los datos al fragmento
                val bundle = Bundle().apply {
                    putInt("postId", post.id)
                    putStringArrayList("postImages", ArrayList(post.images.map { it.url }))
                    putString("postName", post.name)
                    putString("postDescription", post.description)
                    putString("postPurpose", post.purpose)
                    putInt("postLocationId", post.location_id)
                    putInt("postCategoryId", post.category_id)
                }
                fragment.arguments = bundle

                // Navegar al fragmento de edición
                val transaction = (it.context as MainActivity).supportFragmentManager.beginTransaction()
                transaction.replace(R.id.contenedor_fragment, fragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCard2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = posts[position]
        val firstImage = post.images.firstOrNull()?.url

        if (firstImage != null) {
            Glide.with(holder.itemView.context)
                .load("http://192.168.0.14:8001/storage/$firstImage")
                .error(R.drawable.ic_user)
                .into(holder.binding.itemImage)
        }

        holder.binding.itemTitle.text = post.name
        holder.binding.itemDescription.text = post.description
        holder.binding.itemAdditional.text = post.purpose

        val location = sharedPrefs.getLocationById(post.location_id)
        holder.binding.itemLocation.text = location?.municipio ?: "Unknown"
    }

    override fun getItemCount(): Int = posts.size
}