package com.example.myapp012aimagetoapp

import android.content.ContentValues
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp012aimagetoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var selectedImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                selectedImageUri = uri
                binding.ivImage.setImageURI(uri)
                displayImageInfo(uri)
            }
        }

        binding.btnTakeImage.setOnClickListener {
            getContent.launch("image/*")
        }

        binding.btnSaveImage.setOnClickListener {
            saveImageWithNewName(selectedImageUri)
        }
    }

    private fun displayImageInfo(uri: Uri?) {
        uri?.let {
            val cursor = contentResolver.query(uri, null, null, null, null)
            cursor?.use {
                if (it.moveToFirst()) {
                    val displayName = it.getString(it.getColumnIndexOrThrow("_display_name"))
                    val size = it.getLong(it.getColumnIndexOrThrow("_size"))
                    binding.tvImageInfo.text = "Název: $displayName\nVelikost: ${size / 1024} KB"
                }
            }

            val inputStream = contentResolver.openInputStream(uri)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            binding.tvImageInfo.append("\nRozměry: ${bitmap.width} x ${bitmap.height}")
        }
    }

    private fun saveImageWithNewName(uri: Uri?) {
        uri?.let {
            val newName = binding.etImageName.text.toString()
            if (newName.isNotEmpty()) {
                val resolver = contentResolver
                val contentValues = ContentValues().apply {
                    put(MediaStore.Images.Media.DISPLAY_NAME, newName)
                    put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
                }

                val newUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                if (newUri != null) {
                    resolver.openInputStream(uri)?.use { inputStream ->
                        resolver.openOutputStream(newUri)?.use { outputStream ->
                            inputStream.copyTo(outputStream)
                        }
                    }
                    Toast.makeText(this, "Obrázek uložen s novým názvem.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Ukládání se nezdařilo.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Zadejte nový název souboru.", Toast.LENGTH_SHORT).show()
            }
        } ?: Toast.makeText(this, "Žádný obrázek není vybrán.", Toast.LENGTH_SHORT).show()
    }
}
