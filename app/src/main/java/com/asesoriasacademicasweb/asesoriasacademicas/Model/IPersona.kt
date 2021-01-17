package com.asesoriasacademicasweb.asesoriasacademicas.Model

interface IPersona {
    var nombre : String
    var email : String
    var telefono : String
    var direccion : String
    var contrasenia : String
    fun esValido() : Int
}