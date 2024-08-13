package com.example.givinglandv1.ui.user

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
import com.example.givinglandv1.R
import com.example.givinglandv1.data.model.posts.Category
import com.example.givinglandv1.data.model.posts.Location
import com.example.givinglandv1.databinding.FragmentEditpublicBinding
import com.example.givinglandv1.ui.posts.CategoryViewModel
import com.example.givinglandv1.ui.posts.HomeFragment
import com.example.givinglandv1.ui.posts.LocationViewModel
import com.example.givinglandv1.util.SharedPrefs
import java.io.ByteArrayOutputStream

class EditpublicFragment : Fragment() {

    private val IMAGE_PICK_CODE = 1000
    private val CAMERA_PICK_CODE = 1001
    private lateinit var imagePagerAdapter: ImagePagerAdapter
    private var _binding: FragmentEditpublicBinding? = null
    private val binding get() = _binding!!

    private lateinit var locationViewModel: LocationViewModel
    private lateinit var categoryViewModel: CategoryViewModel
    private lateinit var userViewModel: UserViewModel
    private lateinit var sharedPrefs: SharedPrefs
    private var postId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        sharedPrefs = SharedPrefs(requireContext())
        postId = arguments?.getInt("postId") ?: -1
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditpublicBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Cargar imágenes desde argumentos
        val imageUrls = arguments?.getStringArrayList("postImages") ?: arrayListOf()

        // Inicializar el ImagePagerAdapter con la lista de URLs
        imagePagerAdapter = ImagePagerAdapter(requireContext(), imageUrls) { position ->
            imageUrls.removeAt(position)
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

        val movements = arrayOf("Donaciones", "Intercambio")
        val movementAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, movements)
        movementAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerMovementType.adapter = movementAdapter

        // Initialize ViewModels
        locationViewModel = ViewModelProvider(this).get(LocationViewModel::class.java)
        categoryViewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)

        // Observe categories
        categoryViewModel.categories.observe(viewLifecycleOwner) { categories ->
            val categoryNames = categories.map { it.name }
            val categoryAdapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, categoryNames)
            categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerCategoria.adapter = categoryAdapter
        }

        // Observe locations
        locationViewModel.locations.observe(viewLifecycleOwner) { locations ->
            val locationNames = locations.map { it.municipio }
            val locationAdapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, locationNames)
            locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerMunicipio.adapter = locationAdapter
        }

        // Load data
        locationViewModel.loadLocations()
        categoryViewModel.loadCategories()

        // Cargar datos del post desde los argumentos
        binding.etArticleName.setText(arguments?.getString("postName"))
        binding.etArticleDescription.setText(arguments?.getString("postDescription"))

        // Establecer propósito en el spinner
        val purpose = arguments?.getString("postPurpose")
        if (purpose != null) {
            val purposeIndex = movements.indexOf(purpose)
            if (purposeIndex >= 0) {
                binding.spinnerMovementType.setSelection(purposeIndex)
            }
        }

        // Establecer el municipio en el spinner
        val locationId = arguments?.getInt("postLocationId") ?: -1
        locationViewModel.locations.observe(viewLifecycleOwner) { locations ->
            val selectedLocation = locations.find { it.id == locationId }
            val locationIndex = locations.indexOf(selectedLocation)
            if (locationIndex >= 0) {
                binding.spinnerMunicipio.setSelection(locationIndex)
            }
        }

        // Establecer la categoría en el spinner
        val categoryId = arguments?.getInt("postCategoryId") ?: -1
        categoryViewModel.categories.observe(viewLifecycleOwner) { categories ->
            val selectedCategory = categories.find { it.id == categoryId }
            val categoryIndex = categories.indexOf(selectedCategory)
            if (categoryIndex >= 0) {
                binding.spinnerCategoria.setSelection(categoryIndex)
            }
        }

        // Configurar ViewPager con las imágenes cargadas
        setupViewPager()

        binding.btnDelate.setOnClickListener {
            deletePost()
        }

        observeViewModel()
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
            if (imagePagerAdapter.itemCount >= 5) {
                Toast.makeText(
                    requireContext(),
                    "Solo puedes subir un máximo de 5 imágenes",
                    Toast.LENGTH_SHORT
                ).show()
                return
            }
            when (requestCode) {
                IMAGE_PICK_CODE -> {
                    val selectedImageUri = data.data
                    selectedImageUri?.let {
                        imagePagerAdapter.images.add(it.toString())
                        setupViewPager()
                    }
                }

                CAMERA_PICK_CODE -> {
                    val photo = data.extras?.get("data") as Bitmap
                    val tempUri = getImageUri(requireContext(), photo)
                    tempUri?.let {
                        imagePagerAdapter.images.add(it.toString())
                        setupViewPager()
                    }
                }
            }
        }
    }

    private fun deletePost() {
        AlertDialog.Builder(requireContext())
            .setTitle("Eliminar publicación")
            .setMessage("¿Estás seguro de que quieres eliminar esta publicación?")
            .setPositiveButton("Sí") { _, _ ->
                sharedPrefs.authToken?.let { token ->
                    userViewModel.deletePost(token, postId)
                }
            }
            .setNegativeButton("No", null)
            .show()
    }

    private fun observeViewModel() {

        userViewModel.deletePostResult.observe(viewLifecycleOwner) { success ->
            if (success) {
                Toast.makeText(context, "Publicación eliminada con éxito", Toast.LENGTH_SHORT).show()
                navigateBack()
            } else {
                Toast.makeText(context, "Error al eliminar la publicación", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun navigateBack() {
        parentFragmentManager.popBackStack()
    }

    private fun setupViewPager() {
        imagePagerAdapter.notifyDataSetChanged()
        binding.viewPager.visibility = if (imagePagerAdapter.itemCount > 0) View.VISIBLE else View.GONE
    }

    private fun getImageUri(context: Context, bitmap: Bitmap): Uri? {
        val bytes = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path =
            MediaStore.Images.Media.insertImage(context.contentResolver, bitmap, "TempImage", null)
        return Uri.parse(path)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}