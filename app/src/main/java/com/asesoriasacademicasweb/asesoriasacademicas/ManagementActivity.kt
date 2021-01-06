package com.asesoriasacademicasweb.asesoriasacademicas

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class ManagementActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_management)

        var obj: Modelo = Modelo()
        var tutorias: ArrayList<Tutoria> = obj.listarClases(this)
        var listView: ListView? = findViewById(R.id.listView_class)
        var adaptador: ArrayAdapter<Tutoria> = ArrayAdapter<Tutoria>(this, R.layout.activity_listview, R.id.label, tutorias)
        listView?.setAdapter(adaptador)

        var btnAgregarClase = findViewById<Button>(R.id.btn_agregar_clase)
        btnAgregarClase.setOnClickListener{
            val intentClass = Intent(this, ClassActivity::class.java)
            startActivity(intentClass)
        }
    }
}