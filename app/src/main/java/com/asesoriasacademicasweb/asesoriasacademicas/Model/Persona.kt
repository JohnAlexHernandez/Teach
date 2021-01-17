package com.asesoriasacademicasweb.asesoriasacademicas.Model

import com.asesoriasacademicasweb.asesoriasacademicas.LoginActivity
import com.asesoriasacademicasweb.asesoriasacademicas.Modelo
import java.util.regex.Pattern

class Persona(nombre: String, email: String, telefono: String, direccion: String, contrasenia: String) : IPersona{

    override var nombre = nombre
        get() = field
        set(value) {
            field = value
        }
    override var email = email
        get() = field
        set(value) {
            field = value
        }
    override var telefono = telefono
        get() = field
        set(value) {
            field = value
        }
    override var direccion = direccion
        get() = field
        set(value) {
            field = value
        }
    override var contrasenia = contrasenia
        get() = field
        set(value) {
            field = value
        }

    override fun esValido(): Int {
        var obj: Modelo = Modelo()
        if (email.isEmpty()){
            return 0
        } else if (!Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$").matcher(email.trim()).matches()){
            return 1
        } else if (contrasenia.isEmpty()){
            return 2
        } else if (contrasenia.length < 8) {
            return 3
        } else if (obj.buscarPersona(LoginActivity.this, email = email) == 1 && obj.validarSesion(this, email, contrasenia) == 1) {
            return -1
        } else {
            return -1
        }
    }
}