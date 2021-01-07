package com.asesoriasacademicasweb.asesoriasacademicas

class Clase {
    var id = 0
        get() = field
        set(value) {
            field = value
        }
    var fecha = ""
        get() = field
        set(value) {
            field = value
        }
    var hora = ""
        get() = field
        set(value) {
            field = value
        }
    var duracion = ""
        get() = field
        set(value) {
            field = value
        }
    var tutoria = Tutoria()
        get() = field
        set(value) {
            field = value
        }
    override fun toString(): String{
        return tutoria.materia + "\n" + tutoria.tema
    }
}