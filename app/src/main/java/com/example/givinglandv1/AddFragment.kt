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
import androidx.lifecycle.ViewModelProvider
import com.example.givinglandv1.databinding.FragmentAddBinding
import com.example.givinglandv1.ui.posts.CategoryViewModel
import com.example.givinglandv1.ui.posts.HomeFragment
import com.example.givinglandv1.ui.posts.LocationViewModel
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

    private lateinit var locationViewModel: LocationViewModel
    private lateinit var categoryViewModel: CategoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imagePagerAdapter = ImagePagerAdapter(requireContext(), imageUriList) { position ->
            imageUriList.removeAt(position)
            setupViewPager()
        }
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
            parentFragmentManager.beginTransaction()
                .replace(R.id.contenedor_fragment, HomeFragment())
                .commit()
        }

        binding.btnPublish.setOnClickListener {
            Toast.makeText(requireContext(), "Artículo publicado", Toast.LENGTH_SHORT).show()
        }

        // Set up spinners
        val movements = arrayOf("Donaciones", "Intercambio")
        val movementAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, movements)
        movementAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerMovementType.adapter = movementAdapter

        // Initialize ViewModels
        locationViewModel = ViewModelProvider(this).get(LocationViewModel::class.java)
        categoryViewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)

        // Observe categories
        categoryViewModel.categories.observe(viewLifecycleOwner) { categories ->
            val categoryNames = categories.map { it.name }
            val categoryAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, categoryNames)
            categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerCategoria.adapter = categoryAdapter
        }

        // Observe locations
        locationViewModel.locations.observe(viewLifecycleOwner) { locations ->
            val locationNames = locations.map { it.municipio }
            val locationAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, locationNames)
            locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerMunicipio.adapter = locationAdapter
        }

        // Load data
        locationViewModel.loadLocations()
        categoryViewModel.loadCategories()
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
            if (imageUriList.size >= 5) {
                Toast.makeText(requireContext(), "Solo puedes subir un máximo de 5 imágenes", Toast.LENGTH_SHORT).show()
                return
            }
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
        binding.viewPager.visibility = if (imageUriList.isNotEmpty()) View.VISIBLE else View.GONE
    }

    private fun getImageUri(context: Context, bitmap: Bitmap): Uri? {
        val bytes = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(context.contentResolver, bitmap, "TempImage", null)
        return Uri.parse(path)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}