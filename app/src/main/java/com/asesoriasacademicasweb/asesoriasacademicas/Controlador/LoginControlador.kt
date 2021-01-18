package com.asesoriasacademicasweb.asesoriasacademicas.Controlador

import android.content.Context
import com.asesoriasacademicasweb.asesoriasacademicas.Model.Persona
import com.asesoriasacademicasweb.asesoriasacademicas.Vista.ILoginVista

class LoginControlador(var iLoginVista: ILoginVista) : ILoginControlador {

    override fun OnLogin(context: Context, email: String, contrasenia: String) {
        val persona = Persona(
            "",
            email,
            "password",
            "",
            contrasenia
        )

        when (persona.esValido(context)) {
            0 -> this.iLoginVista.OnLoginError("Por favor ingrese el Email")
            1 -> this.iLoginVista.OnLoginError("Por favor ingrese un Email valido")
            2 -> this.iLoginVista.OnLoginError("Por favor ingrese la contraseña")
            3 -> this.iLoginVista.OnLoginError("La contraseña debe tener mas de 8 caracteres")
            4 -> this.iLoginVista.OnLoginError("No existe un usuario con el email ingresado")
            5 -> this.iLoginVista.OnLoginError("Constraseña incorrecta")
            -1 -> this.iLoginVista.OnLoginError("Login satisfactorio")
        }
    }
}