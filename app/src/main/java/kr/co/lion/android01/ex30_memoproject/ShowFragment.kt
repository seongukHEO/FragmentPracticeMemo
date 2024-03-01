package kr.co.lion.android01.ex30_memoproject

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.android01.ex30_memoproject.databinding.FragmentShowBinding

class ShowFragment : Fragment() {

    lateinit var fragmentShowBinding: FragmentShowBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentShowBinding = FragmentShowBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity
        setToolBar()
        inputData()
        return fragmentShowBinding.root
    }

    override fun onResume() {
        super.onResume()
        fragmentShowBinding.apply {
            enum.hideSoftInput(mainActivity)
        }
    }


    //툴바 설정
    fun setToolBar(){
        fragmentShowBinding.apply {
            materialToolbar3.apply {
                title = "메모 보기"
                setNavigationIcon(R.drawable.arrow_back_24px)
                //클릭했을 때
                setNavigationOnClickListener {
                    mainActivity.removeFragment(FragmentName.SHOW_FRAGMENT)
                }
                //메모 설정
                inflateMenu(R.menu.show_menu)
                //메뉴를 클릭했을때
                setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.modify_menu -> {
                            var title = arguments?.getString("title")

                            var bundle = Bundle()
                            bundle.putString("title", title)

                            mainActivity.replaceFragment(FragmentName.MODIFY_FRAGMENT, true, true, bundle)
                        }
                        R.id.delete_menu -> {
                            enum.showDiaLog(mainActivity, "메모 삭제", "메모를 삭제하시겠습니까?"){ dialogInterface: DialogInterface, i: Int ->
                                var title = arguments?.getString("title")
                                if (title != null){
                                    InfoDAO.deleteInfo(mainActivity, title!!)
                                    mainActivity.replaceFragment(FragmentName.MAIN_FRAGMENT, true, true, null)
                                }
                            }
                        }
                    }


                    true
                }
            }
        }
    }

    //내용을 입력한다
    fun inputData(){
        fragmentShowBinding.apply {
            var title = arguments?.getString("title")

            if (title != null){
                var memoInfo = InfoDAO.selectAllTitle(mainActivity, title)
                notextTitle.setText("${memoInfo?.title}")
                notextDate.setText("${memoInfo?.date}")
                nocontentsText.setText("${memoInfo?.contents}")
            }
        }
    }
}

































