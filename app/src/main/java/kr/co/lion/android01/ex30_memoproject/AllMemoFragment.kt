package kr.co.lion.android01.ex30_memoproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.android01.ex30_memoproject.databinding.AllmemoBinding
import kr.co.lion.android01.ex30_memoproject.databinding.FragmentAllMemoBinding
import kr.co.lion.android01.ex30_memoproject.databinding.FragmentCalenderBinding

class AllMemoFragment : Fragment() {

    lateinit var fragmentAllMemoBinding: FragmentAllMemoBinding
    lateinit var mainActivity: MainActivity

    lateinit var memoList:MutableList<Info>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentAllMemoBinding = FragmentAllMemoBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity
        initView()
        saveDate()
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
                adapter = RecyclerViewAdapter2()
                layoutManager = LinearLayoutManager(mainActivity)
                var deco = MaterialDividerItemDecoration(mainActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }
    //초기화
    fun saveDate(){
        fragmentAllMemoBinding.apply {
            memoList = InfoDAO.selectAllInfo(mainActivity)
        }
    }






    inner class RecyclerViewAdapter2:RecyclerView.Adapter<RecyclerViewAdapter2.ViewHolderClass2>(){

        inner class ViewHolderClass2(allmemoBinding: AllmemoBinding):RecyclerView.ViewHolder(allmemoBinding.root){
            var allmemoBinding:AllmemoBinding

            init {
                this.allmemoBinding = allmemoBinding

                this.allmemoBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass2 {
            var allmemoBinding = AllmemoBinding.inflate(layoutInflater)
            var viewHolder = ViewHolderClass2(allmemoBinding)
            return viewHolder
        }

        override fun getItemCount(): Int {
            return memoList.size
        }

        override fun onBindViewHolder(holder: ViewHolderClass2, position: Int) {
            holder.allmemoBinding.textAllMemoTitle.text = memoList[position].title
            holder.allmemoBinding.textAllMemoDate.text = memoList[position].date

            //클릭했을 때
            holder.allmemoBinding.root.setOnClickListener {

                var bundle = Bundle()
                bundle.putString("title", memoList[position].title)
                mainActivity.replaceFragment(FragmentName.SHOW_FRAGMENT, true, true, bundle)
            }
        }
    }
}







































