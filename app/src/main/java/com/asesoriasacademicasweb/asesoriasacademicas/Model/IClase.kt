package com.asesoriasacademicasweb.asesoriasacademicas.Model

import android.content.Context
import com.asesoriasacademicasweb.asesoriasacademicas.Tutoria

interface IClase {
    var fecha: String
    var hora: String
    var duracion: String
    var tutoria: com.asesoriasacademicasweb.asesoriasacademicas.Model.Tutoria
    fun esValido(context: Context): Int
}