package com.asesoriasacademicasweb.asesoriasacademicas.Model

import java.util.regex.Pattern

class Login(email: String, password: String) : ILogin {
    override var email: String = email
        get() = field
        set(value) {field = value}
    override var password: String = password
        get() = field
        set(value) {field = value}

    override fun esValido(): Int {
        if (email.isEmpty()){
            return 0
        } else if (!Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$").matcher(email.trim()).matches()){
                return 1
        } else if (password.isEmpty()){
                return 2
        } else if (password.length < 8){
                return 3
        } else {
                return -1
        }
    }
}