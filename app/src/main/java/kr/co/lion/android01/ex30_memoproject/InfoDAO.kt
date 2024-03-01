package kr.co.lion.android01.ex30_memoproject

import android.content.Context

class InfoDAO {

    companion object{

        //selectOne
        fun selectOneInfo(context: Context, idx:Int): Info?{
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
                return null
            }
        }


        //selectAll
        fun selectAllInfo(context: Context) : MutableList<Info>{
            //쿼리 생성
            var sql = """select idx, title, date, contents
                |from InfoTable
                |order by idx desc
            """.trimMargin()

            //쿼리 실행
            var dbHelper = DBHelper(context)
            var cursor = dbHelper.writableDatabase.rawQuery(sql, null)

            //리스트를 만들어준다
            var infoList = mutableListOf<Info>()

            while (cursor.moveToNext()){
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
                infoList.add(infoModel)

            }
            dbHelper.close()
            cursor.close()
            return infoList
        }


        //insert
        fun insertInfo(context: Context, info: Info){
            var sql = """insert into InfoTable
                |(title, date, contents)
                |values(?, ?, ?)
            """.trimMargin()

            //?에 들어갈 값
            var args = arrayOf(info.title, info.date, info.contents)

            //쿼리 실행
            var dbHelper = DBHelper(context)
            dbHelper.writableDatabase.execSQL(sql, args)
            dbHelper.close()
        }


        //update
        fun updateInfo(context: Context, info: Info){
            //쿼리 생성
            var sql = """update InfoTable
                |set title = ?, date = ?, contents = ?
                |where idx = ?
            """.trimMargin()

            //?에 들어갈 값
            var args = arrayOf(info.title, info.date, info.contents, info.idx)

            //쿼리 실행
            var dbHelper = DBHelper(context)
            dbHelper.writableDatabase.execSQL(sql, args)
            dbHelper.close()
        }


        //delete
        fun deleteInfo(context: Context, idx: Int){
            var sql = """delete from InfoTable
                |where idx = ?
            """.trimMargin()

            //?에 들어갈 값
            var args = arrayOf(idx.toString())

            //쿼리 실행
            var dbHelper = DBHelper(context)
            dbHelper.writableDatabase.execSQL(sql, args)
            dbHelper.close()
        }

        //가장 큰 글 번호를 반환한다
        fun selectMaxIdx(context: Context):Int{
            //쿼리 생성
            var sql = """
                select max(idx) from InfoTable
            """.trimMargin()

            //데이터를 가져온다
            var dbHelper = DBHelper(context)
            var cursor = dbHelper.writableDatabase.rawQuery(sql, null)
            var maxMemoIdx = cursor.getInt(0)

            dbHelper.close()
            return maxMemoIdx
        }
    }
}