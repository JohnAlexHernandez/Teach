package com.asesoriasacademicasweb.asesoriasacademicas.Model

import android.content.Context

interface ITutoria {
    var materia: String
    var tema: String
    var inquietudes: String
    fun esValido(context: Context): Int
}