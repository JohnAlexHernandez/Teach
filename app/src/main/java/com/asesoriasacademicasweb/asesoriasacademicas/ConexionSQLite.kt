package com.asesoriasacademicasweb.asesoriasacademicas

import android.database.sqlite.SQLiteOpenHelper
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import java.lang.Exception

class ConexionSQLite(context: Context, name: String, factory: Nothing?, version: Int): SQLiteOpenHelper(context,"bdasesoriasacademicas", null, 1) {

    var persona = "CREATE TABLE PERSONA(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, nombre TEXT, email TEXT, password TEXT);"
    override fun onCreate(db: SQLiteDatabase?) {
        try {
            db?.execSQL(persona)
        }catch (e: Exception)
        {
            System.out.println(e.message)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}