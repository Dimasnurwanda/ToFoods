package com.example.tofoods.DB

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        val DATABASE_NAME = "dbtofoods.db"
        val DATABASE_VERSION = 1
        private val SQL_CREATE_USER = "CREATE TABLE " + DBUserInfo.UserTable.TABLE_NAME + "("+DBUserInfo.UserTable.COL_EMAIL+" VARCHAR(200) PRIMARY KEY, " + DBUserInfo.UserTable.COL_PASS + " TEXT, " + DBUserInfo.UserTable.COL_FULLNAME + " TEXT, " + DBUserInfo.UserTable.COL_NOHP + " TEXT);"
        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DBUserInfo.UserTable.TABLE_NAME
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_USER)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    @Throws(SQLiteConstraintException::class)
    fun RegisterUser(emailin: String, passin: String, fullnamein: String, nohpin: String) {
        val db = writableDatabase
        val namatablet = DBUserInfo.UserTable.TABLE_NAME
        val emailt = DBUserInfo.UserTable.COL_EMAIL
        val nohpt = DBUserInfo.UserTable.COL_NOHP
        val passt = DBUserInfo.UserTable.COL_PASS
        val fullnamet = DBUserInfo.UserTable.COL_FULLNAME

        val sql = "INSERT INTO " + namatablet + " (" + emailt + ", " + passt + ", " + fullnamet + " , " + nohpt + ") VALUES('" + emailin + "', '" + passin + "', '" + fullnamein + "', '" + nohpin + "')"
        db.execSQL(sql)
    }

    @SuppressLint("Range")
    fun cekUser(emailin: String): String {
        val db = writableDatabase
        var cursor: Cursor? = null
        var jumlah = ""

        try {
            cursor = db.rawQuery("SELECT COUNT("+ DBUserInfo.UserTable.COL_EMAIL +") as jumlah FROM "+ DBUserInfo.UserTable.TABLE_NAME + " WHERE " + DBUserInfo.UserTable.COL_EMAIL + "=='" + emailin +"'" , null)
        } catch (e: android.database.SQLException) {
            db.execSQL(SQL_CREATE_USER)
            return "Tabel Dibuat"
        }
        if (cursor!!.moveToFirst()){
            jumlah = cursor.getString(cursor.getColumnIndex(DBUserInfo.UserTable.COL_JUMLAH))
        }
        return jumlah
    }

    @SuppressLint("Range")
    fun cekLogin(emailin: String, passin: String): String {
        val db = writableDatabase
        var cursor: Cursor? = null
        var jumlah = ""

        try {
            cursor = db.rawQuery("SELECT COUNT("+ DBUserInfo.UserTable.COL_EMAIL +") as jumlah FROM "+ DBUserInfo.UserTable.TABLE_NAME + " WHERE " + DBUserInfo.UserTable.COL_EMAIL + "=='" + emailin +"' AND " + DBUserInfo.UserTable.COL_PASS + "=='" + passin + "'" , null)
        } catch (e: android.database.SQLException) {
            db.execSQL(SQL_CREATE_USER)
            return "Tabel Dibuat"
        }

        if (cursor!!.moveToFirst()){
            jumlah = cursor.getString(cursor.getColumnIndex(DBUserInfo.UserTable.COL_JUMLAH))
        }

        return jumlah
    }

    // get user info to array
    @SuppressLint("Range")
    fun getUserInfo(): List<String> {
        val db = writableDatabase
        val cursor = db.rawQuery("SELECT * FROM "+ DBUserInfo.UserTable.TABLE_NAME, null)
        val list = ArrayList<String>()

        cursor.use { cursor ->
            while (cursor.moveToNext()) {
                list.add(cursor.getString(cursor.getColumnIndex(DBUserInfo.UserTable.COL_EMAIL)))
                list.add(cursor.getString(cursor.getColumnIndex(DBUserInfo.UserTable.COL_PASS)))
                list.add(cursor.getString(cursor.getColumnIndex(DBUserInfo.UserTable.COL_FULLNAME)))
                list.add(cursor.getString(cursor.getColumnIndex(DBUserInfo.UserTable.COL_NOHP)))
            }
        }

        return list
    }

}