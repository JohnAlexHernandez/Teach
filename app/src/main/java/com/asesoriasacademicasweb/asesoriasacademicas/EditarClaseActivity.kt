package com.asesoriasacademicasweb.asesoriasacademicas

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.asesoriasacademicasweb.asesoriasacademicas.Controlador.EditarClaseControlador
import com.asesoriasacademicasweb.asesoriasacademicas.Model.Clase
import com.asesoriasacademicasweb.asesoriasacademicas.Model.Estudiante
import com.asesoriasacademicasweb.asesoriasacademicas.Model.Modelo
import com.asesoriasacademicasweb.asesoriasacademicas.Model.Tutoria
import com.asesoriasacademicasweb.asesoriasacademicas.Vista.IEditarClaseVista
import org.json.JSONException
import java.text.SimpleDateFormat
import java.util.*


class EditarClaseActivity : AppCompatActivity(), IEditarClaseVista {

    var fecha: EditText? = null
    var horaMinutos: EditText? = null
    var calendar = Calendar.getInstance()
    var anio = calendar.get(Calendar.YEAR)
    var mes = calendar.get(Calendar.MONTH)
    var dia = calendar.get(Calendar.DAY_OF_MONTH)

    var hora = calendar.get(Calendar.HOUR_OF_DAY)
    var minutos = calendar.get(Calendar.MINUTE)

    val iEditarClaseControlador = EditarClaseControlador(this)
    var request: RequestQueue? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_clase)

        request = Volley.newRequestQueue(this)

        var idClase= getIntent().getStringExtra("id_clase")
        var obj = Modelo()
        var clase = Clase()
        var tutoria = Tutoria()

        val spnMateria: Spinner? = findViewById(R.id.spn_materia_edit)
        val spnTema: Spinner? = findViewById(R.id.spn_tema_edit)
        var inquietudes: EditText? = findViewById<EditText>(R.id.txt_inquietudes_editar_clase)
        var fecha: EditText? = findViewById<EditText>(R.id.txt_fecha_editar_clase)
        var tiempo: EditText? = findViewById<EditText>(R.id.txt_hora_editar_clase)
        var duracion: EditText? = findViewById<EditText>(R.id.txt_duracion_editar_clase)

        //clase = obj.buscarClase(this, idClase.toString())

        var url = "https://webserviceasesoriasacademicas.000webhostapp.com/cargar_clase.php?idClase=$idClase"
        url = url.replace(" ","%20")
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET,url,null,
                Response.Listener { response ->
                    try {
                        val jsonArray = response.optJSONArray("class")
                        val jsonObjet = jsonArray.getJSONObject(0)
                        clase.id = jsonObjet.getInt("id_clase")
                        clase.fecha = jsonObjet.getString("fecha")
                        clase.hora = jsonObjet.getString("hora")
                        clase.duracion = jsonObjet.getString("duracion")
                        clase.materia = jsonObjet.getString("materia")
                        clase.tema = jsonObjet.getString("tema")
                        clase.inquietudes = jsonObjet.getString("inquietudes")
                        clase.estado = jsonObjet.getString("estado")

                        inquietudes?.setText(clase.inquietudes)
                        fecha?.setText(clase.fecha)
                        tiempo?.setText(clase.hora)
                        duracion?.setText(clase.duracion)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                },
                Response.ErrorListener { error ->
                    Toast.makeText(this, "\n" + "Ocurrió un error cargando la infomación de su clase!", Toast.LENGTH_SHORT).show();
                })
        request?.add(jsonObjectRequest)

        var materiaSeleccionada = ""
        var temaSeleccionado = ""

        val materias = arrayOf(
                "Matemáticas 3",
                "Matemáticas 4",
                "Matemáticas 5",
                "Matemáticas 6",
                "Matemáticas 7",
                "Matemáticas 8",
                "Matemáticas 9",
                "Matemáticas 10",
                "Matemáticas 11",
                "Geometría",
                "Álgebra",
                "Trigonometría",
                "Probabilidad y Estadística",
                "Precálculo",
                "Cálculo",
                "Física",
                "Html Css y JavaScript",
                "Algoritmia y Programación",
                "Modelado UML",
                "Patrones de diseño de software",
                "GIT y GITHUB",
                "Sistemas Operativos GNU/Linux",
                "Base de datos",
                "Metodologías Ágiles SCRUM",
                "Herramientas Ofimáticas"
        )

        val matematicas3 = arrayOf(
                "Los números naturales",
                "Composición y descomposición",
                "Orden de números naturales",
                "Comparación y ordenación >, <,  =",
                "Noción de fracciones",
                "Representación gráfica de fracciones",
                "Fracciones equivalentes",
                "Adición, sustracción y sus propiedades",
                "Problemas de adición y sustracción",
                "Multiplicación, división y sus propiedades",
                "Mitad, tercio y cuarto",
                "Problemas de multiplicación y división"
        )

        val matematicas4 = arrayOf(
                "Los números naturales",
                "Adición llevando",
                "Sustracción llevando",
                "Multiplicación por 2 y 3 cifras",
                "División con ceros en el cociente",
                "División con divisores de 2 o más cifras",
                "Operaciones combinadas",
                "Los números fraccionarios",
                "Operaciones con fracciones",
                "Los números decimales",
                "Adición y sustracción",
                "Multiplicación y división",
                "Múltiplos y divisores"
        )

        val matematicas5 = arrayOf(
                "Los números naturales",
                "Adición y sustracción",
                "Multiplicación",
                "División con ceros en el cociente",
                "División con divisores de 2 y más cifras",
                "Operaciones combinadas",
                "Los números decimales",
                "Lectura y escritura de fracciones",
                "Comparación de fracciones",
                "Operaciones con fracciones",
                "Adición y sustracción",
                "Multiplicación y división",
                "Los números Romanos"
        )

        val matematicas6 = arrayOf(
                "Romano, binario y decimal",
                "Los números naturales",
                "Operaciones con números naturales",
                "Potenciación, radicación y logaritmación",
                "Múltiplos y divisores",
                "Descomposición factorial",
                "Los números enteros",
                "La recta numérica(Plano cartesiano)",
                "Operaciones con números enteros",
                "Ecuaciones simples",
                "Los números fraccionarios",
                "Operaciones con números fraccionarios",
                "Problemas verbales"
        )

        val matematicas7 = arrayOf(
                "Números enteros, polinomios aritméticos",
                "Expresiones con signos de agrupación",
                "Los números racionales",
                "Operaciones con numeros racionales",
                "Problemas verbales",
                "Los números decimales",
                "Operaciones con números decimales",
                "Planteamiento y resolución de problemas",
                "Proporcionalidad",
                "Magnitudes directamente proporcionales",
                "Magnitudes inversamente proporcionales",
                "Regla de tres simple",
                "Problemas de aplicación"
        )

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, materias)
        val adapterMath3 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, matematicas3)
        val adapterMath4 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, matematicas4)
        val adapterMath5 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, matematicas5)
        val adapterMath6 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, matematicas6)
        val adapterMath7 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, matematicas7)
        spnMateria?.adapter = adapter

        spnMateria?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                materiaSeleccionada = spnMateria?.getItemAtPosition(position).toString()

                if (materiaSeleccionada.equals("Matemáticas 3")){
                    spnTema?.adapter = adapterMath3
                } else if (materiaSeleccionada.equals("Matemáticas 4")){
                    spnTema?.adapter = adapterMath4
                } else if (materiaSeleccionada.equals("Matemáticas 5")){
                    spnTema?.adapter = adapterMath5
                } else if (materiaSeleccionada.equals("Matemáticas 6")){
                    spnTema?.adapter = adapterMath6
                } else if (materiaSeleccionada.equals("Matemáticas 7")){
                    spnTema?.adapter = adapterMath7
                }
            }
        }

        spnTema?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                temaSeleccionado = spnTema?.getItemAtPosition(position).toString()
            }
        }

        var btnEditarClase = findViewById<Button>(R.id.btn_guardar_editar_clase)
        btnEditarClase.setOnClickListener{

            var estudiante = Estudiante()
            val stringEmail= getIntent().getStringExtra("email")
            val inquietudes: EditText? = findViewById(R.id.txt_inquietudes_editar_clase)
            fecha = findViewById(R.id.txt_fecha_editar_clase)
            horaMinutos = findViewById(R.id.txt_hora_editar_clase)
            val duracion: EditText? = findViewById(R.id.txt_duracion_editar_clase)

            val stringMateria = materiaSeleccionada
            val stringTema = temaSeleccionado
            val stringInquietudes = inquietudes?.text.toString().trim()
            val stringFecha = fecha?.text.toString().trim()
            val stringHoraMinutos = horaMinutos?.text.toString().trim()
            val stringDuracion = duracion?.text.toString().trim()

            if(iEditarClaseControlador.onEditClass(this, stringFecha, stringHoraMinutos, stringDuracion, stringMateria, stringTema, stringInquietudes, clase.estado, estudiante.id) == -1) {
                tutoria.materia = stringMateria
                tutoria.tema = stringTema
                tutoria.inquietudes = stringInquietudes
                tutoria.estado = clase.estado
                clase.fecha = stringFecha
                clase.hora = stringHoraMinutos
                clase.duracion = stringDuracion

                if (iEditarClaseControlador.updateClase(this, tutoria, clase) == 1) {
                    val intentDetalleClase = Intent(this, PopupDetalleClaseActivity::class.java)
                    Toast.makeText(this, "Transaccion exitosa", Toast.LENGTH_SHORT).show()
                    var idBusqueda = clase.id.toString()
                    intentDetalleClase.putExtra("id_clase", idBusqueda);
                    val email = getIntent().getStringExtra("email")
                    var idClase = clase.id
                    var estadoClase = clase.estado
                    var url = "https://webserviceasesoriasacademicas.000webhostapp.com/editar_clase.php?materia=$stringMateria&tema=$stringTema" +
                            "&inquietudes=$stringInquietudes&estado=$estadoClase&fecha=$stringFecha&hora=$stringHoraMinutos&duracion=$stringDuracion&idClase=$idClase"
                    url = url.replace(" ","%20")
                    url = url.replace("#","%23")
                    url = url.replace("-","%2D")
                    url = url.replace("á","%C3%A1")
                    url = url.replace("é","%C3%A9")
                    url = url.replace("í","%C3%AD")
                    url = url.replace("ó","%C3%B3")
                    url = url.replace("ú","%C3%BA")
                    url = url.replace("°","%C2%B0")
                    println(url)
                    val jsonObjectRequest = JsonObjectRequest(Request.Method.GET,url,null,
                            Response.Listener { response ->
                                if (response.getString("success") == "1"){
                                    intentDetalleClase.putExtra("email", email);
                                    startActivity(intentDetalleClase)
                                } else if(response.getString("error") == "0") {
                                    Toast.makeText(this, "\n" + "Ocurrió un error en la actualización de su clase!", Toast.LENGTH_SHORT).show()
                                }
                            },
                            Response.ErrorListener { error ->
                                Toast.makeText(this, "\n" + "Ocurrió un error en la actualización de su clase!", Toast.LENGTH_SHORT).show();
                            })
                    request?.add(jsonObjectRequest)
                } else {
                    Toast.makeText(this, "Transaccion fallida", Toast.LENGTH_SHORT).show()
                }
            }
        }

        var formatofecha: SimpleDateFormat = SimpleDateFormat("dd/mm/yyyy")
        val lanzadorFecha: ImageView = findViewById(R.id.img_fecha_editar_clase)
        fecha = findViewById(R.id.txt_fecha_editar_clase)
        lanzadorFecha.setOnClickListener{
            val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, anio, mes, dia ->
                var fechaCalendario = "" + dia + "/" + (mes + 1) + "/" + anio
                var date: Date  = formatofecha.parse(fechaCalendario)
                fecha?.setText(formatofecha.format(date))
            }, anio, mes, dia)
            datePickerDialog.show()
        }

        var formatohora: SimpleDateFormat = SimpleDateFormat("h:mm")
        val lanzadorTiempo: ImageView = findViewById(R.id.img_hora_editar_clase)
        horaMinutos = findViewById(R.id.txt_hora_editar_clase)
        lanzadorTiempo.setOnClickListener{
            val timePickerDialog = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener{ view, hora, minutos ->
                var am_pm = ""
                if (hora < 12){
                    am_pm = " AM"
                } else {
                    am_pm = " PM"
                }
                var horaReloj = "" + hora + ":" + minutos
                var date: Date  = formatohora.parse(horaReloj)
                horaMinutos?.setText(formatohora.format(date) + am_pm)
            }, hora, minutos, false)
            timePickerDialog.show()
        }

        var btnCancelarEditarClase = findViewById<Button>(R.id.btn_cancelar_editar_clase)
        btnCancelarEditarClase.setOnClickListener{
            val intentCancelar = Intent(this, PopupDetalleClaseActivity::class.java)
            var idBusqueda = clase.id.toString()
            val email= getIntent().getStringExtra("email")
            intentCancelar.putExtra("email", email);
            intentCancelar.putExtra("id_clase", idBusqueda);
            startActivity(intentCancelar)
        }

        val btnDisminurDuracion = findViewById<ImageView>(R.id.img_disminuir_duracion_editar_clase)
        btnDisminurDuracion.setOnClickListener{
            if (duracion?.text.toString().isEmpty()){
                duracion?.setText("1")
            } else {
                var intDuracion = duracion?.text.toString().trim().toInt()
                var nuevaDuracion = intDuracion - 1
                if (nuevaDuracion >= 1) {
                    duracion?.setText(nuevaDuracion.toString())
                } else {
                    duracion?.setText("1")
                    Toast.makeText(this, "No es posible definir una clase menor a 1 hora", Toast.LENGTH_SHORT).show()
                }
            }
        }

        val btnAumentarDuracion = findViewById<ImageView>(R.id.img_aumentar_duracion_editar_clase)
        btnAumentarDuracion.setOnClickListener{
            if (duracion?.text.toString().isEmpty()){
                duracion?.setText("1")
            } else {
                var intDuracion = duracion?.text.toString().trim().toInt()
                var nuevaDuracion = intDuracion + 1
                if(nuevaDuracion <= 6) {
                    duracion?.setText(nuevaDuracion.toString())
                } else {
                    duracion?.setText("6")
                    Toast.makeText(this, "No es posible definir una clase mayor a 6 horas", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_popup, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intentEditarPerfil = Intent(this, EditarPerfilActivity::class.java)
        if (item.itemId == R.id.editar_perfil){
            val email= getIntent().getStringExtra("email")
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

    override fun onLoginSuccess(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }

    override fun onLoginError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }
}