package com.saralpe.app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.saralpe.app.databinding.HomePromoDiscountListItemDesignBinding

class PromoDiscountListAdapter(private val context: Context, private var promoDiscountList: ArrayList<String>) :
    RecyclerView.Adapter<PromoDiscountListAdapter.ItemViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(position: Int)
        fun onItemLongClick(position: Int)
    }

    private var mListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.mListener = listener
    }

    open class ItemViewHolder(
        private val binding: HomePromoDiscountListItemDesignBinding,
        private val mListener: OnItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun dataBind(item: Pair<Int,String>, position: Int, context: Context) {
//              binding.topHeading.text = "30% OFF"
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutBinding =
            HomePromoDiscountListItemDesignBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(layoutBinding, mListener!!)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
//            val item = promoDiscountList[position]
//            holder.dataBind(item, position, context)
    }

    override fun getItemCount(): Int {
        return 5
    }

}