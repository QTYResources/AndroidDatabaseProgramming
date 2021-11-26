package jwei.apps.dataforandroid.ch1

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class SQLiteHelper(
    private val context: Context
): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE $TABLE_NAME ( $UID INTEGER PRIMARY KEY AUTOINCREMENT, $NAME VARCHAR(255));")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        Log.w(TAG, "Upgrading database from version $oldVersion to $newVersion, which will destroy all old data")

        // KILL PREVIOUS TABLE IF UPGRADED
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")

        // CREATE NEW INSTANCE OF TABLE
        onCreate(db)
    }

    companion object {
        const val TAG = "SQLiteHelper"
        const val  DATABASE_NAME = "my_database.db"
        // TOGGLE THIS NUMBER FOR UPDATING TABLES AND DATABASE
        const val DATABASE_VERSION = 1
        // NAME OF TABLE YOU WISH TO CREATE
        const val TABLE_NAME = "my_table"
        // SOME SAMPLE FIELDS
        const val UID = "_id"
        const val NAME = "name"
    }

}