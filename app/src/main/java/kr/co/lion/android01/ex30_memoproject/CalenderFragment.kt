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
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date


class CalenderFragment : Fragment() {

    lateinit var fragmentCalenderBinding: FragmentCalenderBinding
    lateinit var mainActivity: MainActivity

    //리사이클러뷰!
    lateinit var memoList:MutableList<Info>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentCalenderBinding = FragmentCalenderBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity
        initView()
        setEvent()
        saveCalenderData()
        saveDate()
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
                getMemoDate()
            }
        }
    }

    //초기화
    fun saveDate(){
        fragmentCalenderBinding.apply {
            memoList = InfoDAO.selectAllInfo(mainActivity)
            getMemoDate()
        }
    }

    //화면을 바꿔도 캘린더는 그대로 둔다!
    fun saveCalenderData(){
        fragmentCalenderBinding.apply {
            calendarView.apply {
                date = mainActivity.calenderNowTime

                //오늘 이후의 달력은 선택 못함!
                maxDate = System.currentTimeMillis()
                //캘린더의 위치가 바뀌면 동작하는 메서드
                setOnDateChangeListener { view, year, month, dayOfMonth ->
                    //달력의 정보를 cal에 담아주고
                    var cal = Calendar.getInstance()
                    //년,월,일을 넣어준다
                    cal.set(year, month, dayOfMonth)
                    // 설정된 날짜값을 Long 형태의 시간 값으로 가져와 담아준다.
                    mainActivity.calenderNowTime = cal.timeInMillis
                    getMemoDate()
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
                getMemoDate()
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
            return memoList.size
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.calBinding.textView.text = memoList[position].title

            //클릭했을 때
            holder.calBinding.root.setOnClickListener {

                var bundle = Bundle()
                bundle.putString("title", memoList[position].title)
                mainActivity.replaceFragment(FragmentName.SHOW_FRAGMENT, true, true, bundle)
            }
        }
    }
    //캘린더에 설정되어있는 날짜의 메모 내용을 가져와 리사이클러뷰를 갱신한다
    fun getMemoDate(){
        var simple = SimpleDateFormat("yyyy-MM-dd")
        var date = simple.format(mainActivity.calenderNowTime)
        //데이터를 가져온다
        memoList = InfoDAO.selectAllDate(mainActivity, date)
        //리사이클러뷰 갱신
        fragmentCalenderBinding.recyclerview.adapter?.notifyDataSetChanged()
    }

    fun reloadRecyclerView(){
        //데이터를 읽어온다
        memoList = InfoDAO.selectAllInfo(mainActivity)
        //RecyclerView를 갱신한다
        fragmentCalenderBinding.recyclerview.adapter?.notifyDataSetChanged()
    }














}






































