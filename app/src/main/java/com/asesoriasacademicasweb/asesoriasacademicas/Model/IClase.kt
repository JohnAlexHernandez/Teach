package com.asesoriasacademicasweb.asesoriasacademicas.Model

import android.content.Context

interface IClase {
    var id: Int
    var fecha: String
    var hora: String
    var duracion: String
    var tutoria: Tutoria
    fun esValido(context: Context): Int
}