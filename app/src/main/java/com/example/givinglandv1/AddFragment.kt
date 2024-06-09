package com.example.givinglandv1

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.givinglandv1.databinding.FragmentAddBinding
import java.io.ByteArrayOutputStream


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class AddFragment : Fragment() {


    private val IMAGE_PICK_CODE = 1000
    private val CAMERA_PICK_CODE = 1001
    private val imageUriList = mutableListOf<Uri>()
    private lateinit var imagePagerAdapter: ImagePagerAdapter
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

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
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imagePagerAdapter = ImagePagerAdapter(requireContext(), imageUriList)
        binding.viewPager.adapter = imagePagerAdapter

        binding.btnUploadPhoto.setOnClickListener {
            val options = arrayOf("Cámara", "Galería")
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Seleccionar imagen desde")
            builder.setItems(options) { dialog, which ->
                when (which) {
                    0 -> openCamera()
                    1 -> openGallery()
                }
            }
            builder.show()
        }

        binding.btnCancel.setOnClickListener {
            // Navigate to home fragment
            parentFragmentManager.beginTransaction()
                .replace(R.id.contenedor_fragment, HomeFragment())
                .commit()
        }

        binding.btnPublish.setOnClickListener {
            // Handle publish logic here
            Toast.makeText(requireContext(), "Artículo publicado", Toast.LENGTH_SHORT).show()
        }

        // Set up spinners
        val categories = arrayOf("Ropa", "Vehículos", "Teléfonos", "Joyas")
        val categoryAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, categories)
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerCategory.adapter = categoryAdapter

        val movements = arrayOf("Donaciones", "Intercambio")
        val movementAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, movements)
        movementAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerMovementType.adapter = movementAdapter
    }

    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, CAMERA_PICK_CODE)
    }

    private fun openGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK)
        galleryIntent.type = "image/*"
        startActivityForResult(galleryIntent, IMAGE_PICK_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && data != null) {
            when (requestCode) {
                IMAGE_PICK_CODE -> {
                    val selectedImageUri = data.data
                    selectedImageUri?.let {
                        imageUriList.add(it)
                        setupViewPager()
                    }
                }
                CAMERA_PICK_CODE -> {
                    val photo = data.extras?.get("data") as Bitmap
                    val tempUri = getImageUri(requireContext(), photo)
                    tempUri?.let {
                        imageUriList.add(it)
                        setupViewPager()
                    }
                }
            }
        }
    }

    private fun setupViewPager() {
        imagePagerAdapter.notifyDataSetChanged()
        binding.viewPager.visibility = View.VISIBLE
    }

    private fun getImageUri(context: Context, inImage: Bitmap): Uri? {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(context.contentResolver, inImage, "Title", null)
        return Uri.parse(path)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {


        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}