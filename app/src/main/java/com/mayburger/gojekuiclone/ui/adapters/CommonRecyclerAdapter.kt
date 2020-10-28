package com.mayburger.gojekuiclone.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.mayburger.gojekuiclone.BR
import androidx.recyclerview.widget.RecyclerView
import com.mayburger.gojekuiclone.ui.base.BaseCommonViewHolder
import com.mayburger.gojekuiclone.ui.base.BaseCommonViewModel

class CommonRecyclerAdapter<T : BaseCommonViewModel?> :
        RecyclerView.Adapter<BaseCommonViewHolder<*>>() {

    private var data: java.util.ArrayList<out T>? = null
    private var mListener: Callback<T>? = null
    var selected: Int? = 0

    fun setItems(data: java.util.ArrayList<out T>?) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return data!![position]!!.layoutId()
    }

    override fun getItemCount(): Int {
        return data?.let{
            if (it.isEmpty()){
                0
            } else{
                it.size
            }
        }?:run{
            0
        }
    }

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): BaseCommonViewHolder<*> {
        val bind = DataBindingUtil.bind<ViewDataBinding>(
                LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        )
        return BaseCommonViewHolder(bind)
    }

    fun setListener(listener: Callback<T>) {
        this.mListener = listener
    }

    interface Callback<T> {
        fun onSelectedItem(position: Int, item: T)
    }

    override fun onBindViewHolder(holder: BaseCommonViewHolder<*>, position: Int) {
        val model = data!![position]
        holder.binding.setVariable(BR.viewModel, model)
        holder.binding.root.setOnClickListener {
            data?.get(position)?.let { it1 -> mListener?.onSelectedItem(position, it1) }
            selected = position
            notifyDataSetChanged()
        }
        holder.binding.executePendingBindings()
    }
}