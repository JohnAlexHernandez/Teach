package com.asesoriasacademicasweb.asesoriasacademicas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.asesoriasacademicasweb.asesoriasacademicas.Controlador.ILoginControlador
import com.asesoriasacademicasweb.asesoriasacademicas.Controlador.LoginControlador
import com.asesoriasacademicasweb.asesoriasacademicas.Vista.ILoginVista

class LoginActivity : AppCompatActivity(), ILoginVista{

    var iLoginControlador: ILoginControlador? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        iLoginControlador = LoginControlador(this);

        var btnRegistration = findViewById<Button>(R.id.btn_registrarse_login)
        btnRegistration.setOnClickListener{
            val intentInsert = Intent(this, RegistrationActivity::class.java)
            startActivity(intentInsert)
        }

        var btnLogin = findViewById<Button>(R.id.btn_iniciar_sesion_login)
        btnLogin.setOnClickListener{
            val email: EditText? = findViewById(R.id.txt_email_login)
            val password: EditText? = findViewById(R.id.txt_password_login)
            val stringEmail = email?.text.toString().trim()
            val stringPass = password?.text.toString().trim()
            var obj: Modelo = Modelo()
            var banderaBuscar = 0
            var banderaValidar = 0
            val intentLogin = Intent(this, MainActivity::class.java)
            /*var intentLogin = Intent(this, MainActivity::class.java)
            var obj: Modelo = Modelo()
            var resBuscar = 0
            var resValidar = 0

            var email: EditText? = findViewById(R.id.txt_email_login)
            var password: EditText? = findViewById(R.id.txt_password_login)
            var emailBuscado = email?.text.toString()
            var passwordBuscada = password?.text.toString()

            if (email?.text.toString().trim().isEmpty()){
                email?.setError("El campo email no puede estar vacío")
            }else if (!Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$").matcher(email?.text.toString().trim()).matches()) {
                email?.setError("El campo email no es válido")
            }else if (password?.text.toString().trim().isEmpty()){
                password?.setError("El campo password no puede estar vacío")
            }else{
                resBuscar = obj.buscarPersona(this, emailBuscado)
                resValidar = obj.validarSesion(this, emailBuscado, passwordBuscada)
            }

            if (resBuscar == 1 && resValidar == 1)
            {
                intentLogin.putExtra("email", "" + emailBuscado);
                startActivity(intentLogin)
            }
            else{
                Toast.makeText(this, "Email y/o password no válidos", Toast.LENGTH_SHORT).show()
            }*/
            iLoginControlador?.OnLogin(stringEmail, stringPass)
            banderaBuscar = obj.buscarPersona(this, stringEmail)
            banderaValidar = obj.validarSesion(this, stringEmail, stringPass)
            if (banderaBuscar == 1 && banderaValidar == 1)
            {
                intentLogin.putExtra("email", "" + stringEmail);
                startActivity(intentLogin)
            }
        }
    }

    override fun OnLoginSuccess(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }

    override fun OnLoginError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }
}