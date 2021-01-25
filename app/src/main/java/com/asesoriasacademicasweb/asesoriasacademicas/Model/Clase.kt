package com.asesoriasacademicasweb.asesoriasacademicas.Model

import android.content.Context

class Clase(_id: Int = 0, _fecha: String = "", _hora: String = "", _duracion: String = "", _idClase: Int = 0, _materia: String, _tema: String = "", _inquietudes: String = "") : IClase {
    override var id: Int = _id
        get() = field
        set(value) {
            field = value
        }
    override var fecha: String = _fecha
        get() = field
        set(value) {
            field = value
        }
    override var hora: String = _hora
        get() = field
        set(value) {
            field = hora
        }
    override var duracion: String = _duracion
        get() = field
        set(value) {
            field = value
        }
    override var tutoria: Tutoria = Tutoria(_idClase, _materia, _tema, _inquietudes)
        get() = field
        set(value) {
            field = value
        }

    init {
        this.fecha = _fecha
        this.hora = _hora
        this.duracion = _duracion
        this.tutoria = Tutoria(0, _materia, _tema, _inquietudes)
    }

    constructor(_fecha: String, _hora: String, _duracion: String, _materia: String, _tema: String, _inquietudes: String): this(0, _fecha, _hora, _duracion, 0,_materia, _tema, _inquietudes){
        this.fecha = _fecha
        this.hora = _hora
        this.duracion = _duracion
        this.tutoria = Tutoria(0, _materia, _tema, _inquietudes)
    }

    constructor(): this(0, "","","", 0, "", "", ""){
        this.fecha = ""
        this.hora = ""
        this.duracion = "_duracion"
        this.tutoria = Tutoria(0, "_materia", "_tema", "_inquietudes")
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