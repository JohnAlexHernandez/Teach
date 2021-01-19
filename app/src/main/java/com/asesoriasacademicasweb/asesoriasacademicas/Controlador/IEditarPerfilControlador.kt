package com.asesoriasacademicasweb.asesoriasacademicas.Controlador

import android.content.Context

interface IEditarPerfilControlador {
    fun onEditProfile(context: Context, nombre: String, email: String, telefono: String, direccion: String, contrasenia: String, repetContrasenia: String)
}