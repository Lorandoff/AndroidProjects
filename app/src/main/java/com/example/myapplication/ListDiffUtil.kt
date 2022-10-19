package com.example.myapplication

import androidx.recyclerview.widget.DiffUtil

class ListDiffUtil(private val oldlist: List<Plant>, private val newlist: List<Plant>): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldlist.size
    }

    override fun getNewListSize(): Int {
        return newlist.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldlist = oldlist[oldItemPosition]
        val newlist = newlist[newItemPosition]
        return oldlist.id == newlist.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldlist = oldlist[oldItemPosition]
        val newlist = newlist[newItemPosition]
        return oldlist == newlist
    }
}