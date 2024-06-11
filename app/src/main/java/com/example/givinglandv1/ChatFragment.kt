package com.example.givinglandv1

import android.media.MediaRecorder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.givinglandv1.databinding.FragmentChatBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ChatFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!

    private lateinit var messageAdapter: MessageAdapter
    private val messages = mutableListOf<Message>()

    private var mediaRecorder: MediaRecorder? = null
    private var audioFilePath: String? = null
    private var isRecording: Boolean = false

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
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val chatName = arguments?.getString("chatName")
        binding.textChatHeader.text = chatName

        binding.buttonBack.setOnClickListener {
            requireActivity().onBackPressed()
        }


        // Setup RecyclerView
        messageAdapter = MessageAdapter(messages)
        binding.recyclerViewMessages.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = messageAdapter
        }

        // Load dummy messages
        loadDummyMessages()

        binding.buttonSend.setOnClickListener {
            // Add new message to the list and update RecyclerView
            val newMessageText = binding.editTextMessage.text.toString()
            if (newMessageText.isNotBlank()) {
                messages.add(Message(newMessageText, true))
                messageAdapter.notifyItemInserted(messages.size - 1)
                binding.editTextMessage.text.clear()
                binding.recyclerViewMessages.scrollToPosition(messages.size - 1)
            }
        }
    }

    private fun loadDummyMessages() {
        messages.add(Message("Hola, ¿cómo estás?", false))
        messages.add(Message("Hola! Estoy bien, ¿y tú?", true))
        messages.add(Message("Muy bien, gracias por preguntar.", false))
        messageAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ChatFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}