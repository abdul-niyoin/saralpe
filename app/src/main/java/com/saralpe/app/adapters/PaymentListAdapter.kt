package com.saralpe.app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.saralpe.app.databinding.HomePaymentListItemDesignBinding

class PaymentListAdapter(private val context: Context, private var paymentList: ArrayList<Pair<Int,String>>) :
    RecyclerView.Adapter<PaymentListAdapter.ItemViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(position: Int,view:MaterialTextView)
        fun onItemLongClick(position: Int)
    }

    private var mListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.mListener = listener
    }

    open class ItemViewHolder(
        private val binding: HomePaymentListItemDesignBinding,
        private val mListener: OnItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun dataBind(item: Pair<Int,String>, position: Int, context: Context) {
              binding.icon.setImageResource(item.first)
              binding.title.text = item.second
//            Glide.with(context).load(item.file.absolutePath).into(binding.imageItemView)
            binding.root.setOnClickListener {
                mListener.onItemClick(position,binding.title)
            }
//            if (item.isSelected) {
//                binding.checkbox.visibility = View.VISIBLE
//                binding.imageItemView.alpha = 0.4f
//            } else {
//                binding.checkbox.visibility = View.GONE
//                binding.imageItemView.alpha = 1.0f
//            }

//            binding.imageItemView.setOnLongClickListener {
//                mListener.onItemLongClick(position)
//                true
//            }
        }

    }

//    fun updateRecyclerData(imagesList: ArrayList<FileObject>){
//        this.paymentList.clear()
//        this.paymentList.addAll(imagesList)
//        notifyItemRangeChanged(0,this.paymentList.size)
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutBinding =
            HomePaymentListItemDesignBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(layoutBinding, mListener!!)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            val item = paymentList[position]
            holder.dataBind(item, position, context)
    }

    override fun getItemCount(): Int {
        return paymentList.size
    }

}