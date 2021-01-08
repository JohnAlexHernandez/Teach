package com.asesoriasacademicasweb.asesoriasacademicas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class EditarClaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_clase)

        var idClase= getIntent().getStringExtra("id_clase")
        var obj: Modelo = Modelo()
        var clase: Clase = Clase()

        var materia: EditText? = findViewById<EditText>(R.id.txt_materia_editarClase)
        var tema: EditText? = findViewById<EditText>(R.id.txt_tema_editarClase)
        var inquietudes: EditText? = findViewById<EditText>(R.id.txt_inquietudes_editarClase)
        var fecha: EditText? = findViewById<EditText>(R.id.txt_fecha_editarClase)
        var hora: EditText? = findViewById<EditText>(R.id.txt_hora_editarClase)
        var duracion: EditText? = findViewById<EditText>(R.id.txt_duracion_editarClase)

        clase = obj.buscarClase(this, "" + idClase)
        materia?.setText(clase.tutoria.materia)
        tema?.setText(clase.tutoria.tema)
        inquietudes?.setText(clase.tutoria.inquietudes)
        fecha?.setText(clase.fecha)
        hora?.setText(clase.hora)
        duracion?.setText(clase.duracion)

        var btnEditarClase = findViewById<Button>(R.id.btn_editar_editarClase)
        btnEditarClase.setOnClickListener{
            Toast.makeText(this, "Clase Actualizada", Toast.LENGTH_SHORT).show()
        }

        var btnCancelarEditarClase = findViewById<Button>(R.id.btn_cancelar_editarClase)
        btnCancelarEditarClase.setOnClickListener{
            val intentCancelar = Intent(this, PopupDetalleClassActivity::class.java)
            intentCancelar.putExtra("id_clase", "" + idClase);
            startActivity(intentCancelar)
        }
    }
}