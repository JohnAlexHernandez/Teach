package com.asesoriasacademicasweb.asesoriasacademicas.Model

interface ILogin {
    var email: String
    var password: String
    fun esValido(): Int
}