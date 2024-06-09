package com.example.givinglandv1


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.givinglandv1.databinding.ItemCard2Binding

class PublicationAdapter(private val publications: List<Publication>) : RecyclerView.Adapter<PublicationAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemCard2Binding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.editButton.setOnClickListener {
                // Navegar al fragmento de edición
                val fragment = EditpublicFragment()
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
        val publication = publications[position]
        holder.binding.itemImage.setImageResource(publication.imageResId)
        holder.binding.itemTitle.text = publication.title
        holder.binding.itemDescription.text = publication.description
        holder.binding.itemAdditional.text = publication.additional
        holder.binding.itemLocation.text = publication.location
    }

    override fun getItemCount(): Int = publications.size
}