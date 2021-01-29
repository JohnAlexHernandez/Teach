package com.asesoriasacademicasweb.asesoriasacademicas.Controlador

import android.content.Context
import com.asesoriasacademicasweb.asesoriasacademicas.Model.Clase
import com.asesoriasacademicasweb.asesoriasacademicas.Model.Estudiante

interface ISolicitarClaseControlador {
    fun onNewClass(context: Context, fecha: String, hora: String, duracion: String, materia: String, tema: String, inquietudes: String, _idEstudiante: Int)
    fun getStudent(context: Context, email: String): Estudiante
    fun insertClass(context: Context, clase: Clase): Int
}