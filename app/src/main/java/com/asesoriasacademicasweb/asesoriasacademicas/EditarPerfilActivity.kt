package com.asesoriasacademicasweb.asesoriasacademicas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import java.util.regex.Pattern

class EditarPerfilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_perfil)

        val intentMain = Intent(this, MainActivity::class.java)
        var obj: Modelo = Modelo()
        var persona: Persona = Persona()
        var resUpdate = 0
        var emailBuscado= getIntent().getStringExtra("email")
        persona = obj.obtenerPersona(this, "" + emailBuscado)

        var nombre: EditText? = findViewById<EditText>(R.id.txt_nombre_editar_perfil)
        var email: EditText? = findViewById<EditText>(R.id.txt_email_editar_perfil)
        var telefono: EditText? = findViewById<EditText>(R.id.txt_telefono_editar_perfil)
        var direccion: EditText? = findViewById<EditText>(R.id.txt_direccion_editar_perfil)
        var password: EditText? = findViewById<EditText>(R.id.txt_password_editar_perfil)
        var repetPassword: EditText? = findViewById<EditText>(R.id.txt_repet_password_editar_perfil)

        nombre?.setText(persona.nombre)
        email?.setText(persona.email)
        telefono?.setText(persona.telefono)
        direccion?.setText(persona.direccion)
        password?.setText(persona.password)
        repetPassword?.setText(persona.password)

        if(nombre?.text.toString().trim().isEmpty()) {
            nombre?.setError("El campo nombre no puede estar vacío")
        }else if(email?.text.toString().trim().isEmpty()) {
            email?.setError("El campo email no puede estar vacío")
        }else if (!Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$").matcher(email?.text.toString().trim()).matches()) {
            email?.setError("El campo email no es válido")
        }else if (telefono?.text.toString().trim().isEmpty()) {
            password?.setError("El campo contraseña no puede estar vacío")
        }else if (direccion?.text.toString().trim().isEmpty()) {
            password?.setError("El campo contraseña no puede estar vacío")
        }else if (password?.text.toString().trim().isEmpty()) {
            password?.setError("El campo contraseña no puede estar vacío")
        }else if (repetPassword?.text.toString().trim().isEmpty()) {
            repetPassword?.setError("El campo contraseña no puede estar vacío")
        }else if (!Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)\\S{8,15}$").matcher(password?.text.toString().trim()).matches()){
            password?.setError("El campo contraseña debe tener entre 8 y 15 caracteres, debe incluir al menos 1 letra minúscula, 1 letra mayúscula y 1 número")
        }else if(password?.text.toString().trim() != repetPassword?.text.toString().trim()) {
            password?.setError("Las contraseñas no coinciden. Vuelve a intentarlo")
            password?.setText("")
            repetPassword?.setText("")
        }else {
            persona.email = email?.text.toString()
            persona.nombre = nombre?.text.toString()
            persona.telefono = telefono?.text.toString()
            persona.direccion = direccion?.text.toString()
            persona.password = password?.text.toString()
            var resExiste = obj.obtenerPersona(this, "" + persona.email)
        }

        if (resUpdate == 1)
        {
            startActivity(intentMain)
        }

        var btnCancelarEditarPerfil = findViewById<Button>(R.id.btn_cancelar_editar_perfil)
        btnCancelarEditarPerfil.setOnClickListener{
            val intentMain = Intent(this, MainActivity::class.java)
            intentMain.putExtra("email", "" + emailBuscado);
            startActivity(intentMain)
        }

        var btnGuardarEditarPerfil = findViewById<Button>(R.id.btn_guardar_editar_perfil)
        btnGuardarEditarPerfil.setOnClickListener{

            if(nombre?.text.toString().trim().isEmpty()) {
                nombre?.setError("El campo nombre no puede estar vacío")
            }else if(email?.text.toString().trim().isEmpty()) {
                email?.setError("El campo email no puede estar vacío")
            }else if (!Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$").matcher(email?.text.toString().trim()).matches()) {
                email?.setError("El campo email no es válido")
            }else if (telefono?.text.toString().trim().isEmpty()) {
                password?.setError("El campo contraseña no puede estar vacío")
            }else if (direccion?.text.toString().trim().isEmpty()) {
                password?.setError("El campo contraseña no puede estar vacío")
            }else if (password?.text.toString().trim().isEmpty()) {
                password?.setError("El campo contraseña no puede estar vacío")
            }else if (repetPassword?.text.toString().trim().isEmpty()) {
                repetPassword?.setError("El campo contraseña no puede estar vacío")
            }else if (!Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)\\S{8,15}$").matcher(password?.text.toString().trim()).matches()){
                password?.setError("El campo contraseña debe tener entre 8 y 15 caracteres, debe incluir al menos 1 letra minúscula, 1 letra mayúscula y 1 número")
            }else if(password?.text.toString().trim() != repetPassword?.text.toString().trim()) {
                password?.setError("Las contraseñas no coinciden. Vuelve a intentarlo")
                password?.setText("")
                repetPassword?.setText("")
            }else {
                persona.email = email?.text.toString()
                persona.nombre = nombre?.text.toString()
                persona.telefono = telefono?.text.toString()
                persona.direccion = direccion?.text.toString()
                persona.password = password?.text.toString()
                var resExiste = obj.obtenerPersona(this, "" + persona.email)
                resUpdate = obj.actualizarPersona(this,persona)
            }

            if (resUpdate == 1)
            {
                intentMain.putExtra("email", "" + emailBuscado);
                startActivity(intentMain)
            }
        }
    }
}