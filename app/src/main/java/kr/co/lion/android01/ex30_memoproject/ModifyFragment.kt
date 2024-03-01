package kr.co.lion.android01.ex30_memoproject

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import kr.co.lion.android01.ex30_memoproject.databinding.FragmentModifyBinding

class ModifyFragment : Fragment() {

    lateinit var fragmentModifyBinding: FragmentModifyBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentModifyBinding = FragmentModifyBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity
        getData()
        setToolBar()
        inputDataControll()
        return fragmentModifyBinding.root
    }

    //툴바 설정
    private fun setToolBar(){
        fragmentModifyBinding.apply {
            materialToolbar4.apply {
                title = "메모 수정"
                setNavigationIcon(R.drawable.arrow_back_24px)
                //클릭했을 때
                setNavigationOnClickListener {
                    mainActivity.removeFragment(FragmentName.MODIFY_FRAGMENT)
                }
                //메뉴
                inflateMenu(R.menu.modify_menu_file)
                setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.complete_menu -> {
                            var chk = checkOK()
                            if (chk == true){
                                inputData()
                                enum.showDiaLog(mainActivity, "메모 수정", "메모를 수정하시겠습니까?"){ dialogInterface: DialogInterface, i: Int ->
                                    mainActivity.removeFragment(FragmentName.MODIFY_FRAGMENT)
                                }
                                enum.hideSoftInput(mainActivity)
                            }
                        }
                        R.id.return_memo -> {
                            fragmentModifyBinding.apply {
                                textModifyTitle.setText("")
                                textModifyContents.setText("")
                            }
                        }
                    }
                    true
                }
            }
        }
    }

    //전달받은 값을 입력받는다
    private fun getData(){
        fragmentModifyBinding.apply {

            var title1 = arguments?.getString("title")
            if (title1 != null){
                var title = InfoDAO.selectAllTitle(mainActivity, title1)

                textModifyTitle.setText("${title?.title}")
                textModifyContents.setText("${title?.contents}")
            }
        }
    }

    //내용을 입력한다
    private fun inputData(){
        fragmentModifyBinding.apply {
            var title = textModifyTitle.text.toString()
            var contents = textModifyContents.text.toString()


        }
    }
    //입력요소 설정
    fun inputDataControll(){
        //우선 포커스를 준다
        fragmentModifyBinding.apply {
            mainActivity.showSoftInput(textModifyTitle)

            //에러 메시지를 지워준다
            textModifyTitle.addTextChangedListener {
                modifyLayoutTitle.error = null
            }
            textModifyContents.addTextChangedListener {
                modifyLayoutContents.error = null
            }
        }
    }

    //유효성 검사를 한다
    private fun checkOK() : Boolean{
        fragmentModifyBinding.apply {

            //빈 항목의 값을 담을 객체
            var errorView:View? = null

            var title = textModifyTitle.text.toString()
            if (title.trim().isEmpty()){
                modifyLayoutTitle.error = "메모 제목을 입력해주세요"
                if (errorView == null){
                    errorView = textModifyTitle
                }else{
                    modifyLayoutContents.error = null
                }
            }


            var contents = textModifyContents.text.toString()
            if (contents.trim().isEmpty()){
                modifyLayoutContents.error = "메모 내용을 입력해주세요"
                if (errorView == null){
                    errorView = textModifyContents
                }else{
                    textModifyContents.error = null
                }
            }
            //비어어있는 요소가 있다면 포커스를 준다
            if (errorView != null){
                mainActivity.showSoftInput(errorView)
                return false
            }
            return true

        }

    }

}
































