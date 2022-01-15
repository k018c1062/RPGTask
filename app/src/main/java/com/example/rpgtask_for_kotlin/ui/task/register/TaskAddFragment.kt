package com.example.rpgtask_for_kotlin.ui.task.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.rpgtask_for_kotlin.MainActivity
import com.example.rpgtask_for_kotlin.databinding.FragmentTaskAddBinding
import com.example.rpgtask_for_kotlin.ui.task.TaskListViewModelFactory
import com.example.rpgtask_for_kotlin.ui.task.TaskViewModel


import android.widget.Spinner
import android.widget.Toast
import androidx.annotation.WorkerThread
import com.example.rpgtask_for_kotlin.R
import com.example.rpgtask_for_kotlin.database.task.Task
import com.example.rpgtask_for_kotlin.databinding.TaskFragmentBinding
import com.example.rpgtask_for_kotlin.ui.task.SpinnerItemAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [TaskAddFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TaskAddFragment : Fragment() {
    private val viewModel: TaskViewModel by viewModels {
        TaskListViewModelFactory((activity as MainActivity).repository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val binding: FragmentTaskAddBinding =
            FragmentTaskAddBinding.inflate(inflater, container, false)


        val tagSpinner: Spinner = binding.spinnerType
        val spinnerItemAdapter: SpinnerItemAdapter = SpinnerItemAdapter(
            layoutInflater,
            R.layout.custom_spinner,
            resources.getStringArray(R.array.spinner_type_filter)
        )



        binding.taskRegisterBtn.setOnClickListener(View.OnClickListener {

            val taskName = if (binding.taskName.text.toString() == ""){
                createDateString()
            } else {
                binding.taskName.text.toString()
            }
            val taskMemo = if(binding.taskMemo.text.toString() == ""){
                taskName+"に作成されたタスク"
            }else{
                binding.taskMemo.text.toString()
            }
            val taskTimeLimit = binding.taskTimeLimit.text.toString()
            val taskType = binding.spinnerType.selectedItem.toString()
            val taskRepeatSetting = binding.spinnerRepeat.selectedItem.toString()
            val taskImportance = binding.spinnerImportance.selectedItem.toString()
            val taskGrowthItems = binding.spinnerGrowthItems.selectedItem.toString()
            val taskDifficulty = binding.spinnerDifficulty.selectedItem.toString()


            GlobalScope.launch(Dispatchers.IO) {

                viewModel.insert(
                    Task(
                        UUID.randomUUID().toString(),
                        taskName,
                        taskMemo,
                        taskTimeLimit,
                        taskRepeatSetting,
                        "0",
                        taskGrowthItems,
                        taskDifficulty,
                        taskImportance,
                        "",
                        taskType
                    )
                )
                val manager = parentFragmentManager
                manager.popBackStack()
            }

            container?.findViewById<FloatingActionButton>(R.id.task_add_btn)?.show()
        })
        return binding.root
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment TaskAddFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = TaskAddFragment()
    }

    private fun createDateString(): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = Date().time
        return String.format(
            "%d/%d/%d : %d:%d:%d",
            calendar[Calendar.YEAR], calendar[Calendar.MONTH] + 1, calendar[Calendar.DATE],calendar[Calendar.HOUR_OF_DAY],calendar[Calendar.MINUTE],calendar[Calendar.SECOND]
        )
    }
}