package idat.aplication.decormania

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import idat.aplication.decormania.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        // Listener para el botón de registro (abre la pantalla de registro)
        binding.registrar.setOnClickListener {
            val intent = Intent(this, RegistrarActivity::class.java)
            startActivity(intent)
        }

        // Intentar registrar un nuevo usuario con email y contraseña
        binding.google.setOnClickListener {
            val email = binding.email.text.toString().trim()
            val password = binding.password.text.toString().trim()

            // Verificar que los campos no estén vacíos
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(baseContext, "Por favor ingrese el correo y la contraseña.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener  // Salir de la función sin intentar registrar al usuario
            }

            // Verificar que el correo sea de Gmail
            if (!isValidGmail(email)) {
                Toast.makeText(baseContext, "Por favor ingrese un correo de Gmail válido.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Registrar usuario con email y contraseña
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Registro exitoso
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        // Si falla el registro, muestra el error
                        Log.w(TAG, "Error al crear usuario con correo electrónico.", task.exception)
                        Toast.makeText(baseContext, "Error de autenticación.", Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                }
        }

        // Listener para manejar la entrada de los "system bars" y ajustar el padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Método para verificar si el correo electrónico es un correo de Gmail válido
    private fun isValidGmail(email: String): Boolean {
        return email.endsWith("@gmail.com")
    }

    // Método para actualizar la UI según el estado de autenticación
    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            // Si el usuario está autenticado, ir a la MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()  // Finaliza esta actividad para que no se quede en la pila de actividades
        } else {
            // Si no hay usuario autenticado, puedes mostrar un mensaje o realizar otras acciones
            Toast.makeText(baseContext, "No hay usuario autenticado.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            // Si ya hay un usuario autenticado, redirigir a MainActivity
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish() // Evita que el usuario regrese a esta actividad usando el botón atrás
        }
    }
}
