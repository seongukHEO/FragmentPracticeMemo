package kr.co.lion.android01.ex30_memoproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.android01.ex30_memoproject.databinding.CalBinding
import kr.co.lion.android01.ex30_memoproject.databinding.FragmentCalenderBinding
import java.util.Calendar


class CalenderFragment : Fragment() {

    lateinit var fragmentCalenderBinding: FragmentCalenderBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentCalenderBinding = FragmentCalenderBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity
        initView()
        setEvent()
        return fragmentCalenderBinding.root
    }

    //이벤트 설정
    fun setEvent(){
        fragmentCalenderBinding.apply {
            dateButton.setOnClickListener {
                var currentDate = Calendar.getInstance()
                var currentTimeInMillis = currentDate.timeInMillis

                calendarView.setDate(currentTimeInMillis, true, true)
            }
        }
    }


    //뷰 설정
    fun initView(){
        fragmentCalenderBinding.apply {
            recyclerview.apply {
                adapter = RecyclerViewAdapter()
                layoutManager = LinearLayoutManager(mainActivity)
                var deco = MaterialDividerItemDecoration(mainActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }


    //리사이클러뷰
    inner class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolderClass>(){

        //viewHolder
        inner class ViewHolderClass(calBinding: CalBinding):RecyclerView.ViewHolder(calBinding.root){
            var calBinding:CalBinding

            init {
                this.calBinding = calBinding
                this.calBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            var calBinding = CalBinding.inflate(layoutInflater)
            var viewHolder = ViewHolderClass(calBinding)
            return viewHolder
        }

        override fun getItemCount(): Int {
            return 20
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.calBinding.textView.text = "메모 제목"

            //클릭했을 때
            holder.calBinding.root.setOnClickListener {
                mainActivity.replaceFragment(FragmentName.SHOW_FRAGMENT, true, true, null)
            }
        }
    }














}






































