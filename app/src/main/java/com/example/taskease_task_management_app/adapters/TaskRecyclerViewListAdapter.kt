package com.example.taskease_task_management_app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taskease_task_management_app.R
import com.example.taskease_task_management_app.models.Task
import java.text.SimpleDateFormat
import java.util.Locale

class TaskRecyclerViewListAdapter(
    private val isList: MutableLiveData<Boolean>,
    private val deleteUpdateCallback: (type: String, position: Int, task: Task) -> Unit
) :
    ListAdapter<Task, RecyclerView.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == VIEW_TYPE_GRID) {
            val view = inflater.inflate(R.layout.view_task_grid, parent, false)
            GridTaskViewHolder(view)
        } else {
            val view = inflater.inflate(R.layout.view_task_list, parent, false)
            ListTaskViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val task = getItem(position)
        if (isList.value == true) {
            (holder as? ListTaskViewHolder)?.bind(task, deleteUpdateCallback)
        } else {
            (holder as? GridTaskViewHolder)?.bind(task, deleteUpdateCallback)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (isList.value == true) {
            VIEW_TYPE_LIST
        } else {
            VIEW_TYPE_GRID
        }
    }

    class ListTaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTxt: TextView = itemView.findViewById(R.id.titleTxt)
        private val descrTxt: TextView = itemView.findViewById(R.id.descrTxt)
        private val dateTxt: TextView = itemView.findViewById(R.id.dateTxt)
        private val editImg: ImageView = itemView.findViewById(R.id.editImg)
        private val deleteImg: ImageView = itemView.findViewById(R.id.deleteImg)

        fun bind(
            task: Task,
            deleteUpdateCallback: (type: String, position: Int, task: Task) -> Unit
        ) {
            titleTxt.text = task.title
            descrTxt.text = task.description
            val dateFormat = SimpleDateFormat("dd-MMM-yyyy HH:mm:ss a", Locale.getDefault())
            dateTxt.text = dateFormat.format(task.date)
            deleteImg.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    deleteUpdateCallback("delete", position, task)
                }
            }
            editImg.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    deleteUpdateCallback("update", position, task)
                }
            }
        }
    }

    class GridTaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTxt: TextView = itemView.findViewById(R.id.titleTxt)
        private val descrTxt: TextView = itemView.findViewById(R.id.descrTxt)
        private val dateTxt: TextView = itemView.findViewById(R.id.dateTxt)
        private val editImg: ImageView = itemView.findViewById(R.id.editImg)
        private val deleteImg: ImageView = itemView.findViewById(R.id.deleteImg)

        fun bind(
            task: Task,
            deleteUpdateCallback: (type: String, position: Int, task: Task) -> Unit
        ) {
            titleTxt.text = task.title
            descrTxt.text = task.description
            val dateFormat = SimpleDateFormat("dd-MMM-yyyy HH:mm:ss a", Locale.getDefault())
            dateTxt.text = dateFormat.format(task.date)
            deleteImg.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    deleteUpdateCallback("delete", position, task)
                }
            }
            editImg.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    deleteUpdateCallback("update", position, task)
                }
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }
    }

    companion object {
        private const val VIEW_TYPE_LIST = 0
        private const val VIEW_TYPE_GRID = 1
    }
}
