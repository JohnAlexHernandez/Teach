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
        var sql = "INSERT INTO PERSONA (nombre, email, password) VALUES ('$nombre', '$email', '$password');"

        System.out.println(sql)
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

    fun buscarTutoria(context: Context, id_tutoria: Int): Tutoria{
        var tutoria: Tutoria = Tutoria()
        var id = id_tutoria
        var sql = "SELECT id_tutoria, materia, tema, inquietudes FROM TUTORIA WHERE id_tutoria = '$id';"

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

    fun validarSesion(context: Context, email: String, password: String): Int{
        var res = 0
        var email = email
        var password= password
        var sql = "SELECT email,password FROM PERSONA WHERE email = '$email';"

        var db: SQLiteDatabase = this.getConn(context)
        try {
            var fila: Cursor = db.rawQuery(sql, null)
            if(fila.moveToFirst()){
                System.out.println(password)
                System.out.println(fila.getShort(1))
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