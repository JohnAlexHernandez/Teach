package com.asesoriasacademicasweb.asesoriasacademicas.Controlador

import com.asesoriasacademicasweb.asesoriasacademicas.Model.Login
import com.asesoriasacademicasweb.asesoriasacademicas.Vista.ILoginVista

class LoginControlador(var iLoginVista: ILoginVista) : ILoginControlador {

    override fun OnLogin(email: String, password: String) {
        var login: Login = Login(email, password)
        println(login.email)
        val bandera = login.esValido()

        if (bandera == 0){
            iLoginVista.OnLoginError("Por favor ingrese el Email")
        } else if (bandera == 1){
            iLoginVista.OnLoginError("Por favor ingrese un Email valido")
        } else if (bandera == 2){
            iLoginVista.OnLoginError("Por favor ingrese la contraseña")
        } else if (bandera == 3){
            iLoginVista.OnLoginError("La contraseña debe tener mas de 8 caracteres")
        } else if (bandera == -1){
            iLoginVista.OnLoginError("Login satisfactorio")
        }
    }
}