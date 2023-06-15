package com.codingtask.simpletodotask

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.codingtask.simpletodotask.databinding.ActivityTaskBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

const val DB_NAME = "todo.db"

class TaskActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityTaskBinding

    val db by lazy {
        AppDatabase.getDatabase(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.saveBtn.setOnClickListener(this)

    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.saveBtn -> {
                saveTodo()
            }
        }

    }

    private fun saveTodo() {
        val title = binding.taskTitle.editText?.text.toString()
        val description =binding.taskDescription.editText?.text.toString()
       if(title.isNotEmpty()){
           GlobalScope.launch(Dispatchers.Main) {
               val id = withContext(Dispatchers.IO) {
                   return@withContext db.todoDao().insertTask(
                       TodoModel(
                           title,
                           description,
                           true,
                       )
                   )
               }
               finish()
           }
       }else Toast.makeText(this,"Please enter task title",Toast.LENGTH_SHORT).show()
    }


}
