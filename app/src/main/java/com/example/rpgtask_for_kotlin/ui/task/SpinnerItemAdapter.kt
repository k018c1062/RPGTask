package com.example.rpgtask_for_kotlin.ui.task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

import com.example.rpgtask_for_kotlin.R
import com.example.rpgtask_for_kotlin.database.task.Task


class SpinnerItemAdapter(
    private val inflater: LayoutInflater,
    private val layoutId: Int,
    private val items: Array<String>
) : BaseAdapter(){

    internal class ViewHolder {
        var textView: TextView? = null

    }

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val viewHolder: ViewHolder
        var itemView: View = convertView!!
        if (convertView == null){
            itemView = inflater.inflate(layoutId,null)
            viewHolder = ViewHolder()

            viewHolder.textView = itemView.findViewById(R.id.custom_spinner_list_item)
            itemView.setTag(viewHolder)
        }else{
            viewHolder = convertView.getTag() as ViewHolder
        }
        viewHolder.textView?.setText(items.get(position))
        return itemView

    }
}