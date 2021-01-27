package com.asesoriasacademicasweb.asesoriasacademicas.Model

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.asesoriasacademicasweb.asesoriasacademicas.ConexionSQLite
import java.lang.Exception

class Modelo {
    fun getConn(context: Context): SQLiteDatabase{
        val coon: ConexionSQLite = ConexionSQLite(context, "myBD", null, 1)
        val bd: SQLiteDatabase = coon.writableDatabase
        return bd
    }

    fun insertarPersona(context: Context, persona: Persona): Int {
        var bandera = 0
        val nombre = persona.nombre
        val email = persona.email
        val password = persona.contrasenia
        val sqlPersona = "INSERT INTO PERSONA (nombre, email, telefono, direccion, password) VALUES ('$nombre', '$email', '', '', '$password');"
        val sqlEstudiante = "INSERT INTO ESTUDIANTE (id_persona) SELECT MAX(id_persona) FROM PERSONA;"

        val db: SQLiteDatabase = this.getConn(context)
        try {
            db.execSQL(sqlPersona)
            db.execSQL(sqlEstudiante)
            bandera = 1
        } catch (e: Exception) {
            db.close()
            return bandera
        }
        return bandera
    }

    fun actualizarPersona(context: Context, persona: Persona): Int {
        var bandera = 0
        val nombre = persona.nombre
        val email = persona.email
        val telefono = persona.telefono
        val direccion = persona.direccion
        val password = persona.contrasenia
        val sql = "UPDATE PERSONA SET nombre='$nombre', email='$email', telefono='$telefono', direccion='$direccion', password='$password' WHERE email='$email';"

        val db: SQLiteDatabase = this.getConn(context)
        try {
            db.execSQL(sql)
            bandera = 1
        } catch (e: Exception) {
            db.close()
            return bandera
        }
        return bandera
    }

    fun buscarPersona(context: Context, email: String): Int{
        var bandera = 0
        val email = email
        val sql = "SELECT email FROM PERSONA WHERE email = '$email';"

        val db: SQLiteDatabase = this.getConn(context)
        try {
            var fila: Cursor = db.rawQuery(sql, null)
            if(fila.moveToFirst()){
                bandera = 1
                System.out.println("El usuario existe")
            }else{
                System.out.println("El usuario no existe ")
            }
        }catch (e: Exception)
        {
            db.close()
            return bandera
        }
        return  bandera
    }

    fun obtenerPersona(context: Context, email: String): Persona {
        val persona = Persona()
        val email = email
        val sql = "SELECT nombre,email,telefono,direccion,password FROM PERSONA WHERE email = '$email';"

        val db: SQLiteDatabase = this.getConn(context)
        try {
            var fila: Cursor = db.rawQuery(sql, null)
            if(fila.moveToFirst()){
                persona.nombre = fila.getString(0)
                persona.email = fila.getString(1)
                persona.telefono = fila.getString(2)
                persona.direccion = fila.getString(3)
                persona.contrasenia = fila.getString(4)
            }else{
                System.out.println("La persona no existe ")
            }
        }catch (e: Exception)
        {
            db.close()
            return persona
        }
        return persona
    }

    fun obtenerEstudiante(context: Context, email: String): Estudiante {
        val estudiante = Estudiante()
        val email = email
        val sql = "SELECT id_estudiante,nombre,email,telefono,direccion,password FROM PERSONA INNER JOIN ESTUDIANTE WHERE PERSONA.id_persona = ESTUDIANTE.id_persona AND email = '$email';"

        val db: SQLiteDatabase = this.getConn(context)
        try {
            var fila: Cursor = db.rawQuery(sql, null)
            if(fila.moveToFirst()){
                estudiante.id = fila.getInt(0)
                estudiante.nombre = fila.getString(1)
                estudiante.email = fila.getString(2)
                estudiante.telefono = fila.getString(3)
                estudiante.direccion = fila.getString(4)
                estudiante.contrasenia = fila.getString(5)
            }else{
                System.out.println("La persona no existe ")
            }
        }catch (e: Exception)
        {
            db.close()
            return estudiante
        }
        return estudiante
    }

    fun buscarTutoria(context: Context, id_tutoria: Int): Tutoria {
        val tutoria = Tutoria()
        val id = id_tutoria
        val sql = "SELECT materia, tema, inquietudes FROM TUTORIA WHERE id_tutoria = $id;"

        val db: SQLiteDatabase = this.getConn(context)
        try {
            val fila: Cursor = db.rawQuery(sql, null)
            if(fila.moveToFirst()){
                tutoria.materia = fila.getString(0)
                tutoria.tema = fila.getString(1)
                tutoria.inquietudes = fila.getString(2)
            }else{
                System.out.println("La tutoria no existe ")
            }
        }catch (e: Exception)
        {
            db.close()
            return tutoria
        }
        return tutoria
    }

    fun buscarClase(context: Context, id_clase: String): Clase {
        val clase = Clase()
        val id = id_clase
        val sql = "SELECT id_clase,fecha,hora,duracion,materia,tema,inquietudes FROM CLASE INNER JOIN TUTORIA WHERE TUTORIA.id_tutoria = CLASE.id_tutoria AND id_clase = $id;"

        val db: SQLiteDatabase = this.getConn(context)
        try {
            val fila: Cursor = db.rawQuery(sql, null)
            if(fila.moveToFirst()){
                clase.id = fila.getInt(0)
                clase.fecha = fila.getString(1)
                clase.hora = fila.getString(2)
                clase.duracion = fila.getString(3)
                clase.materia = fila.getString(4)
                clase.tema = fila.getString(5)
                clase.inquietudes = fila.getString(6)
            }else{
                System.out.println("La clase no existe ")
            }
        }catch (e: Exception)
        {
            db.close()
            return clase
        }
        return clase
    }

    fun actualizarClase(context: Context, tutoria: Tutoria, clase: Clase): Int {
        var bandera = 0
        val id_clase = clase.id
        val fecha = clase.fecha
        val hora = clase.hora
        val duracion = clase.duracion
        val id_tutoria = clase.id
        val materia = tutoria.materia
        val tema = tutoria.tema
        val inquietudes = tutoria.inquietudes

        val sqlTutoria = "UPDATE  TUTORIA SET materia='$materia', tema='$tema', inquietudes='$inquietudes' WHERE id_tutoria=$id_tutoria;"
        val sqlClase = "UPDATE CLASE SET fecha='$fecha', hora='$hora', duracion='$duracion', id_tutoria='$id_tutoria' WHERE id_clase=$id_clase;"

        val db: SQLiteDatabase = this.getConn(context)
        try {
            db.execSQL(sqlTutoria)
            db.execSQL(sqlClase)
            bandera = 1
        } catch (e: Exception) {
            db.close()
            return bandera
        }
        return bandera
    }

    fun validarSesion(context: Context, email: String, password: String): Int{
        var bandera = 0
        val email = email
        val password= password
        val sql = "SELECT email,password FROM PERSONA WHERE email = '$email';"

        val db: SQLiteDatabase = this.getConn(context)
        try {
            val fila: Cursor = db.rawQuery(sql, null)
            if(fila.moveToFirst()){
                if(password == fila.getString(1)) {
                    bandera = 1
                }
            }else{
                println("No existe el usuario")
            }
        }catch (e: Exception)
        {
            db.close()
            return bandera
        }
        return bandera
    }

    fun insertarClase(context: Context, clase: Clase): Int {
        var res = 0
        val materia = clase.materia
        val tema = clase.tema
        val inquietudes = clase.inquietudes
        val fecha = clase.fecha
        val hora = clase.hora
        val duracion = clase.duracion
        val idEstudiante = clase.idEstudiante
        val sqlTutoria = "INSERT INTO TUTORIA (materia, tema, inquietudes) VALUES ('$materia', '$tema', '$inquietudes');"
        val sqlClase = "INSERT INTO CLASE (fecha, hora, duracion, id_tutoria, id_estudiante) VALUES ('$fecha', '$hora', '$duracion', (SELECT MAX(id_tutoria) FROM TUTORIA), $idEstudiante);"

        val db: SQLiteDatabase = this.getConn(context)
        try {
            db.execSQL(sqlTutoria)
            db.execSQL(sqlClase)
            res = 1
        } catch (e: Exception) {
            db.close()
            return res
        }
        return res
    }

    fun listarClases(context: Context, idEstudiante: String): ArrayList<Clase>{
        val listaClases: ArrayList<Clase> = ArrayList<Clase>()
        val sql = "SELECT id_clase,fecha,hora,duracion,materia,tema,inquietudes FROM CLASE INNER JOIN TUTORIA WHERE TUTORIA.id_tutoria = CLASE.id_tutoria AND id_Estudiante = $idEstudiante;"

        val db: SQLiteDatabase = this.getConn(context)
        try {
            val fila: Cursor = db.rawQuery(sql, null)
            if(fila.moveToFirst()){
                do{
                    val clase = Clase()
                    clase.id = fila.getInt(0)
                    clase.fecha = fila.getString(1)
                    clase.hora = fila.getString(2)
                    clase.duracion = fila.getString(3)
                    clase.materia = fila.getString(4)
                    clase.tema = fila.getString(5)
                    clase.inquietudes = fila.getString(6)
                    listaClases.add(clase)
                }while (fila.moveToNext())
            }
        }catch (e: Exception)
        {
            db.close()
        }
        return listaClases
    }

    fun eliminarClase(context: Context, id: Int): Int {
        var bandera = 0
        val sql = "DELETE FROM CLASE WHERE id_clase = $id"
        val db: SQLiteDatabase = this.getConn(context)

        try {
            db.execSQL(sql)
            bandera = 1
        }catch (e: Exception)
        {
            db.close()
            return bandera
        }
        return bandera
    }
}