package kr.co.lion.android01.ex30_memoproject

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
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
        inputDataControll()
        return fragmentInputBinding.root
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
                            var chk = checkOK()
                            if (chk == true){
                                setEvent()
                                mainActivity.replaceFragment(FragmentName.SHOW_FRAGMENT, true, true, null)
                                enum.hideSoftInput(mainActivity)
                            }
                        }
                        R.id.all_delete_meun -> {
                            fragmentInputBinding.apply {
                                titleInputText.setText("")
                                contentsInputText.setText("")

                                titleLayout.error = null
                                contentsLayout.error = null
                                mainActivity.showSoftInput(titleInputText)
                            }
                        }
                    }
                    true
                }
            }
        }
    }

    //입력요소 설정
    fun inputDataControll(){
        fragmentInputBinding.apply {
            //화면이 시작되면 첫 번째 텍스트에 포커스를 준다
            mainActivity.showSoftInput(titleInputText)

            titleInputText.addTextChangedListener {
                titleLayout.error = null
            }
            contentsInputText.addTextChangedListener {
                contentsLayout.error = null
            }
        }
    }

    //이벤트 설정
    fun setEvent(){
        fragmentInputBinding.apply {
            var title = titleInputText.text.toString()
            var contents = contentsInputText.text.toString()

            var str = Info(1, title, null, contents)
            InfoDAO.insertInfo(mainActivity, str)
        }
    }

    //유효성 검사
    fun checkOK() : Boolean{
        fragmentInputBinding.apply {
            var emptyView:View? = null

            var title = titleInputText.text.toString()
            if (title.trim().isEmpty()){
                titleLayout.error = "메모 제목을 입력해주세요"
                if (emptyView == null){
                    emptyView = titleInputText
                }else{
                    titleLayout.error = null
                }
            }

            var contents = contentsInputText.text.toString()
            if (contents.trim().isEmpty()){
                contentsLayout.error = "메모 내용을 입력해주세요"
                if (emptyView == null){
                    emptyView = contentsInputText
                }else{
                    contentsInputText.error = null
                }
            }
            //비어있는 입력 요소가 있다면 포커스를 준다
            if (emptyView != null){
                mainActivity.showSoftInput(emptyView)
                return false
            }
            return true
        }
    }
}



































