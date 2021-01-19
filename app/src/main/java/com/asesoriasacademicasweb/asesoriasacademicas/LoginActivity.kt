package com.asesoriasacademicasweb.asesoriasacademicas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.asesoriasacademicasweb.asesoriasacademicas.Controlador.ILoginControlador
import com.asesoriasacademicasweb.asesoriasacademicas.Controlador.LoginControlador
import com.asesoriasacademicasweb.asesoriasacademicas.Model.Persona
import com.asesoriasacademicasweb.asesoriasacademicas.Vista.ILoginVista

class LoginActivity : AppCompatActivity(), ILoginVista{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val iLoginControlador = LoginControlador(this)

        val btnRegistration = findViewById<Button>(R.id.btn_registrarse_login)
        btnRegistration.setOnClickListener{
            val intentInsert = Intent(this, RegistrarseActivity::class.java)
            startActivity(intentInsert)
        }

        val btnLogin = findViewById<Button>(R.id.btn_iniciar_sesion_login)
        btnLogin.setOnClickListener{
            val email: EditText? = findViewById(R.id.txt_email_login)
            val password: EditText? = findViewById(R.id.txt_password_login)
            val stringEmail = email?.text.toString().trim()
            val stringPass = password?.text.toString().trim()

            val intentLogin = Intent(this, MainActivity::class.java)
            iLoginControlador.onLogin(this, stringEmail, stringPass)
            val persona = Persona("",stringEmail, "", "", stringPass)
            if(persona.esValido(this) == -1) {
                intentLogin.putExtra("email", "" + stringEmail)
                startActivity(intentLogin)
            }
        }
    }

    override fun onLoginSuccess(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }

    override fun onLoginError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }
}