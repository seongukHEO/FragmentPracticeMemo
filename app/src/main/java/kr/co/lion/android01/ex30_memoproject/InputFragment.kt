package kr.co.lion.android01.ex30_memoproject

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.android01.ex30_memoproject.databinding.FragmentInputBinding

class InputFragment : Fragment() {

    lateinit var fragmentInputBinding: FragmentInputBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentInputBinding = FragmentInputBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity
        setToolBar()
        return fragmentInputBinding.root
    }

    override fun onResume() {
        super.onResume()
        fragmentInputBinding.apply {
            enum.showSoftInput(titleInputText, mainActivity)
        }
    }
    //툴바 설정
    fun setToolBar(){
        fragmentInputBinding.apply {
            materialToolbar2.apply {
                title = "메모 추가"
                //아이콘
                setNavigationIcon(R.drawable.arrow_back_24px)
                //눌렀을 때
                setNavigationOnClickListener {
                    mainActivity.removeFragment(FragmentName.INPUT_FRAGMENT)
                }
                //메뉴 설정
                inflateMenu(R.menu.input_menu)
                //클릭했을 때
                setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.done_menu -> {
                            mainActivity.replaceFragment(FragmentName.SHOW_FRAGMENT, true, true,null)

                        }
                        R.id.all_delete_meun -> {
                            fragmentInputBinding.apply {
                                titleInputText.setText("")
                                contentsInputText.setText("")
                            }
                        }
                    }
                    true
                }
            }
        }
    }

    //이벤트 설정
    fun setEvent(){
        fragmentInputBinding.apply {
            var title = titleInputText.text.toString()
            var contents = contentsInputText.text.toString()
        }
    }

    //유효성 검사
    fun checkOK(){
        fragmentInputBinding.apply {
            var title = titleInputText.text.toString()
            if (title.trim().isEmpty()){
                enum.showDiaLog(mainActivity, "제목 입력 오류", "제목을 입력해주세요"){ dialogInterface: DialogInterface, i: Int ->
                    enum.showSoftInput(titleInputText, mainActivity)
                }
                return
            }

            var contents = contentsInputText.text.toString()
            if (contents.trim().isEmpty()){
                enum.showDiaLog(mainActivity, "내용 입력 오류", "내용을 입력해주세요"){ dialogInterface: DialogInterface, i: Int ->
                    enum.showSoftInput(contentsInputText, mainActivity)
                }
                return
            }
        }
        //setEvent()
    }
}



































