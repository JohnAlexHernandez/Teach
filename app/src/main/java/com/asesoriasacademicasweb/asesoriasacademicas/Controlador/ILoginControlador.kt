package com.asesoriasacademicasweb.asesoriasacademicas.Controlador

import android.content.Context


interface ILoginControlador {
    fun OnLogin(context: Context, email: String, password: String)
}