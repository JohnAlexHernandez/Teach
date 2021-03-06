package com.asesoriasacademicasweb.asesoriasacademicas.Controlador

import android.content.Context
import com.asesoriasacademicasweb.asesoriasacademicas.Model.Clase
import com.asesoriasacademicasweb.asesoriasacademicas.Model.Estudiante

interface IGestionarClaseControlador {
    fun onDeleteClass(context: Context, idClase: String)
    fun getClass(context: Context, idEstudiante: String): ArrayList<Clase>
    fun findClass(context: Context, idClase: String): Clase
    fun deleteClass(context: Context, idClase: Int): Int
    fun getStudent(context: Context, email: String): Estudiante
    fun changeStatus(context: Context, estado: String, idClase: String): Int
    fun getStatus(context: Context, idClase: String): String
}