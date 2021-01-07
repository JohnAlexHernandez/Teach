package com.asesoriasacademicasweb.asesoriasacademicas

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
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
            Toast.makeText(this, ""+clases[position].id, Toast.LENGTH_SHORT).show()
            var resEliminar = obj.eliminarClase(this, clases[position].id)

            if (resEliminar == 1)
            {
                Toast.makeText(this,"Transacción exitosa", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"Transacción fallida", Toast.LENGTH_SHORT).show()
            }
        })


        var btnAgregarClase = findViewById<Button>(R.id.btn_agregar_clase)
        btnAgregarClase.setOnClickListener{
            val intentClass = Intent(this, ClassActivity::class.java)
            startActivity(intentClass)
        }

        /*var btnEliminarClase = findViewById<Button>(R.id.btn_eliminar_clase)
        btnEliminarClase.setOnClickListener{
            var obj: Modelo = Modelo()

            var id: EditText? = findViewById(R.id.)
            System.out.println(id)
            var idBusqueda = id?.text.toString()

            var resEliminar = obj.eliminarClase(this, idBusqueda)

            if (resEliminar == 1)
            {
                Toast.makeText(this,"Transacción exitosa", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"Transacción fallida", Toast.LENGTH_SHORT).show()
            }
        }*/
    }
}