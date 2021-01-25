package com.asesoriasacademicasweb.asesoriasacademicas.Controlador

import android.content.Context

interface ISolicitarClaseControlador {
    fun onNewClass(context: Context, id: Int, fecha: String, hora: String, duracion: String, idClase: Int, materia: String, tema: String, inquietudes: String)
}