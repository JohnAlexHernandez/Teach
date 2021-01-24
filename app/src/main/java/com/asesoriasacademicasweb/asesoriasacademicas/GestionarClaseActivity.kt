package com.asesoriasacademicasweb.asesoriasacademicas

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AppCompatActivity
import com.asesoriasacademicasweb.asesoriasacademicas.Controlador.GestionarClaseControlador
import com.asesoriasacademicasweb.asesoriasacademicas.Controlador.SolicitarClaseControlador
import com.asesoriasacademicasweb.asesoriasacademicas.Vista.IGestionarClaseVista


class GestionarClaseActivity : AppCompatActivity(), IGestionarClaseVista {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gestionar_clase)

        val iGestionarClaseControlador = GestionarClaseControlador(this)
        val obj = Modelo()
        val clases: ArrayList<Clase> = obj.listarClases(this)
        val listView: ListView? = findViewById(R.id.listView_class)
        val adaptador: ArrayAdapter<Clase> = ArrayAdapter<Clase>(this, R.layout.activity_listview, R.id.label, clases)
        listView?.setAdapter(adaptador)

        listView?.setOnItemClickListener(OnItemClickListener { parent, view, position, id ->
            val intentPopupDetalleClass = Intent(this, PopupDetalleClaseActivity::class.java)
            intentPopupDetalleClass.putExtra("id_clase", "" + clases[position].id);
            val email= getIntent().getStringExtra("email")
            intentPopupDetalleClass.putExtra("email", "" + email);
            startActivity(intentPopupDetalleClass)
        })

        val btnAgregarClase = findViewById<Button>(R.id.btn_agregar_gestionar_clase)
        btnAgregarClase.setOnClickListener{
            val intentClass = Intent(this, SolicitarClaseActivity::class.java)
            val email= getIntent().getStringExtra("email")
            intentClass.putExtra("email", "" + email);
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
            intentEditarPerfil.putExtra("email", "" + email);
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