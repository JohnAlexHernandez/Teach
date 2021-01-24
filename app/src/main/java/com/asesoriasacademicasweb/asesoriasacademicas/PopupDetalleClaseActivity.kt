package com.asesoriasacademicasweb.asesoriasacademicas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class PopupDetalleClaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popup_detalle_class)

        val idClase= getIntent().getStringExtra("id_clase")
        val obj = Modelo()
        var clase = Clase()
        val materia: TextView? = findViewById<TextView>(R.id.txv_materia_detalle_clase)
        val tema: TextView? = findViewById<TextView>(R.id.txv_tema_detalle_clase)
        val inquietudes: TextView? = findViewById<TextView>(R.id.txv_inquietudes_detalle_clase)
        val fecha: TextView? = findViewById<TextView>(R.id.txv_fecha_detalle_clase)
        val hora: TextView? = findViewById<TextView>(R.id.txv_hora_detalle_clase)
        val duracion: TextView? = findViewById<TextView>(R.id.txv_duracion_detalle_clase)

        clase = obj.buscarClase(this, "" + idClase)
        materia?.setText(clase.tutoria.materia)
        tema?.setText(clase.tutoria.tema)
        inquietudes?.setText(clase.tutoria.inquietudes)
        fecha?.setText(clase.fecha)
        hora?.setText(clase.hora)
        duracion?.setText(clase.duracion)

        val btnEliminarClase = findViewById<Button>(R.id.btn_eliminar_detalle_clase)
        btnEliminarClase.setOnClickListener{
            val intentEliminarClase = Intent(this, GestionarClaseActivity::class.java)
            val obj = Modelo()

            val idBusqueda = clase.id

            if (obj.eliminarClase(this, idBusqueda) == 1) {
                Toast.makeText(this,"La clase ha sido eliminada correctamente", Toast.LENGTH_SHORT).show()
                val email= getIntent().getStringExtra("email")
                intentEliminarClase.putExtra("email", "" + email);
                startActivity(intentEliminarClase)
            }else{
                Toast.makeText(this,"Error al elimniar la clase", Toast.LENGTH_SHORT).show()
            }
        }

        val btnBuscarClase =findViewById<Button>(R.id.btn_buscar_detalle_clase)
        btnBuscarClase.setOnClickListener {
            val intentEditarClase = Intent(this, EditarClaseActivity::class.java)

            val idBusqueda = clase.id.toString()
            intentEditarClase.putExtra("id_clase", "" + idBusqueda)
            val email= getIntent().getStringExtra("email")
            intentEditarClase.putExtra("email", "" + email);
            startActivity(intentEditarClase)
        }

        val btnListadoClases = findViewById<Button>(R.id.btn_volver_detalle_clase)
        btnListadoClases.setOnClickListener {
            val intentListadoClases = Intent(this, GestionarClaseActivity::class.java)
            val email= getIntent().getStringExtra("email")
            intentListadoClases.putExtra("email", "" + email);
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
            var email= getIntent().getStringExtra("email")
            intentEditarPerfil.putExtra("email", "" + email);
            startActivity(intentEditarPerfil)
        }
        return true
    }
}