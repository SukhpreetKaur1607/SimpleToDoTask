package com.example.todoapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.codingtask.simpletodotask.TodoModel

@Dao
interface TodoDao {

    @Insert()
    suspend fun insertTask(todoModel: TodoModel):Long

    @Query("Select * from TodoModel")
    fun getTask():LiveData<List<TodoModel>>

    @Query("Update TodoModel Set isPending = 0 where id=:uid")
    fun finishTask(uid:Long)

    @Query("Delete from TodoModel where id=:uid")
    fun deleteTask(uid:Long)
}