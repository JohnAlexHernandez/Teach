package com.asesoriasacademicasweb.asesoriasacademicas.Controlador

import android.content.Context
import com.asesoriasacademicasweb.asesoriasacademicas.Model.Clase
import com.asesoriasacademicasweb.asesoriasacademicas.Vista.IGestionarClaseVista

class GestionarClaseControlador(var iGestionarClaseVista: IGestionarClaseVista) : IGestionarClaseControlador {
    override fun onDeleteClass(context: Context, idClase: String) {
    }
}