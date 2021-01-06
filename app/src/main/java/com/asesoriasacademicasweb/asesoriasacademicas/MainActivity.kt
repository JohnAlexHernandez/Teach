package com.asesoriasacademicasweb.asesoriasacademicas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnListarClase = findViewById<Button>(R.id.btn_listar_clase)
        btnListarClase.setOnClickListener{
            val intentClass = Intent(this, ManagementActivity::class.java)
            startActivity(intentClass)
        }
    }
}