package com.asesoriasacademicasweb.asesoriasacademicas.Model

class Tutoria(materia: String, tema: String, inquietudes: String) : ITutoria {
    override var materia: String = materia
        get() = field
        set(value) {
            field = value
        }
    override var tema: String = tema
        get() =  field
        set(value) {
            field = value
        }
    override var inquietudes: String = inquietudes
        get() = field
        set(value) {
            field = value
        }

    override fun esValido(): Int {
        return if (materia.trim().isEmpty()){
            3
        } else if (tema.trim().isEmpty()){
            4
        } else if (inquietudes.trim().isEmpty()){
            5
        } else {
            -1
        }
    }
}