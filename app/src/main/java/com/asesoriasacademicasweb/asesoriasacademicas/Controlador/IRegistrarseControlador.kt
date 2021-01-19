package com.asesoriasacademicasweb.asesoriasacademicas.Controlador

import android.content.Context

interface IRegistrarseControlador {
    fun onRegistry(context: Context, nombre: String, email: String, contrasenia: String)
}