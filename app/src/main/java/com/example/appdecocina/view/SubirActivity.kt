package com.example.appdecocina.view
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.appdecocina.R
import com.example.appdecocina.adapter.MainAdapter
import com.example.appdecocina.model.Recetas
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import com.example.appdecocina.viewmodel.ViewModel

class SubirActivity : AppCompatActivity() {
    private val REQUEST_IMAGE_PICK = 100
    private var selectedImageBase64: String? = null
    private lateinit var editTextName: EditText
    private lateinit var spinnerCategory: Spinner
    private lateinit var editTextDescription: EditText
    private lateinit var buttonSelectImage: Button
    private lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subir)

        editTextName = findViewById(R.id.formNombre)
        spinnerCategory = findViewById(R.id.spinnerCategoria)
        editTextDescription = findViewById(R.id.formDesc)
        buttonSelectImage = findViewById(R.id.buttonSubirImg)

        val categories = listOf("Pescado", "Verduras", "Pan y bollería", "Carne", "Legumbre", "Pasta")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCategory.adapter = adapter

        buttonSelectImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, REQUEST_IMAGE_PICK)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    fun onClickInicio(item: MenuItem) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_PICK && resultCode == RESULT_OK && data != null) {
            val selectedImageUri = data.data
            if (selectedImageUri != null) {
                selectedImageBase64 = convertImageToBase64(selectedImageUri)

            }
        }
    }

    private fun convertImageToBase64(imageUri: Uri): String? {
        try {
            val inputStream = contentResolver.openInputStream(imageUri)
            val bitmap = BitmapFactory.decodeStream(inputStream)

            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
            val byteArray = byteArrayOutputStream.toByteArray()

            return Base64.encodeToString(byteArray, Base64.NO_WRAP)
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
/*
    fun onClickSubir(item: MenuItem) {

        val valorEditTextName = editTextName.text.toString()

        val valorEditTextDescription = editTextDescription.text.toString()

        if (valorEditTextName.isEmpty() || valorEditTextDescription.isEmpty()) {

        } else if (selectedImageBase64 == null) {

        } else {
            val receta = Recetas()
            receta.Nombre = valorEditTextName
            receta.Categoria = spinnerCategory.selectedItem.toString()
            receta.Descripcion = valorEditTextDescription
            receta.Imagen = selectedImageBase64.toString()

            viewModel.addRecetas(receta).observe(this, Observer { it ->
                it?.let{
                    val intent = Intent(this, SubirActivity::class.java)
                    startActivity(intent)
                }
            })
        }

    }
*/

}
