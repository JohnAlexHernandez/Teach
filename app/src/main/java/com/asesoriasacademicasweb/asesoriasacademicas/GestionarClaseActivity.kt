package com.asesoriasacademicasweb.asesoriasacademicas

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.asesoriasacademicasweb.asesoriasacademicas.Controlador.GestionarClaseControlador
import com.asesoriasacademicasweb.asesoriasacademicas.Model.Clase
import com.asesoriasacademicasweb.asesoriasacademicas.Model.Estudiante
import com.asesoriasacademicasweb.asesoriasacademicas.Vista.IGestionarClaseVista
import org.json.JSONException
import org.json.JSONObject


class GestionarClaseActivity : AppCompatActivity(), IGestionarClaseVista {

    val iGestionarClaseControlador = GestionarClaseControlador(this)

    var request: RequestQueue? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gestionar_clase)

        request = Volley.newRequestQueue(this)

        var estudiante = Estudiante()
        val stringEmail= getIntent().getStringExtra("email")
        estudiante = iGestionarClaseControlador.getStudent(this,stringEmail.toString())
        //val clases: ArrayList<Clase> = iGestionarClaseControlador.getClass(this, estudiante.id.toString())

        val idEstudiante = estudiante.id.toString()
        var url = "https://webserviceasesoriasacademicas.000webhostapp.com/listar_clases.php?idEstudiante=$idEstudiante"
        url = url.replace(" ","%20")
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET,url,null,
                Response.Listener { response ->
                    try {
                        val clases: ArrayList<Clase> = ArrayList<Clase>()
                        var jsonObjet : JSONObject
                        val jsonArray = response.optJSONArray("listaClases")
                        for (i in 0 until jsonArray.length()) {
                            var clase = Clase()
                            jsonObjet = jsonArray.getJSONObject(i)
                            clase.id = jsonObjet.getInt("id_clase")
                            clase.fecha = jsonObjet.getString("fecha")
                            clase.hora = jsonObjet.getString("hora")
                            clase.duracion = jsonObjet.getString("duracion")
                            clase.materia = jsonObjet.getString("materia")
                            clase.tema = jsonObjet.getString("tema")
                            clase.inquietudes = jsonObjet.getString("inquietudes")
                            clase.estado = jsonObjet.getString("estado")
                            clases.add(clase)
                        }
                        val listView: ListView? = findViewById(R.id.listView_class)
                        val adaptador: ArrayAdapter<Clase> = ArrayAdapter(this, R.layout.activity_listview, R.id.label, clases)

                        if (adaptador.count != 0) {
                            listView?.setAdapter(adaptador)

                            listView?.setOnItemClickListener(OnItemClickListener { parent, view, position, id ->
                                val intentPopupDetalleClass = Intent(this, PopupDetalleClaseActivity::class.java)
                                intentPopupDetalleClass.putExtra("id_clase", clases[position].id.toString());
                                val email= getIntent().getStringExtra("email")
                                intentPopupDetalleClass.putExtra("email", email);
                                startActivity(intentPopupDetalleClass)
                            })
                        } else{
                            val mensajeClasesVacio: ArrayList<String> = ArrayList()
                            mensajeClasesVacio.add("No tiene clases")
                            val adaptadorEmpty: ArrayAdapter<String> = ArrayAdapter<String>(this, R.layout.activity_listview, R.id.label_empty, mensajeClasesVacio)
                            listView?.setAdapter(adaptadorEmpty)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                },
                Response.ErrorListener { error ->
                    Toast.makeText(this, "\n" + "Error de registro!", Toast.LENGTH_SHORT).show();
                })
        request?.add(jsonObjectRequest)

        val btnAgregarClase = findViewById<Button>(R.id.btn_agregar_gestionar_clase)
        btnAgregarClase.setOnClickListener{
            val intentClass = Intent(this, SolicitarClaseActivity::class.java)
            val email= getIntent().getStringExtra("email")
            intentClass.putExtra("email", email);
            startActivity(intentClass)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_popup, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intentEditarPerfil = Intent(this, EditarPerfilActivity::class.java)
        if (item.itemId == R.id.editar_perfil){
            val email= getIntent().getStringExtra("email")
            intentEditarPerfil.putExtra("email", email);
            startActivity(intentEditarPerfil)
        }

        val intentLogout = Intent(this, LoginActivity::class.java)
        if (item.itemId == R.id.logout){
            val email= getIntent().getStringExtra("email")
            intentLogout.putExtra("email", email);
            startActivity(intentLogout)
        }
        return true
    }

    override fun onManagementSuccess(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }

    override fun onManagementError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }
}