package com.asesoriasacademicasweb.asesoriasacademicas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.asesoriasacademicasweb.asesoriasacademicas.Controlador.EditarPerfilControlador
import com.asesoriasacademicasweb.asesoriasacademicas.Model.Persona
import com.asesoriasacademicasweb.asesoriasacademicas.Vista.IEditarPerfilVista
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class EditarPerfilActivity : AppCompatActivity(), IEditarPerfilVista {

    val iEditarPerfilControlador = EditarPerfilControlador(this)
    var request: RequestQueue? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_perfil)

        request = Volley.newRequestQueue(this)

        var persona = Persona()
        val emailBuscado= getIntent().getStringExtra("email")
        //persona = iEditarPerfilControlador.getUser(this, "" + emailBuscado)

        val nombre: EditText? = findViewById<EditText>(R.id.txt_nombre_editar_perfil)
        val telefono: EditText? = findViewById<EditText>(R.id.txt_telefono_editar_perfil)
        val direccion: EditText? = findViewById<EditText>(R.id.txt_direccion_editar_perfil)
        val password: EditText? = findViewById<EditText>(R.id.txt_password_editar_perfil)
        val repetPassword: EditText? = findViewById<EditText>(R.id.txt_repet_password_editar_perfil)

        var url = "https://webserviceasesoriasacademicas.000webhostapp.com/cargar_perfil.php?email=$emailBuscado"
        url = url.replace(" ","%20")
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET,url,null,
                Response.Listener { response ->
                    try {
                        val jsonArray = response.optJSONArray("user")
                        val jsonObjet = jsonArray.getJSONObject(0)
                        persona.nombre = jsonObjet.getString("nombre")
                        persona.telefono = jsonObjet.getString("telefono")
                        persona.email = jsonObjet.getString("email")
                        persona.direccion = jsonObjet.getString("direccion")
                        persona.contrasenia = jsonObjet.getString("password")

                        nombre?.setText(persona.nombre)
                        telefono?.setText(persona.telefono)
                        direccion?.setText(persona.direccion)
                        password?.setText(persona.contrasenia)
                        repetPassword?.setText(persona.contrasenia)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                },
                Response.ErrorListener { error ->
                    Toast.makeText(this, "\n" + "Ocurrió un error cargando la información de su perfil!", Toast.LENGTH_SHORT).show();
                })
        request?.add(jsonObjectRequest)

        val btnCancelarEditarPerfil = findViewById<Button>(R.id.btn_cancelar_editar_perfil)
        btnCancelarEditarPerfil.setOnClickListener{
            val intentMain = Intent(this, GestionarClaseActivity::class.java)
            intentMain.putExtra("email", emailBuscado);
            startActivity(intentMain)
        }

        val btnGuardarEditarPerfil = findViewById<Button>(R.id.btn_guardar_editar_perfil)
        btnGuardarEditarPerfil.setOnClickListener{

            val nombre: EditText? = findViewById(R.id.txt_nombre_editar_perfil)
            val telefono: EditText? = findViewById(R.id.txt_telefono_editar_perfil)
            val direccion: EditText? = findViewById(R.id.txt_direccion_editar_perfil)
            val password: EditText? = findViewById(R.id.txt_password_editar_perfil)
            val repetPassword: EditText? = findViewById(R.id.txt_repet_password_editar_perfil)
            val stringNombre = nombre?.text.toString().trim()
            val stringEmail = persona.email
            val stringTelefono = telefono?.text.toString().trim()
            val stringDireccion = direccion?.text.toString().trim()
            val stringPass = password?.text.toString().trim()
            val stringRepetPass = repetPassword?.text.toString().trim()

            val intentEditProfile = Intent(this, GestionarClaseActivity::class.java)
            if(iEditarPerfilControlador.onEditProfile(this, stringNombre, stringEmail, stringTelefono, stringDireccion, stringPass, stringRepetPass) == -1) {
                val persona = Persona(stringNombre, stringEmail, stringTelefono, stringDireccion, stringPass, "Estudiante")
                if (iEditarPerfilControlador.updateProfile(this, persona) == 1) {
                    var url = "https://webserviceasesoriasacademicas.000webhostapp.com/editar_perfil.php?nombre=$stringNombre&email=$stringEmail" +
                            "&telefono=$stringTelefono&direccion=$stringDireccion&password=$stringPass"
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
                                    intentEditProfile.putExtra("email", stringEmail!!)
                                    startActivity(intentEditProfile)
                                } else if(response.getString("error") == "0") {
                                    Toast.makeText(this, "\n" + "Ocurrió un error en la actualización de su perfil!", Toast.LENGTH_SHORT).show()
                                }
                            },
                            Response.ErrorListener { error ->
                                Toast.makeText(this, "\n" + "Ocurrió un error en la actualización de su perfil!", Toast.LENGTH_SHORT).show();
                            })
                    request?.add(jsonObjectRequest)
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