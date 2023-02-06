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
import com.example.databinding.databinding.ActivityMainBinding
import com.example.databinding.model.Outcome
import com.example.databinding.model.TestModel
import com.example.databinding.viewmodel.NewViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mNewViewModel: NewViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main)

        val testModel: TestModel = TestModel("unknown", "hb rd, hunington, united states.")
        binding.testmodel = testModel

        //observer
        getTodosObserve()

        mNewViewModel.getTodos()
    }

    private fun getTodosObserve(){
        mNewViewModel.response.observe(this, Observer { outcome ->
            when(outcome){
                is Outcome.Success ->{
                    if(outcome.data != null){
                        Toast.makeText(this,"Got it", Toast.LENGTH_SHORT).show()
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

}