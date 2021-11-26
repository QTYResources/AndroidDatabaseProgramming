package jwei.apps.dataforandroid.ch1

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class SQLiteExample : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // INIT OUR SQLITE HELPER
        val sqh = SQLiteHelper(this)

        // RETRIEVE A READABLE AND WRITEABLE DATABASE
        val sqdb = sqh.writableDatabase

        // METHOD #1: INSERT USING CONTENTVALUE CLASS
        val cv = ContentValues()
        cv.put(SQLiteHelper.NAME, "jason wei")

        // CALL INSERT METHOD
        sqdb.insert(SQLiteHelper.TABLE_NAME, SQLiteHelper.NAME, cv)

        // METHOD #2: INSERT USING SQL QUERY
        val insertQuery = "INSERT INTO ${SQLiteHelper.TABLE_NAME} (${SQLiteHelper.NAME}) VALUES ('jwei')"
        sqdb.execSQL(insertQuery)

        // METHOD #1: QUERY USING WRAPPER METHOD
        val c = sqdb.query(SQLiteHelper.TABLE_NAME, arrayOf( SQLiteHelper.UID, SQLiteHelper.NAME ), null,
                    null, null, null, null)

        while (c.moveToNext()) {
            // GET COLUMN INDICES AS WELL AS VALUES OF THOSE COLUMNS
            val id = c.getInt(c.getColumnIndex(SQLiteHelper.UID))
            val name = c.getString(c.getColumnIndex(SQLiteHelper.NAME))
            Log.i("LOG_TAG", "ROW $id HAS NAME $name")
        }

        c.close()

        // METHOD #2: QUERY USING SQL SELECT QUERY
        val query = "SELECT ${SQLiteHelper.UID}, ${SQLiteHelper.NAME} FROM ${SQLiteHelper.TABLE_NAME}"
        val c2 = sqdb.rawQuery(query, null)

        while (c2.moveToNext()) {
            val id = c2.getInt(c2.getColumnIndex(SQLiteHelper.UID))
            val name = c2.getString(c2.getColumnIndex(SQLiteHelper.NAME))
            Log.i("LOG_TAG", "ROW $id HAS NAME $name")
        }

        c2.close()

        // CLOSE DATABASE CONNECTIONS
        sqdb.close()
        sqh.close()
    }
}