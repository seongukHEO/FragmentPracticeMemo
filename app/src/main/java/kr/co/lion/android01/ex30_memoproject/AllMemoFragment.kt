package kr.co.lion.android01.ex30_memoproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.android01.ex30_memoproject.databinding.AllmemoBinding
import kr.co.lion.android01.ex30_memoproject.databinding.FragmentAllMemoBinding
import kr.co.lion.android01.ex30_memoproject.databinding.FragmentCalenderBinding

class AllMemoFragment : Fragment() {

    lateinit var fragmentAllMemoBinding: FragmentAllMemoBinding
    lateinit var mainActivity: MainActivity

    lateinit var allMemoViewModel:AllMemoViewModel


    val allMemoAdapter:AllMemoAdapter by lazy {
        val adapter = AllMemoAdapter()
        adapter.setMemoClickListener(object : AllMemoAdapter.OneMemoClickListener{
            override fun memoClickListener(title: String) {
                var bundle = Bundle()
                bundle.putString("title", title)
                mainActivity.replaceFragment(FragmentName.SHOW_FRAGMENT, true, true, bundle)
            }
        })
        adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentAllMemoBinding = FragmentAllMemoBinding.inflate(layoutInflater)
        allMemoViewModel = AllMemoViewModel()
        mainActivity = activity as MainActivity

        allMemoViewModel.gettingUserData(mainActivity)
        initView()
       // saveDate()
        initAdapter()
        return fragmentAllMemoBinding.root
    }

    override fun onResume() {
        super.onResume()
        fragmentAllMemoBinding.apply {
            recyclerview2.adapter?.notifyDataSetChanged()
        }
    }


    fun initView(){
        fragmentAllMemoBinding.apply {
            recyclerview2.apply {
                adapter = allMemoAdapter
                layoutManager = LinearLayoutManager(mainActivity)
                var deco = MaterialDividerItemDecoration(mainActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

    fun initAdapter(){
        allMemoViewModel.memoList.observe(viewLifecycleOwner){
            allMemoAdapter.submitList(it)
        }
    }
//    //초기화
//    fun saveDate(){
//        fragmentAllMemoBinding.apply {
//            memoList = InfoDAO.selectAllInfo(mainActivity)
//        }
//    }

//    fun reloadRecyclerView(){
//        //데이터를 읽어온다
//        memoList = InfoDAO.selectAllInfo(mainActivity)
//        //RecyclerView를 갱신한다
//        fragmentAllMemoBinding.recyclerview2.adapter?.notifyDataSetChanged()
//    }
}







































