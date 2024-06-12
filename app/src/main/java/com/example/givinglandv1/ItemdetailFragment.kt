package com.example.givinglandv1

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.givinglandv1.databinding.FragmentItemdetailBinding

class ItemdetailFragment : Fragment() {

    private var _binding: FragmentItemdetailBinding? = null
    private val binding get() = _binding!!

    private var item: Item? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            item = it.getParcelable("item")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemdetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        item?.let {
            binding.itemTitle.text = it.title
            binding.itemDescription.text = it.description
            binding.itemAdditional.text = it.additionalInfo
            binding.itemLocation.text = it.location

            val adapter = ImagePagerAdapter(requireContext(), it.images)
            binding.imageViewPager.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(item: Item) =
            ItemdetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("item", item)
                }
            }
    }
}