package com.asesoriasacademicasweb.asesoriasacademicas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import com.asesoriasacademicasweb.asesoriasacademicas.Controlador.GestionarClaseControlador
import com.asesoriasacademicasweb.asesoriasacademicas.Model.Clase
import com.asesoriasacademicasweb.asesoriasacademicas.Vista.IGestionarClaseVista

class PopupDetalleClaseActivity : AppCompatActivity(), IGestionarClaseVista {

    val iGestionarClaseControlador = GestionarClaseControlador(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popup_detalle_class)

        val intentListadoClases = Intent(this, GestionarClaseActivity::class.java)
        val idClase= getIntent().getStringExtra("id_clase")
        var clase = Clase()
        val materia: TextView? = findViewById<TextView>(R.id.txv_materia_detalle_clase)
        val tema: TextView? = findViewById<TextView>(R.id.txv_tema_detalle_clase)
        val fecha: TextView? = findViewById<TextView>(R.id.txv_fecha_detalle_clase)
        val hora: TextView? = findViewById<TextView>(R.id.txv_hora_detalle_clase)
        val duracion: TextView? = findViewById<TextView>(R.id.txv_duracion_detalle_clase)
        val estado: Switch = findViewById(R.id.swt_estado_solicitar_clase)

        clase = iGestionarClaseControlador.findClass(this, idClase.toString())
        materia?.setText(clase.materia)
        tema?.setText(clase.tema)

        if(iGestionarClaseControlador.getStatus(this, idClase.toString()).equals("activo")){
            estado.setChecked(true)
            estado.setTextOn("Activa")
        } else {
            estado.setChecked(false)
            estado.setTextOff("Inactiva")
        }

        fecha?.setText(clase.fecha)
        hora?.setText(clase.hora)
        duracion?.setText(clase.duracion)

        estado.setOnCheckedChangeListener{buttonView, isChecked ->
            if (isChecked){
                iGestionarClaseControlador.changeStatus(this, "activo", idClase.toString())
                Toast.makeText(this, "La clase se cambió a estado ACTIVO", Toast.LENGTH_SHORT).show()
            } else {
                iGestionarClaseControlador.changeStatus(this, "inactivo", idClase.toString())
                Toast.makeText(this, "La clase se cambió a estado INACTIVO", Toast.LENGTH_SHORT).show()

            }
            val email= getIntent().getStringExtra("email")
            intentListadoClases.putExtra("email", email);
            startActivity(intentListadoClases)
        }

        val btnBuscarClase =findViewById<Button>(R.id.btn_buscar_detalle_clase)
        btnBuscarClase.setOnClickListener {
            val intentEditarClase = Intent(this, EditarClaseActivity::class.java)
            val idBusqueda = clase.id.toString()
            intentEditarClase.putExtra("id_clase", idBusqueda)
            val email= getIntent().getStringExtra("email")
            intentEditarClase.putExtra("email", email);
            if (iGestionarClaseControlador.getStatus(this, idClase.toString()).equals("activo")) {
                startActivity(intentEditarClase)
            } else{
                Toast.makeText(this, "No es posible editar una clase con estado INACTIVO", Toast.LENGTH_SHORT).show()
            }
        }

        val btnListadoClases = findViewById<Button>(R.id.btn_volver_detalle_clase)
        btnListadoClases.setOnClickListener {
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