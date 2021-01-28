package com.asesoriasacademicasweb.asesoriasacademicas.Controlador

import android.content.Context
import com.asesoriasacademicasweb.asesoriasacademicas.Model.Persona

interface IRegistrarseControlador {
    fun onRegistry(context: Context, nombre: String, email: String, contrasenia: String, repetContrasenia: String)
    fun insertUser(context: Context, persona: Persona): Int
}