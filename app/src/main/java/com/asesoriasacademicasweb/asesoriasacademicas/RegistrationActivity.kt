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
        setContentView(R.layout.activity_registrarse)

        var btnInsertarPersona = findViewById<Button>(R.id.btn_registrarse_registro)
        btnInsertarPersona.setOnClickListener {
            val intentMain = Intent(this, MainActivity::class.java)
            var obj: Modelo = Modelo()
            var persona: Persona = Persona()
            var resInsert = 0

            var nombre: EditText? = findViewById(R.id.txt_nombre_registro)
            var email: EditText? = findViewById(R.id.txt_email_registro)
            var password: EditText? = findViewById(R.id.txt_password_registro)
            var repetPassword: EditText? = findViewById(R.id.txt_repet_pass_registro)

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
            }else if (!Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)\\S{8,15}$").matcher(password?.text.toString().trim()).matches()){
                password?.setError("El campo contraseña debe tener entre 8 y 15 caracteres, debe incluir al menos 1 letra minúscula, 1 letra mayúscula y 1 número")
            }else if(password?.text.toString().trim() != repetPassword?.text.toString().trim()) {
                password?.setError("Las contraseñas no coinciden. Vuelve a intentarlo")
                password?.setText("")
                repetPassword?.setText("")
            }else {
                persona.email = email?.text.toString()
                persona.nombre = nombre?.text.toString()
                persona.password = password?.text.toString()
                var resExiste = obj.buscarPersona(this, persona.email)
                if (resExiste == 1){
                    Toast.makeText(this,"Ya tenemos registrado un usuario con el email ingresado", Toast.LENGTH_SHORT).show()
                }else {
                    resInsert = obj.insertarPersona(this,persona)
                }
            }

            if (resInsert == 1)
            {
                startActivity(intentMain)
            }
        }

        var btnCancelarRegistro = findViewById<Button>(R.id.btn_cancelar_registro)
        btnCancelarRegistro.setOnClickListener{
            val intentLogin = Intent(this, LoginActivity::class.java)
            startActivity(intentLogin)
        }
    }
}