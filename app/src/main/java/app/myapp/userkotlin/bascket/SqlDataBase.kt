package app.myapp.userkotlin.bascket

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SqlDataBase(var context: Context) :SQLiteOpenHelper(context,DataBase,null,1){



    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table $TABLE (id INTEGER PRIMARY KEY AUTOINCREMENT,"
       +"name TEXT,image TEXT )")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS "+ TABLE)
    }

    companion object{
        val DataBase="requestuser"
        val TABLE="tablerequest"
        val ID="id"
        val NAME="name"
        val IMAGE="image"
    }

    fun insertData(item:ItemBascket){

        val db=writableDatabase
        val contentVlaues=ContentValues()
        contentVlaues.put(NAME,item.name)
        contentVlaues.put(IMAGE,item.image)
        db.insert(TABLE,null,contentVlaues)

    }

    val allData:Cursor
    get() {
        val db=writableDatabase
        val res=db.rawQuery("select * from "+ TABLE,null)
        return res
    }

    fun deleted(id:String){

        val db=writableDatabase
        db.delete(TABLE,"id = ?", arrayOf(id))

    }

    fun deletedtable(){

        val db=writableDatabase

        db.delete(TABLE,null,null)


    }


}