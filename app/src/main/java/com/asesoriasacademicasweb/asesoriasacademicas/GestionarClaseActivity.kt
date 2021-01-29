package com.asesoriasacademicasweb.asesoriasacademicas

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AppCompatActivity
import com.asesoriasacademicasweb.asesoriasacademicas.Controlador.GestionarClaseControlador
import com.asesoriasacademicasweb.asesoriasacademicas.Model.Clase
import com.asesoriasacademicasweb.asesoriasacademicas.Model.Estudiante
import com.asesoriasacademicasweb.asesoriasacademicas.Model.Modelo
import com.asesoriasacademicasweb.asesoriasacademicas.Vista.IGestionarClaseVista


class GestionarClaseActivity : AppCompatActivity(), IGestionarClaseVista {

    val iGestionarClaseControlador = GestionarClaseControlador(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gestionar_clase)

        var estudiante = Estudiante()
        val stringEmail= getIntent().getStringExtra("email")
        estudiante = iGestionarClaseControlador.getStudent(this,stringEmail.toString())
        val clases: ArrayList<Clase> = iGestionarClaseControlador.getClass(this, estudiante.id.toString())
        val listView: ListView? = findViewById(R.id.listView_class)
        val adaptador: ArrayAdapter<Clase> = ArrayAdapter<Clase>(this, R.layout.activity_listview, R.id.label, clases)
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
        return true
    }

    override fun onManagementSuccess(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }

    override fun onManagementError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }
}