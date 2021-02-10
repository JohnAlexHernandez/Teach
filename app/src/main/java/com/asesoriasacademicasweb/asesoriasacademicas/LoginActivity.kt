package com.asesoriasacademicasweb.asesoriasacademicas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.asesoriasacademicasweb.asesoriasacademicas.Controlador.LoginControlador
import com.asesoriasacademicasweb.asesoriasacademicas.Model.Estudiante
import com.asesoriasacademicasweb.asesoriasacademicas.Vista.ILoginVista
import org.json.JSONObject


class LoginActivity : AppCompatActivity(), ILoginVista{

    val iLoginControlador = LoginControlador(this)
    var stringEmail = ""
    var stringPass = ""
    var request: RequestQueue? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        request = Volley.newRequestQueue(this)

        val btnRegistration = findViewById<Button>(R.id.btn_registrarse_login)
        btnRegistration.setOnClickListener{
            val intentInsert = Intent(this, RegistrarseActivity::class.java)
            startActivity(intentInsert)
        }

        val btnLogin = findViewById<Button>(R.id.btn_iniciar_sesion_login)
        btnLogin.setOnClickListener{
            val email: EditText? = findViewById(R.id.txt_email_login)
            val password: EditText? = findViewById(R.id.txt_password_login)
            stringEmail = email?.text.toString().trim()
            stringPass = password?.text.toString().trim()

            val intentLogin = Intent(this, GestionarClaseActivity::class.java)
            if(iLoginControlador.onLogin(this, stringEmail, stringPass) == -1) {
                loadWebService()
                intentLogin.putExtra("email", stringEmail)
                startActivity(intentLogin)
            }
        }
    }

    private fun loadWebService() {
        var url = "https://webserviceasesoriasacademicas.000webhostapp.com/login.php?email=$stringEmail&password=$stringPass"
        url = url.replace(" ","%20")
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET,url,null,
                Response.Listener { response ->
                    if (response.getString("success") == "1"){
                        Toast.makeText(this, "Inicio de sesión exitoso!", Toast.LENGTH_SHORT).show()
                    } else if(response.getString("error") == "0") {
                        Toast.makeText(this, "\n" + "Error en el inicio de sesión!", Toast.LENGTH_SHORT).show()
                    }
                },
                Response.ErrorListener { error ->
                    Toast.makeText(this, "\n" + "Error en el inicio de sesión!", Toast.LENGTH_SHORT).show();
                })
        request?.add(jsonObjectRequest)
    }

    override fun onLoginSuccess(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }

    override fun onLoginError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }
}