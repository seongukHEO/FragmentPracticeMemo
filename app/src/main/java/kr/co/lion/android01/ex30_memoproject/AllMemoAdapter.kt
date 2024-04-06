package kr.co.lion.android01.ex30_memoproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.android01.ex30_memoproject.databinding.AllmemoBinding

class AllMemoAdapter : RecyclerView.Adapter<ViewHolderClass2>() {

    private lateinit var memoClickListener: OneMemoClickListener

    fun setMemoClickListener(memoClickListener: OneMemoClickListener){
        this.memoClickListener = memoClickListener
    }

    var memoList = listOf<Info>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass2 {

        val layoutInflater = LayoutInflater.from(parent.context)

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
            memoClickListener.memoClickListener(memoList[position].title)

        }
    }

    fun submitList(newList: List<Info>){
        memoList = newList
        notifyDataSetChanged()
    }

    interface OneMemoClickListener{
        fun memoClickListener(title : String)
    }
}

class ViewHolderClass2(allmemoBinding: AllmemoBinding):RecyclerView.ViewHolder(allmemoBinding.root){
    var allmemoBinding: AllmemoBinding

    init {
        this.allmemoBinding = allmemoBinding

        this.allmemoBinding.root.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }
}