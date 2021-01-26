package com.asesoriasacademicasweb.asesoriasacademicas.Controlador

import android.content.Context

interface ISolicitarClaseControlador {
    fun onNewClass(context: Context, fecha: String, hora: String, duracion: String, materia: String, tema: String, inquietudes: String, _idEstudiante: Int)
}