package com.example.givinglandv1.ui.posts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.example.givinglandv1.R
import com.example.givinglandv1.data.model.posts.Post
import com.example.givinglandv1.databinding.FragmentItemdetailBinding

class ItemdetailFragment : Fragment() {

    private var _binding: FragmentItemdetailBinding? = null
    private val binding get() = _binding!!

    private var post: Post? = null
    private var municipio: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemdetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtén los datos pasados a este fragmento
        post = arguments?.getParcelable("post")
        municipio = arguments?.getString("municipio")

        post?.let {
            binding.itemTitle.text = it.name
            binding.itemDescription.text = it.description
            binding.itemAdditional.text = it.purpose
            binding.itemLocation.text = municipio ?: "Desconocido"

            // Configura el ViewPager para mostrar las imágenes
            val imageUrls = it.images?.map { image -> "http://192.168.0.14:8001/storage/${image.url}" } ?: emptyList()
            val adapter = ImageePagerAdapter(imageUrls)
            binding.imageViewPager.adapter = adapter
        }

        val buttonBack: ImageButton = view.findViewById(R.id.buttonBack)
        buttonBack.setOnClickListener {
            closeFragment()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun closeFragment() {
        parentFragmentManager.popBackStack()
    }

    companion object {
        fun newInstance(post: Post, municipio: String) = ItemdetailFragment().apply {
            arguments = Bundle().apply {
                putParcelable("post", post)
                putString("municipio", municipio)
            }
        }
    }
}