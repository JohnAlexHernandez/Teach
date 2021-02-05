package com.asesoriasacademicasweb.asesoriasacademicas.Controlador

import android.content.Context
import android.view.Display
import com.asesoriasacademicasweb.asesoriasacademicas.Model.Clase
import com.asesoriasacademicasweb.asesoriasacademicas.Model.Estudiante
import com.asesoriasacademicasweb.asesoriasacademicas.Model.Modelo
import com.asesoriasacademicasweb.asesoriasacademicas.Vista.IGestionarClaseVista

class GestionarClaseControlador(var iGestionarClaseVista: IGestionarClaseVista) : IGestionarClaseControlador {
    override fun onDeleteClass(context: Context, idClase: String) {
    }

    override fun getClass(context: Context, idEstudiante: String): ArrayList<Clase> {
        val obj = Modelo()
        return obj.listarClases(context, idEstudiante)
    }

    override fun findClass(context: Context, idClase: String): Clase {
        val obj = Modelo()
        return obj.buscarClase(context, idClase)
    }

    override fun deleteClass(context: Context, idClase: Int): Int {
        val obj = Modelo()
        return obj.eliminarClase(context, idClase)
    }

    override fun getStudent(context: Context, email: String): Estudiante {
        val obj = Modelo()
        return obj.obtenerEstudiante(context, email)
    }

    override fun changeStatus(context: Context, estado: String, id: String): Int {
        val obj = Modelo()
        return obj.cambiarEstado(context, estado, id)
    }

    override fun getStatus(context: Context, idClase: String): String {
        val obj = Modelo()
        return obj.obtenerEstado(context, idClase)
    }
}