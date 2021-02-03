package com.asesoriasacademicasweb.asesoriasacademicas.Model

import android.content.Context
import java.text.SimpleDateFormat
import java.util.*

class Clase(_id: Int = 0, _fecha: String = "", _hora: String = "", _duracion: String = "", _materia: String = "", _tema: String = "", _inquietudes: String = "", _idEstudiante: Int = 0) : IClase, ITutoria {
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
            field = value
        }
    override var duracion: String = _duracion
        get() = field
        set(value) {
            field = value
        }
    override var materia: String = _materia
        get() = field
        set(value) {
            field = value
        }
    override var tema: String = _tema
        get() = field
        set(value) {
            field = value
        }
    override var inquietudes: String = _inquietudes
        get() = field
        set(value) {
            field = value
        }
    override var idEstudiante: Int = _idEstudiante
        get() = field
        set(value) {
            field = value
        }

    init {
        this.id = _id
        this.fecha = _fecha
        this.hora = _hora
        this.duracion = _duracion
        this.materia = _materia
        this.tema = _tema
        this.inquietudes = _inquietudes
        this.idEstudiante = _idEstudiante
    }

    constructor(_fecha: String, _hora: String, _duracion: String, _materia: String, _tema: String, _inquietudes: String, _idEstudiante: Int): this(0, _fecha, _hora, _duracion,_materia, _tema, _inquietudes, _idEstudiante){
        this.fecha = _fecha
        this.hora = _hora
        this.duracion = _duracion
        this.materia = _materia
        this.tema = _tema
        this.inquietudes = _inquietudes
        this.idEstudiante = _idEstudiante
    }

    constructor(): this(0, "","","", "", "", "", 0){
        this.id = 0
        this.fecha = ""
        this.hora = ""
        this.duracion = ""
        this.materia = ""
        this.tema = ""
        this.inquietudes = ""
        this.idEstudiante = 0
    }

    override fun toString(): String{
        return materia + "\n" + tema
    }

    override fun esValido(context: Context): Int {
        val formatoFecha = SimpleDateFormat("dd/MM/yyyy")
        val formatoHora = SimpleDateFormat("h:mm")
        val fechaActual = formatoFecha.format(Date())
        val horaActual = formatoHora.format(Date())
        return if (materia.trim().isEmpty()){
            0
        } else if (tema.trim().isEmpty()){
            1
        } else if (inquietudes.trim().isEmpty()){
            2
        } else if (fecha.trim().isEmpty()){
            3
        } else if (fecha.trim() < fechaActual){
            4
        } else if (hora.trim().isEmpty()){
            5
        } else if (hora.trim() < horaActual){
            6
        } else if (duracion.trim().isEmpty()){
            7
        } else if (duracion.toInt() < 1 || duracion.toInt() > 6){
            8
        } else {
            -1
        }
    }

}