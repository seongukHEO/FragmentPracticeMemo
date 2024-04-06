package kr.co.lion.android01.ex30_memoproject

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AllMemoViewModel :ViewModel() {
    private val _memoList:MutableLiveData<List<Info>> = MutableLiveData(emptyList())
    val memoList:LiveData<List<Info>> = _memoList

    fun gettingUserData(context: Context){
        //데이터를 받아온다
        _memoList.postValue(InfoDAO.selectAllInfo(context))
    }
}