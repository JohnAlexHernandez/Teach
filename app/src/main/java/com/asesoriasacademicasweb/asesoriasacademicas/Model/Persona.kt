package com.asesoriasacademicasweb.asesoriasacademicas.Model

import android.content.Context
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

    override fun esValido(context: Context): Int {
        val obj = Modelo()
        return if (email.trim().isEmpty()){
            0
        } else if (!Pattern.compile("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$").matcher(email.trim()).matches()){
            1
        } else if (contrasenia.trim().isEmpty()){
            2
        } else if (!Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)\\S{8,15}$").matcher(contrasenia).matches()) {
            3
        } else if (obj.buscarPersona(context, email) == 0) {
            4
        } else if (obj.validarSesion(context, email, contrasenia) == 0) {
            5
        } else {
            -1
        }
    }

    override fun registroValido(context: Context, repetContrasenia: String): Int {
        val obj = Modelo()
        return if (nombre.trim().isEmpty()) {
            0
        } else if (email.trim().isEmpty()) {
            1
        } else if (!Pattern.compile("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$").matcher(email.trim()).matches()){
            2
        } else if (contrasenia.trim().isEmpty()){
            3
        } else if (!Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)\\S{8,15}$").matcher(contrasenia).matches()) {
            4
        } else if (obj.buscarPersona(context, email) == 1) {
            5
        } else if (!contrasenia.equals(repetContrasenia)) {
            6
        } else{
            -1
        }
    }

    override fun editarPerfil(context: Context, repetContrasenia: String): Int {
        return if (nombre.trim().isEmpty()) {
            0
        } else if (email.trim().isEmpty()) {
            1
        } else if (!Pattern.compile("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$").matcher(email.trim()).matches()){
            2
        } else if (telefono.trim().isEmpty()) {
            3
        } else if (direccion.trim().isEmpty()) {
            4
        } else if (contrasenia.trim().isEmpty()){
            5
        } else if (!Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)\\S{8,15}$").matcher(contrasenia).matches()) {
            6
        } else if (!contrasenia.equals(repetContrasenia)) {
            7
        } else{
            -1
        }
    }
}