package kr.co.lion.android01.ex30_memoproject

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) :SQLiteOpenHelper(context, "Info.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        //테이블 생성
        var sql = """create table InfoTable
            |(idx integer primary key autoincrement,
            |title text not null,
            |date text not null,
            |contents text not null)
        """.trimMargin()
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}