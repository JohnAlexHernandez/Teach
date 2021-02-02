package com.asesoriasacademicasweb.asesoriasacademicas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.asesoriasacademicasweb.asesoriasacademicas.Controlador.GestionarClaseControlador
import com.asesoriasacademicasweb.asesoriasacademicas.Model.Clase
import com.asesoriasacademicasweb.asesoriasacademicas.Model.Modelo
import com.asesoriasacademicasweb.asesoriasacademicas.Vista.IGestionarClaseVista
import java.text.SimpleDateFormat
import java.util.*

class PopupDetalleClaseActivity : AppCompatActivity(), IGestionarClaseVista {

    val iGestionarClaseControlador = GestionarClaseControlador(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popup_detalle_class)

        val idClase= getIntent().getStringExtra("id_clase")
        var clase = Clase()
        val materia: TextView? = findViewById<TextView>(R.id.txv_materia_detalle_clase)
        val tema: TextView? = findViewById<TextView>(R.id.txv_tema_detalle_clase)
        val fecha: TextView? = findViewById<TextView>(R.id.txv_fecha_detalle_clase)
        val hora: TextView? = findViewById<TextView>(R.id.txv_hora_detalle_clase)
        val duracion: TextView? = findViewById<TextView>(R.id.txv_duracion_detalle_clase)

        clase = iGestionarClaseControlador.findClass(this, idClase.toString())
        materia?.setText(clase.materia)
        tema?.setText(clase.tema)
        val formatofecha = SimpleDateFormat("dd/mm/yyyy")
        val date: Date = formatofecha.parse(clase.fecha)
        fecha?.setText(formatofecha.format(date))

        val formatohora = SimpleDateFormat("h:mm")
        val hour: Date  = formatohora.parse(clase.hora)
        hora?.setText(formatohora.format(hour))
        duracion?.setText(clase.duracion)

        /*val btnEliminarClase = findViewById<Button>(R.id.btn_eliminar_detalle_clase)
        btnEliminarClase.setOnClickListener{
            val intentEliminarClase = Intent(this, GestionarClaseActivity::class.java)
            val obj = Modelo()

            val idBusqueda = clase.id

            if (iGestionarClaseControlador.deleteClass(this, idBusqueda) == 1) {
                Toast.makeText(this,"La clase ha sido eliminada correctamente", Toast.LENGTH_SHORT).show()
                val email= getIntent().getStringExtra("email")
                intentEliminarClase.putExtra("email", email);
                startActivity(intentEliminarClase)
            }else{
                Toast.makeText(this,"Error al elimniar la clase", Toast.LENGTH_SHORT).show()
            }
        }*/

        val btnBuscarClase =findViewById<Button>(R.id.btn_buscar_detalle_clase)
        btnBuscarClase.setOnClickListener {
            val intentEditarClase = Intent(this, EditarClaseActivity::class.java)

            val idBusqueda = clase.id.toString()
            intentEditarClase.putExtra("id_clase", idBusqueda)
            val email= getIntent().getStringExtra("email")
            intentEditarClase.putExtra("email", email);
            startActivity(intentEditarClase)
        }

        val btnListadoClases = findViewById<Button>(R.id.btn_volver_detalle_clase)
        btnListadoClases.setOnClickListener {
            val intentListadoClases = Intent(this, GestionarClaseActivity::class.java)
            val email= getIntent().getStringExtra("email")
            intentListadoClases.putExtra("email", email);
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