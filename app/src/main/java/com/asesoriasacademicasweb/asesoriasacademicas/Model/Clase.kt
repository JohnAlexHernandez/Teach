package com.asesoriasacademicasweb.asesoriasacademicas.Model

import android.content.Context

class Clase(id: Int, fecha: String, hora: String, duracion: String, idClase: Int, materia: String, tema: String, inquietudes: String) : IClase {
    override var id: Int = id
        get() = field
        set(value) {
            field = value
        }
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
    override var tutoria: Tutoria = Tutoria(idClase, materia, tema, inquietudes)
        get() = field
        set(value) {
            field = value
        }

    override fun toString(): String{
        return tutoria.materia + "\n" + tutoria.tema
    }

    override fun esValido(context: Context): Int {
        return if (tutoria.esValido() != -1){
            tutoria.esValido()
        } else if (fecha.trim().isEmpty()){
            0
        } else if (hora.trim().isEmpty()){
            1
        } else if (duracion.trim().isEmpty()){
            2
        } else if (duracion.toInt() < 1 || duracion.toInt() > 6){
            3
        } else {
            -1
        }
    }

}