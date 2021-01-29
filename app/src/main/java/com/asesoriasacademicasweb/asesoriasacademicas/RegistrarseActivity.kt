package com.asesoriasacademicasweb.asesoriasacademicas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.asesoriasacademicasweb.asesoriasacademicas.Controlador.RegistrarseControlador
import com.asesoriasacademicasweb.asesoriasacademicas.Model.Modelo
import com.asesoriasacademicasweb.asesoriasacademicas.Model.Persona
import com.asesoriasacademicasweb.asesoriasacademicas.Vista.IRegistrarseVista

class RegistrarseActivity : AppCompatActivity(), IRegistrarseVista {

    val iRegistraseControlador = RegistrarseControlador(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrarse)

        val btnInsertarPersona = findViewById<Button>(R.id.btn_registrarse_registro)
        btnInsertarPersona.setOnClickListener {

            val nombre: EditText? = findViewById(R.id.txt_nombre_registro)
            val email: EditText? = findViewById(R.id.txt_email_registro)
            val password: EditText? = findViewById(R.id.txt_password_registro)
            val repetPassword: EditText? = findViewById(R.id.txt_repet_pass_registro)
            val stringNombre = nombre?.text.toString().trim()
            val stringEmail = email?.text.toString().trim()
            val stringPass = password?.text.toString().trim()
            val stringRepetPass = repetPassword?.text.toString().trim()

            val intentRegistry = Intent(this, GestionarClaseActivity::class.java)
            iRegistraseControlador.onRegistry(this, stringNombre, stringEmail, stringPass, stringRepetPass)
            val persona = Persona(stringNombre, stringEmail, stringPass)
            if (iRegistraseControlador.insertUser(this, persona) == 1) {
                intentRegistry.putExtra("email", stringEmail)
                startActivity(intentRegistry)
            }

        }

        val btnCancelarRegistro = findViewById<Button>(R.id.btn_cancelar_registro)
        btnCancelarRegistro.setOnClickListener{
            val intentLogin = Intent(this, LoginActivity::class.java)
            startActivity(intentLogin)
        }
    }

    override fun onLoginSuccess(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }

    override fun onLoginError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }
}