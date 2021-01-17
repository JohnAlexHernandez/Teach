package com.asesoriasacademicasweb.asesoriasacademicas.Controlador

import com.asesoriasacademicasweb.asesoriasacademicas.Vista.ILoginVista

interface ILoginControlador {
    fun OnLogin(email: String, password: String)
}