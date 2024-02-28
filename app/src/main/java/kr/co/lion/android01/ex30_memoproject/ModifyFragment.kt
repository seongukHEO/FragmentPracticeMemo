package kr.co.lion.android01.ex30_memoproject

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
                            enum.showDiaLog(mainActivity, "메모 수정", "메모를 수정하시겠습니까?"){ dialogInterface: DialogInterface, i: Int ->
                                mainActivity.replaceFragment(FragmentName.SHOW_FRAGMENT, true,true,null)
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
            textModifyTitle.setText("이거지이거지")
            textModifyContents.setText("이거야이거!!")


        }
    }

    //내용을 입력한다
    private fun inputData(){
        fragmentModifyBinding.apply {
            var title = textModifyTitle.text.toString()
            var contents = textModifyContents.text.toString()


        }
    }

    //유효성 검사를 한다
    private fun checkOK(){
        fragmentModifyBinding.apply {
            var title = textModifyTitle.text.toString()
            if (title.trim().isEmpty()){
                enum.showDiaLog(mainActivity, "제목 입력 오류", "제목을 입력해주세요"){ dialogInterface: DialogInterface, i: Int ->
                    enum.showSoftInput(textModifyTitle, mainActivity)
                }
                return
            }

            var contents = textModifyContents.text.toString()
            if (contents.trim().isEmpty()){
                enum.showDiaLog(mainActivity, "내용 입력 오류", "내용을 입력해주세요"){ dialogInterface: DialogInterface, i: Int ->
                    enum.showSoftInput(textModifyContents, mainActivity)
                }
                return
            }
        }
        //inputData()
    }

}
































