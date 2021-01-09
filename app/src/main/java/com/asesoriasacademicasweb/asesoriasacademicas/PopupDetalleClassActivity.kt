package com.asesoriasacademicasweb.asesoriasacademicas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class PopupDetalleClassActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popup_detalle_class)

        var idClase= getIntent().getStringExtra("id_clase")
        var obj: Modelo = Modelo()
        var clase: Clase = Clase()
        var materia: TextView? = findViewById<TextView>(R.id.txt_materia_clase)
        var tema: TextView? = findViewById<TextView>(R.id.txt_tema_clase)
        var inquietudes: TextView? = findViewById<TextView>(R.id.txt_inquietudes_clase)
        var fecha: TextView? = findViewById<TextView>(R.id.txt_fecha_clase)
        var hora: TextView? = findViewById<TextView>(R.id.txt_hora_clase)
        var duracion: TextView? = findViewById<TextView>(R.id.txt_duracion_clase)

        clase = obj.buscarClase(this, "" + idClase)
        materia?.setText(clase.tutoria.materia)
        tema?.setText(clase.tutoria.tema)
        inquietudes?.setText(clase.tutoria.inquietudes)
        fecha?.setText(clase.fecha)
        hora?.setText(clase.hora)
        duracion?.setText(clase.duracion)

        var btnEliminarClase = findViewById<Button>(R.id.btn_eliminar_clase)
        btnEliminarClase.setOnClickListener{
            val intentEliminarClase = Intent(this, ManagementActivity::class.java)
            var obj: Modelo = Modelo()

            var idBusqueda = clase.id
            var resEliminar = obj.eliminarClase(this, idBusqueda)

            if (resEliminar == 1)
            {
                Toast.makeText(this,"Transacción exitosa", Toast.LENGTH_SHORT).show()
                startActivity(intentEliminarClase)
            }else{
                Toast.makeText(this,"Transacción fallida", Toast.LENGTH_SHORT).show()
            }
        }

        var btnBuscarClase =findViewById<Button>(R.id.btn_buscar_clase)
        btnBuscarClase.setOnClickListener {
            val intentEditarClase = Intent(this, EditarClaseActivity::class.java)
            var obj: Modelo = Modelo()

            var idBusqueda = clase.id.toString()
            intentEditarClase.putExtra("id_clase", "" + idBusqueda);
            startActivity(intentEditarClase)
        }

        var btnListadoClases = findViewById<Button>(R.id.btn_listado_clases)
        btnListadoClases.setOnClickListener {
            val intentListadoClases = Intent(this, ManagementActivity::class.java)
            startActivity(intentListadoClases)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_popup, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intentEditarPerfil = Intent(this, EditarPerfilActivity::class.java)
        if (item.itemId == R.id.editar_perfil){
            startActivity(intentEditarPerfil)
        }
        return true
    }
}