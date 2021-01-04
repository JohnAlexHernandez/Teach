package com.asesoriasacademicasweb.asesoriasacademicas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.regex.Pattern

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        var btnInsertarPersona = findViewById<Button>(R.id.btnRegistration_registro)
        btnInsertarPersona.setOnClickListener {
            val intentMain = Intent(this, MainActivity::class.java)
            var obj: Modelo = Modelo()
            var persona: Persona = Persona()
            var resInsert = 0

            var nombre: EditText? = findViewById(R.id.txt_nombre_registration)
            var email: EditText? = findViewById(R.id.txt_email_registration)
            var password: EditText? = findViewById(R.id.txt_password_registration)
            var repetPassword: EditText? = findViewById(R.id.txt_repetPassword_registration)

            if(nombre?.text.toString().trim().isEmpty()) {
                nombre?.setError("El campo nombre no puede estar vacío")
            }else if(email?.text.toString().trim().isEmpty()) {
                email?.setError("El campo email no puede estar vacío")
            }else if (!Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$").matcher(email?.text.toString().trim()).matches()) {
                email?.setError("El campo email no es válido")
            }else if (password?.text.toString().trim().isEmpty()) {
                password?.setError("El campo contraseña no puede estar vacío")
            }else if (repetPassword?.text.toString().trim().isEmpty()) {
                repetPassword?.setError("El campo contraseña no puede estar vacío")
            }else if(password?.text.toString().trim() != repetPassword?.text.toString().trim()) {
                password?.setError("Las contraseñas no coinciden. Vuelve a intentarlo")
                password?.setText("")
                repetPassword?.setText("")
            }else {
                persona.email = email?.text.toString()
                persona.nombre = nombre?.text.toString()
                persona.password = password?.text.toString()
                resInsert = obj.insertarPersona(this,persona)
            }

            if (resInsert == 1)
            {
                startActivity(intentMain)
                Toast.makeText(this,"Transacción exitosa", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"Transacción fallida", Toast.LENGTH_SHORT).show()
            }
        }

        var btnCancelarRegistro = findViewById<Button>(R.id.btnCancelar_registro)
        btnCancelarRegistro.setOnClickListener{
            val intentLogin = Intent(this, LoginActivity::class.java)
            startActivity(intentLogin)
        }
    }
}