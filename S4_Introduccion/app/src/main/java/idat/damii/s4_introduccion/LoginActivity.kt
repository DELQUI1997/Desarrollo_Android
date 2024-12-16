package idat.damii.s4_introduccion

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val usuario: EditText = findViewById(R.id.usuario)
        var contraseña: EditText = findViewById(R.id.contraseña)
        var ingresar: Button = findViewById(R.id.btningresar)

        ingresar.setOnClickListener(View.OnClickListener {
            autentificarUsuario(usuario.text.toString(),
                contraseña.text.toString())
        })


    }
    private fun autentificarUsuario(usr: String, pwd:String){
        if(usr=="Admin"&& pwd=="admin123") {
            //redirigir al activity main
            var intent = Intent(applicationContext, MainActivity::class.java)
            intent.putExtra("usuario", usr)

            startActivity(intent)
        }else{
            Toast.makeText(this@LoginActivity,
                "usuario o contraseña incorrectos",
                Toast.LENGTH_LONG).show()

            startActivity(intent)

        }
    }
}