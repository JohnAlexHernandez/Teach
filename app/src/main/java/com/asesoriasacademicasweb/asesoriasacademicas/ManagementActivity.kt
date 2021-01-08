package com.asesoriasacademicasweb.asesoriasacademicas

import android.content.Intent
import android.os.Bundle
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AppCompatActivity


class ManagementActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_management)

        var obj: Modelo = Modelo()
        var clases: ArrayList<Clase> = obj.listarClases(this)
        var listView: ListView? = findViewById(R.id.listView_class)
        var adaptador: ArrayAdapter<Clase> = ArrayAdapter<Clase>(this, R.layout.activity_listview, R.id.label, clases)
        listView?.setAdapter(adaptador)

        listView?.setOnItemClickListener(OnItemClickListener { parent, view, position, id ->
            val intentPopupDetalleClass = Intent(this, PopupDetalleClassActivity::class.java)
            intentPopupDetalleClass.putExtra("id_clase", "" + clases[position].id);
            startActivity(intentPopupDetalleClass)
        })

        var btnAgregarClase = findViewById<Button>(R.id.btn_agregar_clase)
        btnAgregarClase.setOnClickListener{
            val intentClass = Intent(this, ClassActivity::class.java)
            startActivity(intentClass)
        }
    }
}