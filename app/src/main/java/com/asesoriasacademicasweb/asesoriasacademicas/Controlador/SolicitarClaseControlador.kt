package com.asesoriasacademicasweb.asesoriasacademicas.Controlador

import android.content.Context
import com.asesoriasacademicasweb.asesoriasacademicas.Model.Clase
import com.asesoriasacademicasweb.asesoriasacademicas.Vista.ISolicitarClaseVista

class SolicitarClaseControlador(var iSolicitarClaseVista: ISolicitarClaseVista) : ISolicitarClaseControlador {
    override fun onNewClass(context: Context, fecha: String, hora: String, duracion: String, materia: String, tema: String, inquietudes: String, _idEstudiante: Int) {
        val clase = Clase(
                0,
                fecha,
                hora,
                duracion,
                0,
                materia,
                tema,
                inquietudes,
                _idEstudiante
        )

        when(clase.esValido(context)){
            0 -> this.iSolicitarClaseVista.onLoginError("El campo materia no puede estar vacío")
            1 -> this.iSolicitarClaseVista.onLoginError("El campo tema no puede estar vacío")
            2 -> this.iSolicitarClaseVista.onLoginError("El campo inquietudes no puede estar vacío")
            3 -> this.iSolicitarClaseVista.onLoginError("El campo fecha no puede estar vacío")
            4 -> this.iSolicitarClaseVista.onLoginError("El campo hora no puede estar vacío")
            5 -> this.iSolicitarClaseVista.onLoginError("El campo duración no puede estar vacío")
            6 -> this.iSolicitarClaseVista.onLoginError("El campo duracion debe ser igual o inferior a 6 horas")
            -1 -> this.iSolicitarClaseVista.onLoginSuccess("Solicitud satisfactoria")
        }
    }
}