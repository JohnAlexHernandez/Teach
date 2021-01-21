package com.asesoriasacademicasweb.asesoriasacademicas.Model

interface ITutoria {
    var materia: String
    var tema: String
    var inquietudes: String
    fun esValido(): Int
}