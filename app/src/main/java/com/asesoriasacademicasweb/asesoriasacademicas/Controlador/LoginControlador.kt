package com.asesoriasacademicasweb.asesoriasacademicas.Controlador

import com.asesoriasacademicasweb.asesoriasacademicas.Model.Persona
import com.asesoriasacademicasweb.asesoriasacademicas.Vista.ILoginVista

class LoginControlador(var iLoginVista: ILoginVista) : ILoginControlador {

    override fun OnLogin(email: String, contrasenia: String) {
        val persona = Persona(
            "",
            email,
            "password",
            "",
            contrasenia
        )

        when (persona.esValido()) {
            0 -> this.iLoginVista.OnLoginError("Por favor ingrese el Email")
            1 -> this.iLoginVista.OnLoginError("Por favor ingrese un Email valido")
            2 -> this.iLoginVista.OnLoginError("Por favor ingrese la contraseña")
            3 -> this.iLoginVista.OnLoginError("La contraseña debe tener mas de 8 caracteres")
            -1 -> this.iLoginVista.OnLoginError("Login satisfactorio")
        }
    }
}