package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.content.contentValuesOf

import java.util.ArrayList

class DBHelper(context: Context) {
    fun onCreate(db: SQLiteDatabase){
        db.execSQL(SQL_CREATE_ENTERIES)
    }

    fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int){
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int){
        onUpgrade(db, oldVersion, newVersion)
    }

    @Throws(SQLiteConstraintException::class)
    fun insertCompany(company: CompanyModel):Boolean {
        val db = writableDatabase

        val values = ContentValues()
        values.put(DBContract.CompanyEntry.COLUMN_ID, company.id)
        values.put(DBContract.CompanyEntry.COLUMN_COMPANY, company.company)
        values.put(DBContract.CompanyEntry.PRICE, company.price)

        val newRowId = db.insert(DBContract.CompanyEntry.TABLE_NAME, null, values)

        return true
    }

    companion object{
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "datac.db"

        private val SQL_CREATE_ENTERIES =
            "CREATE TABLE" + DBContract.CompanyEntry.TABLE_NAME + "(" +
                    DBContract.CompanyEntry.COLUMN_ID + "TEXT PRIMARY KEY," +
                    DBContract.CompanyEntry.COLUMN_COMPANY + "TEXT," +
                    DBContract.CompanyEntry.PRICE + "TEXT)"

        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXIST" + DBContract.CompanyEntry.TABLE_NAME
    }
}