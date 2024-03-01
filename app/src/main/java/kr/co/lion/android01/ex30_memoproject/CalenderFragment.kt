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
        saveCalenderData()
        return fragmentCalenderBinding.root
    }

    //이벤트 설정
    fun setEvent(){
        fragmentCalenderBinding.apply {
            dateButton.setOnClickListener {
                //달력의 시점을 현재 시점으로 맞춘다
                calendarView.date = System.currentTimeMillis()
                //이 값을 메인 엑티비티에 저장한다 왜냐 프라그먼트는 화면이 넘어가면 안에있는 제약이 날아간다고
                //생각하면 된다
                mainActivity.calenderNowTime = calendarView.date
            }
        }
    }

    //화면을 바꿔도 캘린더는 그대로 둔다!
    fun saveCalenderData(){
        fragmentCalenderBinding.apply {
            calendarView.apply {
                date = mainActivity.calenderNowTime

                //캘린더의 위치가 바뀌면 동작하는 메서드
                setOnDateChangeListener { view, year, month, dayOfMonth ->
                    //달력의 정보를 cal에 담아주고
                    var cal = Calendar.getInstance()
                    //년,월,일을 넣어준다
                    cal.set(year, month, dayOfMonth)
                    // 설정된 날짜값을 Long 형태의 시간 값으로 가져와 담아준다.
                    mainActivity.calenderNowTime = cal.timeInMillis
                }
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






































