package com.asesoriasacademicasweb.asesoriasacademicas

import android.database.sqlite.SQLiteOpenHelper
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import java.lang.Exception

class ConexionSQLite(context: Context, name: String, factory: Nothing?, version: Int): SQLiteOpenHelper(context,"bdasesoriasacademicas", null, 1) {

    var persona = "CREATE TABLE PERSONA(id_persona INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, nombre TEXT, email TEXT, password TEXT);"
    var tutoria = "CREATE TABLE TUTORIA(id_tutoria INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, materia TEXT, tema TEXT, inquietudes TEXT);"
    var clase = "CREATE TABLE CLASE(id_clase INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, fecha DATE, hora TIME, duracion INTEGER, id_tutoria, FOREIGN KEY (id_tutoria) REFERENCES TUTORIA (id_tutoria));"
    override fun onCreate(db: SQLiteDatabase?) {
        try {
            db?.execSQL(persona)
            db?.execSQL(tutoria)
            db?.execSQL(clase)
        }catch (e: Exception)
        {
            System.out.println(e.message)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}