package com.asesoriasacademicasweb.asesoriasacademicas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.util.regex.Pattern

class ClassActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class)

        var btnGuardar = findViewById<Button>(R.id.btn_guardar_class)
        btnGuardar.setOnClickListener{
            var intentInsert = Intent(this, ClassActivity::class.java)
            var obj: Modelo = Modelo()
            var clase: Clase = Clase()
            var tutoria: Tutoria = Tutoria()
            var resInsert = 0

            var materia: EditText? = findViewById(R.id.txt_materia_class)
            var tema: EditText? = findViewById(R.id.txt_tema_class)
            var inquietudes: EditText? = findViewById(R.id.txt_inquietudes_class)
            var fecha: EditText? = findViewById(R.id.txt_fecha_class)
            var hora: EditText? = findViewById(R.id.txt_hora_class)
            var duracion: EditText? = findViewById(R.id.txt_duracion_class)

            tutoria.materia = materia?.text.toString()
            tutoria.tema = tema?.text.toString()
            tutoria.inquietudes = inquietudes?.text.toString()
            clase.fecha = fecha?.text.toString()
            clase.hora = hora?.text.toString()
            clase.duracion = duracion?.text.toString()

            resInsert = obj.insertarClase(this, tutoria, clase)
            if (resInsert == 1)
            {
                Toast.makeText(this,"Clase guardada", Toast.LENGTH_SHORT).show()
                startActivity(intentInsert)
            }else{
                Toast.makeText(this,"No se guardó la clase", Toast.LENGTH_SHORT).show()
            }

        }
    }
}