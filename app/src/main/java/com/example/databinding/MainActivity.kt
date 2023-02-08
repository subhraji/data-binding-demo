package com.example.databinding

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.databinding.adapter.TestAdapter
import com.example.databinding.databinding.ActivityMainBinding
import com.example.databinding.model.Outcome
import com.example.databinding.model.TestModel
import com.example.databinding.model.todo.GetTodosResponseItem
import com.example.databinding.viewmodel.NewViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mNewViewModel: NewViewModel by viewModels()
    private var testList: MutableList<TestModel> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main)

        //observer
        getTodosObserve()

        mNewViewModel.getTodos()

        /*testList.add(TestModel("jhon","fddg sgs vsds"))
        testList.add(TestModel("aryan","fddg sgs"))
        testList.add(TestModel("isaiah","fddg efa ef"))
        fillTestRecycler(testList)*/
    }

    private fun getTodosObserve(){
        mNewViewModel.response.observe(this, Observer { outcome ->
            when(outcome){
                is Outcome.Success ->{
                    if(outcome.data != null){
                        Toast.makeText(this,"Got it", Toast.LENGTH_SHORT).show()
                        binding.todos = outcome.data
                        fillTestRecycler(outcome.data!!)
                        mNewViewModel.navigationComplete()
                    }else{
                        Toast.makeText(this,"no data", Toast.LENGTH_SHORT).show()
                    }
                }
                is Outcome.Failure<*> -> {
                    Toast.makeText(this,outcome.e.message, Toast.LENGTH_SHORT).show()

                    outcome.e.printStackTrace()
                    Log.i("status",outcome.e.cause.toString())
                }
                is Outcome.Progress ->{

                }
                null ->{

                }
            }
        })
    }

    private fun fillTestRecycler(list: MutableList<GetTodosResponseItem>) {
        val gridLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.testRecycler.apply {
            layoutManager = gridLayoutManager
            adapter = TestAdapter(list,this@MainActivity)
        }
    }
}