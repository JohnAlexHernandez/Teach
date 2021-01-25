package com.asesoriasacademicasweb.asesoriasacademicas.Model

interface ITutoria {
    var id: Int
    var materia: String
    var tema: String
    var inquietudes: String
    fun esValido(): Int
}