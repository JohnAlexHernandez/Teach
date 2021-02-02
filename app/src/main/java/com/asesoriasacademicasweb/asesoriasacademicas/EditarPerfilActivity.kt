package com.asesoriasacademicasweb.asesoriasacademicas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.asesoriasacademicasweb.asesoriasacademicas.Controlador.EditarPerfilControlador
import com.asesoriasacademicasweb.asesoriasacademicas.Model.Estudiante
import com.asesoriasacademicasweb.asesoriasacademicas.Model.Modelo
import com.asesoriasacademicasweb.asesoriasacademicas.Model.Persona
import com.asesoriasacademicasweb.asesoriasacademicas.Vista.IEditarPerfilVista

class EditarPerfilActivity : AppCompatActivity(), IEditarPerfilVista {

    val iEditarPerfilControlador = EditarPerfilControlador(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_perfil)

        var persona = Persona()
        val emailBuscado= getIntent().getStringExtra("email")
        persona = iEditarPerfilControlador.getUser(this, "" + emailBuscado)

        val nombre: EditText? = findViewById<EditText>(R.id.txt_nombre_editar_perfil)
        val email: EditText? = findViewById<EditText>(R.id.txt_email_editar_perfil)
        val telefono: EditText? = findViewById<EditText>(R.id.txt_telefono_editar_perfil)
        val direccion: EditText? = findViewById<EditText>(R.id.txt_direccion_editar_perfil)
        val password: EditText? = findViewById<EditText>(R.id.txt_password_editar_perfil)
        val repetPassword: EditText? = findViewById<EditText>(R.id.txt_repet_password_editar_perfil)

        nombre?.setText(persona.nombre)
        email?.setText(persona.email)
        telefono?.setText(persona.telefono)
        direccion?.setText(persona.direccion)
        password?.setText(persona.contrasenia)
        repetPassword?.setText(persona.contrasenia)

        val btnCancelarEditarPerfil = findViewById<Button>(R.id.btn_cancelar_editar_perfil)
        btnCancelarEditarPerfil.setOnClickListener{
            val intentMain = Intent(this, GestionarClaseActivity::class.java)
            intentMain.putExtra("email", emailBuscado);
            startActivity(intentMain)
        }

        val btnGuardarEditarPerfil = findViewById<Button>(R.id.btn_guardar_editar_perfil)
        btnGuardarEditarPerfil.setOnClickListener{

            val nombre: EditText? = findViewById(R.id.txt_nombre_editar_perfil)
            val email: EditText? = findViewById(R.id.txt_email_editar_perfil)
            val telefono: EditText? = findViewById(R.id.txt_telefono_editar_perfil)
            val direccion: EditText? = findViewById(R.id.txt_direccion_editar_perfil)
            val password: EditText? = findViewById(R.id.txt_password_editar_perfil)
            val repetPassword: EditText? = findViewById(R.id.txt_repet_password_editar_perfil)
            val stringNombre = nombre?.text.toString().trim()
            val stringEmail = email?.text.toString().trim()
            val stringTelefono = telefono?.text.toString().trim()
            val stringDireccion = direccion?.text.toString().trim()
            val stringPass = password?.text.toString().trim()
            val stringRepetPass = repetPassword?.text.toString().trim()

            val intentEditProfile = Intent(this, GestionarClaseActivity::class.java)
            if(iEditarPerfilControlador.onEditProfile(this, stringNombre, stringEmail, stringTelefono, stringDireccion, stringPass, stringRepetPass) == -1) {
                val persona = Persona(stringNombre, stringEmail, stringTelefono, stringDireccion, stringPass, "Estudiante")
                if (iEditarPerfilControlador.updateProfile(this, persona) == 1) {
                    intentEditProfile.putExtra("email", stringEmail)
                    startActivity(intentEditProfile)
                }
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