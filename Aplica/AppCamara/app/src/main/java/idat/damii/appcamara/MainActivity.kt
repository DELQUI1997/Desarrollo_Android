package idat.damii.appcamara

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import idat.damii.appcamara.databinding.ActivityMainBinding
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var imvFoto: ImageView
    private lateinit var currentPhotoPath: String
    private var bitmap: Bitmap? = null

    // Registro para seleccionar una imagen de la galería
    private val arlActionPick = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val path: Uri? = data?.data

            imvFoto.setImageURI(path)
        }
    }

    // Registro para capturar una foto
    private val arlActionImageCapture = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val extras: Bundle? = data?.extras

            bitmap = extras?.get("data") as Bitmap

            imvFoto.setImageBitmap(bitmap)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imvFoto = findViewById(R.id.imvFoto)

        // Asignar listeners a los botones
        binding.btnTomarFoto.setOnClickListener { takePhoto() }
        binding.btnCargarFoto.setOnClickListener { uploadPhoto() }
        binding.btnGuardarFoto.setOnClickListener { savePhoto() }

        // Aplicar márgenes de acuerdo con las barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Función para cargar una foto desde la galería
    private fun uploadPhoto() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        arlActionPick.launch(intent)
    }

    // Función para tomar una foto
    private fun takePhoto() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        arlActionImageCapture.launch(intent)
    }

    // Función para guardar una foto (a implementar)
    private fun savePhoto() {
        var outputStream: OutputStream? = null
        val drawable = imvFoto.drawable as BitmapDrawable
        val bitmap = drawable.bitmap

        // Obtener el directorio para guardar las fotos
        val filePath = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val dir = File(filePath!!.absolutePath + "*/AppPhotos/*")

        // Crear el directorio si no existe
        if (!dir.exists()) {
            dir.mkdirs()  // Usar mkdirs() para crear todo el árbol de directorios si es necesario
        }

        // Crear el archivo con un nombre único usando la hora actual
        val file = File(dir, System.currentTimeMillis().toString() + ".jpg")
        currentPhotoPath = file.absolutePath
        try {
            // Intentar abrir el archivo de salida
            outputStream = FileOutputStream(file)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }

        // Comprimir y escribir el bitmap en el archivo
        if (outputStream != null) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        }

        // Finalizar el flujo de salida
        try {
            outputStream?.flush()
            outputStream?.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        // Mostrar un mensaje indicando que la foto se guardó
        Toast.makeText(this@MainActivity, R.string.texto_guardar_foto, Toast.LENGTH_LONG).show()
    }

}
