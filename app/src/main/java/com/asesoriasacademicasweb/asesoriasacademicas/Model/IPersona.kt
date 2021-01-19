package com.asesoriasacademicasweb.asesoriasacademicas.Model

import android.content.Context

interface IPersona {
    var nombre : String
    var email : String
    var telefono : String
    var direccion : String
    var contrasenia : String
    fun esValido(context: Context): Int
    fun registroValido(context: Context): Int
}