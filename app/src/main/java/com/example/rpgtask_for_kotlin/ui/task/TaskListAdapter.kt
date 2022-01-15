package com.example.rpgtask_for_kotlin.ui.task

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rpgtask_for_kotlin.R
import com.example.rpgtask_for_kotlin.database.task.Task
import com.example.rpgtask_for_kotlin.databinding.TaskFragmentBinding
import com.example.rpgtask_for_kotlin.databinding.TaskItemBinding
import net.cachapa.expandablelayout.ExpandableLayout

class TaskListAdapter() : ListAdapter<Task, TaskListAdapter.TaskListViewHolder>(TaskComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListViewHolder {
        return TaskListViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: TaskListViewHolder, position: Int) {
        val current = currentList[position]
        holder.bind(
            current.name,
            current.memo
        )
    }

    override fun getItemCount(): Int {
        return currentList.size
    }


    class TaskListViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        private var currentItem: Task? = null
        private val taskName: TextView = itemview.findViewById(R.id.task_item_name_view)
        private val taskMemo: TextView = itemview.findViewById(R.id.task_item_memo_view)
        private val expandableLayout: ExpandableLayout = itemview.findViewById(R.id.expandable_layout)

        init {
            itemview.setOnClickListener{
                expandableLayout.toggle()
            }
        }

        fun bind(
            name: String?,
            memo: String?,
        ) {
            taskName.text = name
            taskMemo.text = memo
        }

        companion object{
            fun create(parent: ViewGroup): TaskListViewHolder{
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.task_item,parent,false)
                return TaskListViewHolder(view)
            }
        }
    }

    class TaskComparator : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.taskId == newItem.taskId &&
                    oldItem.name == newItem.name &&
                    oldItem.memo == newItem.memo &&
                    oldItem.taskTag == newItem.taskTag
        }

    }

}

