package com.asesoriasacademicasweb.asesoriasacademicas

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class ClassActivity : AppCompatActivity() {

    var fecha: EditText? = null
    var horaMinutos: EditText? = null
    var calendar = Calendar.getInstance()
    var anio = calendar.get(Calendar.YEAR)
    var mes = calendar.get(Calendar.MONTH)
    var dia = calendar.get(Calendar.DAY_OF_MONTH)

    var hora = calendar.get(Calendar.HOUR_OF_DAY)
    var minutos = calendar.get(Calendar.MINUTE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class)

        var btnGuardar = findViewById<Button>(R.id.btn_guardar_class)
        btnGuardar.setOnClickListener{
            var intentInsert = Intent(this, ManagementActivity::class.java)
            var obj: Modelo = Modelo()
            var clase: Clase = Clase()
            var tutoria: Tutoria = Tutoria()
            var resInsert = 0

            var materia: EditText? = findViewById(R.id.txt_materia_class)
            var tema: EditText? = findViewById(R.id.txt_tema_class)
            var inquietudes: EditText? = findViewById(R.id.txt_inquietudes_class)
            fecha = findViewById(R.id.txt_fecha_class)
            horaMinutos = findViewById(R.id.txt_hora_class)
            var duracion: EditText? = findViewById(R.id.txt_duracion_class)

            if(materia?.text.toString().trim().isEmpty()) {
                materia?.setError("El campo materia no puede estar vacío")
            }else if (tema?.text.toString().trim().isEmpty()) {
                tema?.setError("El campo tema no puede estar vacío")
            }else if (fecha?.text.toString().trim().isEmpty()){
                fecha?.setError("El campo fecha no puede estar vacío")
            }else if (horaMinutos?.text.toString().trim().isEmpty()){
                horaMinutos?.setError("El campo hora no puede estar vacío")
            }else {

                tutoria.materia = materia?.text.toString()
                tutoria.tema = tema?.text.toString()
                tutoria.inquietudes = inquietudes?.text.toString()
                clase.fecha = fecha?.text.toString()
                clase.hora = horaMinutos?.text.toString()
                clase.duracion = duracion?.text.toString()

                resInsert = obj.insertarClase(this, tutoria, clase)
            }
            if (resInsert == 1)
            {
                Toast.makeText(this,"Transaccion exitosa", Toast.LENGTH_SHORT).show()
                startActivity(intentInsert)
            }else{
                Toast.makeText(this,"Transaccion fallida", Toast.LENGTH_SHORT).show()
            }

        }

        var formatofecha: SimpleDateFormat = SimpleDateFormat("dd/mm/yyyy")
        fecha = findViewById(R.id.txt_fecha_class)
        fecha?.setOnClickListener{
            val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, anio, mes, dia ->
                var fechaCalendario = "" + dia + "/" + (mes + 1) + "/" + anio
                var date: Date  = formatofecha.parse(fechaCalendario)
                fecha?.setText(formatofecha.format(date))
            }, anio, mes, dia)
            datePickerDialog.show()
        }

        var formatohora: SimpleDateFormat = SimpleDateFormat("h:mm")
        horaMinutos = findViewById(R.id.txt_hora_class)
        horaMinutos?.setOnClickListener{
            val timePickerDialog = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener{ view, hora, minutos ->
                var horaReloj = "" + hora + ":" + minutos
                var date: Date  = formatohora.parse(horaReloj)
                horaMinutos?.setText(formatohora.format(date))
            }, hora, minutos, false)
            timePickerDialog.show()
        }

        var btnCancelarClase = findViewById<Button>(R.id.btn_cancelar_clase)
        btnCancelarClase.setOnClickListener{
            val intentClass = Intent(this, ManagementActivity::class.java)
            startActivity(intentClass)
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