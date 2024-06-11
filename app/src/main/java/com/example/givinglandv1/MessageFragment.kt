package com.example.givinglandv1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.givinglandv1.databinding.FragmentMessageBinding


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class MessageFragment : Fragment() {

    private var _binding: FragmentMessageBinding? = null
    private val binding get() = _binding!!

    private lateinit var chatAdapter: ChatAdapter

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMessageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewChats.layoutManager = LinearLayoutManager(context)
        chatAdapter = ChatAdapter(getDummyChats()) { chat ->
            // On item click, navigate to ChatFragment
            val bundle = Bundle().apply {
                putString("chatName", chat.name)
            }
            val chatFragment = ChatFragment().apply {
                arguments = bundle
            }
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.contenedor_fragment, chatFragment)
                .addToBackStack(null)
                .commit()
        }
        binding.recyclerViewChats.adapter = chatAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getDummyChats(): List<Chat> {
        return listOf(
            Chat("Persona 1", "Conversación 1"),
            Chat("Persona 2", "Conversación 2"),
            // Añadir más chats de prueba aquí
        )
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MessageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}