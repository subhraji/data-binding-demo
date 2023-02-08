package com.example.databinding.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.databinding.R
import com.example.databinding.databinding.TestRecyclerItemLayoutBinding
import com.example.databinding.model.TestModel
import com.example.databinding.model.todo.GetTodosResponse
import com.example.databinding.model.todo.GetTodosResponseItem

class TestAdapter(
    private var itemList: MutableList<GetTodosResponseItem>,
    private var context: Context
) : RecyclerView.Adapter<TestAdapter.TestViewHolder>(){

    inner class TestViewHolder(
        val testRecyclerItemLayoutBinding: TestRecyclerItemLayoutBinding
    ): RecyclerView.ViewHolder(testRecyclerItemLayoutBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TestViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.test_recycler_item_layout,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        holder.testRecyclerItemLayoutBinding.todos = itemList[position]
    }

    override fun getItemCount() = itemList.size
}