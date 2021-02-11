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
import com.asesoriasacademicasweb.asesoriasacademicas.Controlador.RegistrarseControlador
import com.asesoriasacademicasweb.asesoriasacademicas.Model.Persona
import com.asesoriasacademicasweb.asesoriasacademicas.Vista.IRegistrarseVista




class RegistrarseActivity : AppCompatActivity(), IRegistrarseVista {

    val iRegistraseControlador = RegistrarseControlador(this)
    var stringNombre = ""
    var stringEmail = ""
    var stringPass = ""
    var stringRepetPass= ""
    var request: RequestQueue? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrarse)

        request = Volley.newRequestQueue(this)

        val btnInsertarPersona = findViewById<Button>(R.id.btn_registrarse_registro)
        btnInsertarPersona.setOnClickListener {

            val nombre: EditText? = findViewById(R.id.txt_nombre_registro)
            val email: EditText? = findViewById(R.id.txt_email_registro)
            val password: EditText? = findViewById(R.id.txt_password_registro)
            val repetPassword: EditText? = findViewById(R.id.txt_repet_pass_registro)
            stringNombre = nombre?.text.toString().trim()
            stringEmail = email?.text.toString().trim()
            stringPass = password?.text.toString().trim()
            stringRepetPass = repetPassword?.text.toString().trim()

            val intentRegistry = Intent(this, GestionarClaseActivity::class.java)
            if(iRegistraseControlador.onRegistry(this, stringNombre, stringEmail, stringPass, stringRepetPass) == -1) {
                val persona = Persona(stringNombre, stringEmail, stringPass)
                if (iRegistraseControlador.insertUser(this, persona) == 1) {
                    var url = "https://webserviceasesoriasacademicas.000webhostapp.com/registrar_usuario.php?nombre=$stringNombre&email=$stringEmail" +
                            "&telefono=&direccion=&password=$stringPass"
                    url = url.replace(" ","%20")
                    url = url.replace("#","%23")
                    url = url.replace("-","%2D")
                    url = url.replace("á","%C3%A1")
                    url = url.replace("é","%C3%A9")
                    url = url.replace("í","%C3%AD")
                    url = url.replace("ó","%C3%B3")
                    url = url.replace("ú","%C3%BA")
                    url = url.replace("°","%C2%B0")
                    val jsonObjectRequest = JsonObjectRequest(Request.Method.GET,url,null,
                            Response.Listener { response ->
                                if (response.getString("success") == "1"){
                                    intentRegistry.putExtra("email", stringEmail)
                                    startActivity(intentRegistry)
                                } else if(response.getString("success") == "0") {
                                    Toast.makeText(this, "\n" + "Error de registro!", Toast.LENGTH_SHORT).show()
                                }
                            },
                            Response.ErrorListener { error ->
                                Toast.makeText(this, "\n" + "Error de registro!", Toast.LENGTH_SHORT).show();
                            })
                    request?.add(jsonObjectRequest)
                }
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