package com.idmdragon.base_ui

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseRecyclerViewAdapter<ITEM, VB:ViewBinding>:RecyclerView.Adapter<BaseRecyclerViewAdapter<ITEM,VB>.ViewHolder>(){

    protected val listItems = arrayListOf<ITEM>()

    abstract fun getViewBinding(parent: ViewGroup): VB

    abstract fun bindItem(binding: VB, item: ITEM, position: Int)

    fun getListItems(): List<ITEM> =
        listItems

    @SuppressLint("NotifyDataSetChanged")
    open fun addItems(items:List<ITEM>){
        listItems.addAll(items)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    open fun changeItems(items: List<ITEM>){
        listItems.apply {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = getViewBinding(parent)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        bindItem(holder.VB, listItems[position], position)
    }

    override fun getItemCount(): Int = listItems.size

    inner class ViewHolder(val VB:VB):RecyclerView.ViewHolder(VB.root)

}