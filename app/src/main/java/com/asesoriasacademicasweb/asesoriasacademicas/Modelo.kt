package com.asesoriasacademicasweb.asesoriasacademicas

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import java.lang.Exception

class Modelo {
    fun getConn(context: Context): SQLiteDatabase{
        var coon: ConexionSQLite = ConexionSQLite(context, "myBD", null, 1)
        var bd: SQLiteDatabase = coon.writableDatabase
        return bd
    }

    fun insertarPersona(context: Context, persona: Persona): Int {
        var res = 0
        var nombre = persona.nombre
        var email = persona.email
        var password = persona.password
        var sql = "INSERT INTO PERSONA (nombre, email, telefono, direccion, password) VALUES ('$nombre', '$email', '', '', '$password');"

        var db: SQLiteDatabase = this.getConn(context)
        try {
            db.execSQL(sql)
            res = 1
        } catch (e: Exception) {
            db.close()
            return res
        }
        return res
    }

    fun actualizarPersona(context: Context, persona: Persona): Int {
        var res = 0
        var id = persona.id
        var nombre = persona.nombre
        var email = persona.email
        var telefono = persona.telefono
        var direccion = persona.direccion
        var password = persona.password
        var sql = "UPDATE PERSONA SET nombre='$nombre', email='$email', telefono='$telefono', direccion='$direccion', password='$password' WHERE id_persona=$id;"

        var db: SQLiteDatabase = this.getConn(context)
        try {
            db.execSQL(sql)
            res = 1
        } catch (e: Exception) {
            db.close()
            return res
        }
        return res
    }

    fun buscarPersona(context: Context, email: String): Int{
        var res = 0
        var email = email
        var sql = "SELECT email FROM PERSONA WHERE email = '$email';"

        var db: SQLiteDatabase = this.getConn(context)
        try {
            var fila: Cursor = db.rawQuery(sql, null)
            if(fila.moveToFirst()){
                res = 1
                System.out.println("El usuario existe")
            }else{
                System.out.println("El usuario no existe ")
            }
        }catch (e: Exception)
        {
            db.close()
            return res
        }
        return  res
    }

    fun obtenerPersona(context: Context, email: String): Persona{
        var persona: Persona = Persona()
        var sql = "SELECT id_persona,nombre,email,telefono,direccion,password FROM PERSONA WHERE email = '$email';"

        var db: SQLiteDatabase = this.getConn(context)
        try {
            var fila: Cursor = db.rawQuery(sql, null)
            if(fila.moveToFirst()){
                persona.id = fila.getInt(0)
                persona.nombre = fila.getString(1)
                persona.email = fila.getString(2)
                persona.telefono = fila.getString(3)
                persona.direccion = fila.getString(4)
                persona.password = fila.getString(5)
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

    fun buscarTutoria(context: Context, id_tutoria: Int): Tutoria{
        var tutoria: Tutoria = Tutoria()
        var id = id_tutoria
        var sql = "SELECT id_tutoria, materia, tema, inquietudes FROM TUTORIA WHERE id_tutoria = $id;"

        var db: SQLiteDatabase = this.getConn(context)
        try {
            var fila: Cursor = db.rawQuery(sql, null)
            if(fila.moveToFirst()){
                tutoria.id = fila.getInt(0)
                tutoria.materia = fila.getString(1)
                tutoria.tema = fila.getString(2)
                tutoria.inquietudes = fila.getString(3)
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

    fun buscarClase(context: Context, id_clase: String): Clase{
        var clase: Clase = Clase()
        var id = id_clase
        var sql = "SELECT id_clase,fecha,hora,duracion,id_tutoria FROM CLASE WHERE id_clase = $id;"

        System.out.println(sql)
        var db: SQLiteDatabase = this.getConn(context)
        try {
            var fila: Cursor = db.rawQuery(sql, null)
            if(fila.moveToFirst()){
                clase.id = fila.getInt(0)
                clase.fecha = fila.getString(1)
                clase.hora = fila.getString(2)
                clase.duracion = fila.getString(3)
                clase.tutoria = buscarTutoria(context, fila.getInt(4))
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
        var res = 0
        var id_clase = clase.id
        var fecha = clase.fecha
        var hora = clase.hora
        var duracion = clase.duracion
        var id_tutoria = clase.tutoria.id
        var materia = tutoria.materia
        var tema = tutoria.tema
        var inquietudes = tutoria.inquietudes

        var sqlTutoria = "UPDATE  TUTORIA SET materia='$materia', tema='$tema', inquietudes='$inquietudes' WHERE id_tutoria=$id_tutoria;"
        var sqlClase = "UPDATE CLASE SET fecha='$fecha', hora='$hora', duracion='$duracion', id_tutoria='$id_tutoria' WHERE id_clase=$id_clase;"

        var db: SQLiteDatabase = this.getConn(context)
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

    fun validarSesion(context: Context, email: String, password: String): Int{
        var res = 0
        var email = email
        var password= password
        var sql = "SELECT email,password FROM PERSONA WHERE email = '$email';"

        var db: SQLiteDatabase = this.getConn(context)
        try {
            var fila: Cursor = db.rawQuery(sql, null)
            if(fila.moveToFirst()){
                if(password == fila.getString(1)) {
                    res = 1
                }
            }else{
                System.out.println("No existe el usuario")
            }
        }catch (e: Exception)
        {
            db.close()
            return res
        }
        return res
    }

    fun insertarClase(context: Context, tutoria: Tutoria, clase: Clase): Int {
        var res = 0
        var materia = tutoria.materia
        var tema = tutoria.tema
        var inquietudes = tutoria.inquietudes
        var fecha = clase.fecha
        var hora = clase.hora
        var duracion = clase.duracion
        var sqlTutoria = "INSERT INTO TUTORIA (materia, tema, inquietudes) VALUES ('$materia', '$tema', '$inquietudes');"
        var sqlClase = "INSERT INTO CLASE (fecha, hora, duracion, id_tutoria) VALUES ('$fecha', '$hora', '$duracion', (SELECT MAX(id_tutoria) FROM TUTORIA));"

        var db: SQLiteDatabase = this.getConn(context)
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

    fun listarClases(context: Context): ArrayList<Clase>{
        var listaClases: ArrayList<Clase> = ArrayList<Clase>()
        var sql = "SELECT id_clase,fecha,hora,duracion,id_tutoria FROM CLASE;"

        var db: SQLiteDatabase = this.getConn(context)
        try {
            var fila: Cursor = db.rawQuery(sql, null)
            if(fila.moveToFirst()){
                do{
                    var clase: Clase = Clase()
                    clase.id = fila.getInt(0)
                    clase.fecha = fila.getString(1)
                    clase.hora = fila.getString(2)
                    clase.duracion = fila.getString(3)
                    clase.tutoria = buscarTutoria(context, fila.getInt(4))
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
        var resDelete = 0
        var sql = "DELETE FROM CLASE WHERE id_clase = $id"
        var db: SQLiteDatabase = this.getConn(context)
        try {
            db.execSQL(sql)
            resDelete = 1
        }catch (e: Exception)
        {
            db.close()
            return resDelete
        }
        return resDelete
    }
}