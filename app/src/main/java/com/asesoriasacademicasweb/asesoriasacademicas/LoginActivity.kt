package com.asesoriasacademicasweb.asesoriasacademicas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var btnRegistration = findViewById<Button>(R.id.btnRegistration)
        btnRegistration.setOnClickListener{
            val intentInsert = Intent(this, RegistrationActivity::class.java)
            startActivity(intentInsert)
        }

        var btnLogin = findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener{
            var intentLogin = Intent(this, MainActivity::class.java)
            var obj: Modelo = Modelo()
            var resBuscar = 0
            var resValidar = 0

            var email: EditText? = findViewById(R.id.txt_email_login)
            var password: EditText? = findViewById(R.id.txt_password_login)
            var emailBuscado = email?.text.toString()
            var passwordBuscada = password?.text.toString()

            if (email?.text.toString().trim().isEmpty()){
                email?.setError("El campo email no puede estar vacío")
            }else if (!Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$").matcher(email?.text.toString().trim()).matches()) {
                email?.setError("El campo email no es válido")
            }else if (password?.text.toString().trim().isEmpty()){
                password?.setError("El campo password no puede estar vacío")
            }else{
                resBuscar = obj.buscarPersona(this, emailBuscado)
                resValidar = obj.validarSesion(this, emailBuscado, passwordBuscada)
            }

            if (resBuscar == 1 && resValidar == 1)
            {
                startActivity(intentLogin)
            }else{
                Toast.makeText(this,"Correo electrónico y/o contraseña no válidos", Toast.LENGTH_SHORT).show()
            }

        }
    }
}