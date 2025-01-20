package com.example.mcs_qualification.utils


import android.content.Context
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.mcs_qualification.model.TransactionHeader
import java.sql.Date

class TransactionHeaderHelper(private val context: Context) {
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

    fun getTransactionHeader(userID: Int): ArrayList<TransactionHeader> {
        val transactionHeaderList: ArrayList<TransactionHeader> = ArrayList<TransactionHeader>()

        val query =
            "Select * from TransactionHeader where user_id = $userID order by transaction_date desc"
        val cursor = db!!.rawQuery(query, null)
        cursor.moveToFirst()

        var transactionHeader: TransactionHeader

        var id: Int
        var user_id: Int
        var date: Date?

        if (cursor.count > 0) {
            do {
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                user_id = cursor.getInt(cursor.getColumnIndexOrThrow("user_id"))
                val dateString = cursor.getString(cursor.getColumnIndexOrThrow("transaction_date"))
                Log.d("HEHEH", "getTransactionHeader: $dateString")
                date = Date.valueOf(dateString)

                transactionHeader = TransactionHeader(id, user_id, date)
                transactionHeaderList.add(transactionHeader)

                cursor.moveToNext()
            } while (!cursor.isAfterLast)
        }

        cursor.close()
        return transactionHeaderList
    }

    fun addTransactionHeader(user_id: Int, date: Date) {
        val query = "INSERT INTO TransactionHeader (user_id, transaction_date) VALUES (?, ?)"

        try {
            db!!.execSQL(query, arrayOf<Any>(user_id, date.toString()))
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            db!!.close()
        }
    }

    val lastId: Int
        get() {
            val query = "Select * from TransactionHeader order by id desc"
            val cursor = db!!.rawQuery(query, null)
            cursor.moveToFirst()

            var id = -1
            if (cursor.count > 0) {
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
            }

            cursor.close()
            return id
        }
}
