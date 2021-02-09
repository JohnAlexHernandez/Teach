package com.asesoriasacademicasweb.asesoriasacademicas.Controlador

import android.content.Context
import android.graphics.ColorSpace
import com.asesoriasacademicasweb.asesoriasacademicas.Model.Estudiante
import com.asesoriasacademicasweb.asesoriasacademicas.Model.Modelo
import com.asesoriasacademicasweb.asesoriasacademicas.Model.Persona
import com.asesoriasacademicasweb.asesoriasacademicas.Vista.ILoginVista
import com.asesoriasacademicasweb.asesoriasacademicas.Vista.IRegistrarseVista

class RegistrarseControlador(var iRegistrarseVista: IRegistrarseVista) : IRegistrarseControlador {
    override fun onRegistry(context: Context, nombre: String, email: String, contrasenia: String, reperContrasenia: String): Int {
        val estudiante = Estudiante(
                nombre,
                email,
                contrasenia
        )

        when(estudiante.registroValido(context, reperContrasenia)){
            0 -> this.iRegistrarseVista.onLoginError("El campo nombre no puede estar vacío")
            1 -> this.iRegistrarseVista.onLoginError("El campo email no puede estar vacío")
            2 -> this.iRegistrarseVista.onLoginError("El campo email no es válido")
            3 -> this.iRegistrarseVista.onLoginError("El campo contraseña no puede estar vacío")
            4 -> this.iRegistrarseVista.onLoginError("El campo contraseña no es válido. Debe contener entre 8 y 15 caracteres, utiliza al menos 1 letra Mayúscula, 1 letra minúscula y un número")
            5 -> this.iRegistrarseVista.onLoginError("Ya existe un usuario con el email ingresado")
            6 -> this.iRegistrarseVista.onLoginError("La contraseña no coincide, verifica e intenta nuevamente")
            7 -> this.iRegistrarseVista.onLoginError("El campo nombre no es válido")
            -1 -> this.iRegistrarseVista.onLoginSuccess("Registro satisfactorio")
        }
        return estudiante.registroValido(context, reperContrasenia)
    }

    override fun insertUser(context: Context, persona: Persona): Int {
        val obj = Modelo()
        return obj.insertarPersona(context, persona)
    }
}