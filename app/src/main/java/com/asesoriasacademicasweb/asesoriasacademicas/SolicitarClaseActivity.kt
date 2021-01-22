package com.asesoriasacademicasweb.asesoriasacademicas

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.asesoriasacademicasweb.asesoriasacademicas.Controlador.SolicitarClaseControlador
import com.asesoriasacademicasweb.asesoriasacademicas.Vista.ISolicitarClaseVista
import com.asesoriasacademicasweb.asesoriasacademicas.Model.Clase
import java.text.SimpleDateFormat
import java.util.*

class SolicitarClaseActivity : AppCompatActivity(), ISolicitarClaseVista {

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
        setContentView(R.layout.activity_solicitar_clase)

        val iSolicitarClaseControlador = SolicitarClaseControlador(this)
        val spnMateria: Spinner = findViewById(R.id.spn_materia)
        val spnTema: Spinner = findViewById(R.id.spn_tema)

        val materias = arrayOf(
                "Matemáticas3",
                "Matemáticas4",
                "Matemáticas5",
                "Matemáticas6",
                "Matemáticas7",
                "Matemáticas8",
                "Matemáticas9",
                "Matemáticas10",
                "Matemáticas11",
                "Geometría",
                "ProbabilidadyEstadística:",
                "Precálculo",
                "Física",
                "HtmlCssyJavaScript:",
                "AlgoritmiayProgramación",
                "SQL",
                "HerramientasOfimáticas"
        )

        val matematicas3 = arrayOf(
                "Los números naturales",
                "Composición y descomposición de números naturales",
                "Orden de números naturales",
                "Comparación y ordenación de números naturales >, <,  =",
                "Noción de fracciones",
                "Representación gráfica de fracciones",
                "Fracciones equivalentes",
                "Adición y sustracción de números naturales y sus propiedades",
                "Problemas de adición y sustracción",
                "Multiplicación y división de números naturales y sus propiedades",
                "Mitad, tercio y cuarto",
                "Problemas de multiplicación y división"
        )

        val matematicas4 = arrayOf(
                "Los números naturales",
                "Adición de números naturales llevando",
                "Sustracción de números naturales llevando",
                "Multiplicación de números naturales por 2 y 3 cifras",
                "División con ceros en el cociente",
                "División de naturales con divisores de 2 o más cifras",
                "Operaciones combinadas",
                "Los números fraccionarios",
                "Operaciones con fracciones",
                "Los números decimales",
                "Adición y sustracción de números decimales",
                "Multiplicación y división de números decimales",
                "Múltiplos y divisores"
        )

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, materias)
        val adapterMath3 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, matematicas3)
        val adapterMath4 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, matematicas4)
        spnMateria.adapter = adapter

        spnMateria.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val item: String = spnMateria.getItemAtPosition(position).toString()

                if (item.equals("Matemáticas3")){
                    spnTema.adapter = adapterMath3
                } else if (item.equals("Matemáticas4")){
                    spnTema.adapter = adapterMath4
                }
            }

        }

        val btnGuardar = findViewById<Button>(R.id.btn_guardar_solicitar_clase)
        btnGuardar.setOnClickListener{

            val obj = Modelo()
            val stringEmail= getIntent().getStringExtra("email")
            val materia: EditText? = findViewById(R.id.txt_materia_solicitar_clase)
            val tema: EditText? = findViewById(R.id.txt_tema_solicitar_clase)
            val inquietudes: EditText? = findViewById(R.id.txt_inquietudes__solicitar_clase)
            fecha = findViewById(R.id.txt_fecha_solicitar_clase)
            horaMinutos = findViewById(R.id.txt_hora_solicitar_clase)
            val duracion: EditText? = findViewById(R.id.txt_duracion_solicitar_clase)

            val stringMateria = materia?.text.toString().trim()
            val stringTema = tema?.text.toString().trim()
            val stringInquietudes = inquietudes?.text.toString().trim()
            val stringFecha = fecha?.text.toString().trim()
            val stringHoraMinutos = horaMinutos?.text.toString().trim()
            val stringDuracion = duracion?.text.toString().trim()

            val intentInsert = Intent(this, GestionarClaseActivity::class.java)
            iSolicitarClaseControlador.onNewClass(this, stringFecha, stringHoraMinutos, stringDuracion, stringMateria, stringTema, stringInquietudes)
            val clase = Clase(
                    stringFecha,
                    stringHoraMinutos,
                    stringDuracion,
                    stringMateria,
                    stringTema,
                    stringInquietudes
            )
            if(clase.esValido(this) == -1) {
                if (obj.insertarClase(this, clase) == 1) {
                    intentInsert.putExtra("email", "" + stringEmail)
                    startActivity(intentInsert)
                }
            }
            /*var intentInsert = Intent(this, GestionarClaseActivity::class.java)
            var obj: Modelo = Modelo()
            var clase: Clase = Clase()
            var tutoria: Tutoria = Tutoria()
            var resInsert = 0

            var materia: EditText? = findViewById(R.id.txt_materia_solicitar_clase)
            var tema: EditText? = findViewById(R.id.txt_tema_solicitar_clase)
            var inquietudes: EditText? = findViewById(R.id.txt_inquietudes__solicitar_clase)
            fecha = findViewById(R.id.txt_fecha_solicitar_clase)
            horaMinutos = findViewById(R.id.txt_hora_solicitar_clase)
            var duracion: EditText? = findViewById(R.id.txt_duracion_solicitar_clase)

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
            }*/

        }

        val formatofecha: SimpleDateFormat = SimpleDateFormat("dd/mm/yyyy")
        fecha = findViewById(R.id.txt_fecha_solicitar_clase)
        fecha?.setOnClickListener{
            val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, anio, mes, dia ->
                var fechaCalendario = "" + dia + "/" + (mes + 1) + "/" + anio
                var date: Date  = formatofecha.parse(fechaCalendario)
                fecha?.setText(formatofecha.format(date))
            }, anio, mes, dia)
            datePickerDialog.show()
        }

        val formatohora: SimpleDateFormat = SimpleDateFormat("h:mm")
        horaMinutos = findViewById(R.id.txt_hora_solicitar_clase)
        horaMinutos?.setOnClickListener{
            val timePickerDialog = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener{ view, hora, minutos ->
                val horaReloj = "" + hora + ":" + minutos
                val date: Date  = formatohora.parse(horaReloj)
                horaMinutos?.setText(formatohora.format(date))
            }, hora, minutos, false)
            timePickerDialog.show()
        }

        var btnCancelarClase = findViewById<Button>(R.id.btn_cancelar_solicitar_clase)
        btnCancelarClase.setOnClickListener{
            val intentClass = Intent(this, GestionarClaseActivity::class.java)
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

    override fun onLoginSuccess(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }

    override fun onLoginError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }
}