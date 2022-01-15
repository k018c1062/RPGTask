package com.example.rpgtask_for_kotlin.ui.task

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rpgtask_for_kotlin.MainActivity
import com.example.rpgtask_for_kotlin.R
import com.example.rpgtask_for_kotlin.database.task.Task
import com.example.rpgtask_for_kotlin.databinding.TaskFragmentBinding
import com.example.rpgtask_for_kotlin.ui.task.register.TaskAddFragment

class TaskFragment : Fragment() {
    private val TAG = "TaskFragment"


    companion object {
        fun newInstance() = TaskFragment()

    }


    private lateinit var binding: TaskFragmentBinding
    private val viewModel: TaskViewModel by viewModels {
        TaskListViewModelFactory((activity as MainActivity).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //メニューの有効化
        setHasOptionsMenu(true)

        binding = TaskFragmentBinding.inflate(inflater, container, false)

        val taskAdapter = TaskListAdapter()

        val taskRecyclerView = binding.taskList
        taskRecyclerView.layoutManager = LinearLayoutManager(context)
        taskRecyclerView.itemAnimator = DefaultItemAnimator()

        taskRecyclerView.adapter = taskAdapter

        viewModel.allTask.observe(viewLifecycleOwner, Observer { allTask ->
            allTask?.let { taskAdapter.submitList(it) }
        })



        binding.taskAddBtn.setOnClickListener(View.OnClickListener {
            val manager = parentFragmentManager
            val fragmentTransaction = manager.beginTransaction()

            fragmentTransaction.addToBackStack("TaskList")
            fragmentTransaction.setCustomAnimations(R.anim.taskaddfragment_slide_in, R.anim.taskaddfragment_slide_out)
            fragmentTransaction.replace(R.id.task_frame, TaskAddFragment.newInstance())
            fragmentTransaction.commit()

            binding.taskAddBtn.hide()

            Log.d(TAG, "タスク追加viewボタンが押下されました")


        })


        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.task_menu, menu)
        menu.findItem(R.id.task_menu_search).isVisible = true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.task_menu_search -> {
                Toast.makeText(requireContext(), "search", Toast.LENGTH_SHORT).show()
            }
        }
        return true
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        // TODO: Use the ViewModel

    }



}