package kr.co.lion.android01.ex30_memoproject

import android.content.Context

class InfoDAO {

    companion object{

        //selectOne
        fun selectOneInfo(context: Context, idx:Int): Info{
            //쿼리 생성
            var sql = """select idx, title, date, contents
                |from InfoTable
                |where idx = ?
            """.trimMargin()

            //?에 들어갈 값
            var args = arrayOf(idx.toString())

            //쿼리 실행
            var dbHelper = DBHelper(context)
            var cursor = dbHelper.writableDatabase.rawQuery(sql, args)

            //값을 가져온다
            if (cursor.moveToNext()){
                //순서값을 가져온다
                var idx1 = cursor.getColumnIndex("idx")
                var idx2 = cursor.getColumnIndex("title")
                var idx3 = cursor.getColumnIndex("date")
                var idx4 = cursor.getColumnIndex("contents")

                //값을 가져온다
                var idx = cursor.getInt(idx1)
                var title = cursor.getString(idx2)
                var date = cursor.getString(idx3)
                var contents = cursor.getString(idx4)

                var infoModel = Info(idx, title, date, contents)

                dbHelper.close()
                cursor.close()
                return infoModel
            }else{
                dbHelper.close()
                cursor.close()

            }

        }


        //selectAll


        //insert


        //update


        //delete
    }
}