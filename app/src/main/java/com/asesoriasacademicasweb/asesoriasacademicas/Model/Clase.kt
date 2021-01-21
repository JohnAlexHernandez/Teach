package com.asesoriasacademicasweb.asesoriasacademicas.Model

import android.content.Context
import com.asesoriasacademicasweb.asesoriasacademicas.Modelo
import com.asesoriasacademicasweb.asesoriasacademicas.Model.Tutoria
import java.util.regex.Pattern

class Clase(fecha: String, hora: String, duracion: String, materia: String, tema: String, inquietudes: String) : IClase {
    override var fecha: String = fecha
        get() = field
        set(value) {
            field = value
        }
    override var hora: String = hora
        get() = field
        set(value) {
            field = hora
        }
    override var duracion: String = duracion
        get() = field
        set(value) {
            field = value
        }
    override var tutoria: Tutoria = Tutoria(materia, tema, inquietudes)
        get() = field
        set(value) {
            field = value
        }

    override fun esValido(context: Context): Int {
        return if (tutoria.esValido() != -1){
            tutoria.esValido()
        } else if (fecha.trim().isEmpty()){
            0
        } else if (hora.trim().isEmpty()){
            1
        } else if (duracion.toInt() <= 0 && duracion.toInt() >= 7){
            2
        } else {
            -1
        }
    }

}