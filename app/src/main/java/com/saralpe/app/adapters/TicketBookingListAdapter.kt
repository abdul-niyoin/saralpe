package com.saralpe.app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.saralpe.app.databinding.HomeBankingServicesItemDesignBinding
import com.saralpe.app.databinding.HomeEgovernanceServicesItemDesignBinding
import com.saralpe.app.databinding.HomeInsurancePaymentItemDesignBinding
import com.saralpe.app.databinding.HomePaymentListItemDesignBinding
import com.saralpe.app.databinding.HomeRechargeBillPaymentItemDesignBinding
import com.saralpe.app.databinding.HomeTicketBookingItemDesignBinding

class TicketBookingListAdapter(private val context: Context, private var itemsList: ArrayList<Pair<Int,String>>) :
    RecyclerView.Adapter<TicketBookingListAdapter.ItemViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(position: Int)
        fun onItemLongClick(position: Int)
    }

    private var mListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.mListener = listener
    }

    open class ItemViewHolder(
        private val binding: HomeTicketBookingItemDesignBinding,
        private val mListener: OnItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun dataBind(item: Pair<Int,String>, position: Int, context: Context) {
            binding.icon.setImageResource(item.first)
            binding.title.text = item.second
            binding.root.setOnClickListener {
                mListener.onItemClick(position)
            }
            if(position == 0 || position == 1){

                binding.root.isEnabled = true
                binding.overlayoutView.visibility = View.GONE
            }
            else{
                binding.root.isEnabled = false
                binding.overlayoutView.visibility = View.VISIBLE
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
            HomeTicketBookingItemDesignBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(layoutBinding, mListener!!)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            val item = itemsList[position]
            holder.dataBind(item, position, context)
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

}