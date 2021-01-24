package com.asesoriasacademicasweb.asesoriasacademicas.Controlador

import android.content.Context

interface IGestionarClaseControlador {
    fun onDeleteClass(context: Context, idClase: String)
}