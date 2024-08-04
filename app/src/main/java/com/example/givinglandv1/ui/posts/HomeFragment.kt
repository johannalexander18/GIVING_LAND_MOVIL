package com.example.givinglandv1.ui.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.givinglandv1.ItemdetailFragment
import com.example.givinglandv1.R

import com.example.givinglandv1.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: CardAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializa el ViewModel
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        // Inicializa el adaptador con una lista vacía
        adapter = CardAdapter(emptyList()) { item ->
            // Acción cuando se hace clic en un item
           /*viewModel.toggleFavorite(item)
            parentFragmentManager.beginTransaction()
                .replace(R.id.contenedor_fragment, ItemdetailFragment.newInstance(item))
                .addToBackStack(null)
                .commit()*/

        }

        // Configura el RecyclerView
        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)
        binding.recyclerView.adapter = adapter

        // Observa los cambios en la lista de posts
        viewModel.posts.observe(viewLifecycleOwner) { posts ->
            adapter.updateItems(posts)
        }

        // Configura la búsqueda
        binding.searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter(newText ?: "")
                return true
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}