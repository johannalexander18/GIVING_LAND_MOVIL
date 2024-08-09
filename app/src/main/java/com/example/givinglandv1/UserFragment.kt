package com.example.givinglandv1

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.givinglandv1.databinding.FragmentUserBinding
import com.example.givinglandv1.ui.user.PublicationAdapter
import com.example.givinglandv1.ui.user.UserViewModel
import com.example.givinglandv1.util.SharedPrefs


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class UserFragment : Fragment() {

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!
    private lateinit var userViewModel: UserViewModel
    private lateinit var sharedPrefs: SharedPrefs
    private lateinit var adapter: PublicationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPrefs = SharedPrefs(requireContext())
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.configButton.setOnClickListener {
            val intent = Intent(activity, ConfigurationActivity::class.java)
            startActivity(intent)
        }

        val token = sharedPrefs.authToken
        token?.let {
            userViewModel.getUser(it).observe(viewLifecycleOwner) { user ->
                user?.let {
                    binding.userName.text = user.name
                    userViewModel.getProfile(user.id).observe(viewLifecycleOwner) { profile ->
                        profile?.let {
                            Glide.with(this)
                                .load("http://192.168.0.14:8001/storage/" + profile.image.url)
                                .error(R.drawable.ic_user)
                                .into(binding.userImage)
                        }
                    }
                    // Fetch and observe user posts
                    userViewModel.getUserPosts(user.id)
                }
            }
        }

        // Observe the posts data and update RecyclerView
        userViewModel.userPosts.observe(viewLifecycleOwner) { posts ->
            posts?.let {
                adapter = PublicationAdapter(it, sharedPrefs)
                binding.recyclerView.layoutManager = LinearLayoutManager(activity)
                binding.recyclerView.adapter = adapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
