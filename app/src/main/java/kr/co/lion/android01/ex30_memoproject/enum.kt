package kr.co.lion.android01.ex30_memoproject

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.DialogInterface
import android.os.SystemClock
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlin.concurrent.thread

class enum {

    companion object{
        //DiaLog 생성
        fun showDiaLog(context: Context, title:String, message:String, listener:(DialogInterface, Int) -> Unit){
            var diaLogBuilder = MaterialAlertDialogBuilder(context)
            diaLogBuilder.setTitle(title)
            diaLogBuilder.setMessage(message)
            diaLogBuilder.setPositiveButton("확인", listener)
            diaLogBuilder.setNegativeButton("취소", null)
            diaLogBuilder.show()

        }
        //키보드를 올려준다
//        fun showSoftInput(view: View){
//            view.requestFocus()
//            thread {
//                SystemClock.sleep(200)
//                var inputMethodManager = getSystemService
//            }
//        }
        //키보드를 내려준다
        fun hideSoftInput(activity: AppCompatActivity){
            //현재 포커스를 가지고있는 뷰가 있다면 키보드를 내린다
            if(activity.window.currentFocus != null){
                val inputMethodManager = activity.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(activity.window.currentFocus?.windowToken, 0);
            }
        }


    }
}
//여기에 들어오는 애들은 따로 툴바가 있는 애들
enum class FragmentName(var str:String){
    MAIN_FRAGMENT("MainFragment"),
    INPUT_FRAGMENT("InputFragment"),
    SHOW_FRAGMENT("ShowFragment"),
    MODIFY_FRAGMENT("ModifyFragment")
}

//mainFragment안에 들어올 fragment 즉 툴바가 없는 애들
enum class FragmentName2(var str:String){
    CALENDER_FRAGMENT("CalenderFragment"),
    ALL_MEMO_FRAGMENT("AllMemoFragment")
}