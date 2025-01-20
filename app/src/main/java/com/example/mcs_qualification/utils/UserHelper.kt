package com.example.mcs_qualification.utils

import android.content.Context
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import com.example.mcs_qualification.model.User

class UserHelper(private val context: Context) {
    private var database_helper: DatabaseHelper? = null

    private var db: SQLiteDatabase? = null

    @Throws(SQLException::class)
    fun open() {
        database_helper = DatabaseHelper(context)
        db = database_helper!!.writableDatabase
    }

    @Throws(SQLException::class)
    fun close() {
        database_helper!!.close()
    }

    fun login(name: String, pass: String): User? {
        var name = name
        val query =
            "Select * from User where username = '$name' and password = '$pass'"
        val cursor = db!!.rawQuery(query, null)
        cursor.moveToFirst()

        val user: User

        val id: Int
        var username: String
        val password: String
        val email: String
        val gender: Int

        if (cursor.count > 0) {
            id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
            name = cursor.getString(cursor.getColumnIndexOrThrow("username"))
            password = cursor.getString(cursor.getColumnIndexOrThrow("password"))
            email = cursor.getString(cursor.getColumnIndexOrThrow("email"))
            gender = cursor.getInt(cursor.getColumnIndexOrThrow("gender"))

            user = User(id, name, password, email, gender)
            cursor.close()
        } else {
            cursor.close()
            return null
        }

        return user
    }

    fun register(username: String, password: String, email: String, gender: Int) {
        val query =
            "Insert into User (username, password, email, gender) values ('$username', '$password', '$email', $gender)"
        db!!.execSQL(query)
    }
}
