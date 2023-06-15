package com.codingtask.simpletodotask

import androidx.room.Entity
import androidx.room.PrimaryKey

// we are making list for each task
@Entity
data class TodoModel(
    var title:String,
    var description:String?,
    var isPending: Boolean,
    @PrimaryKey(autoGenerate = true)
    var id:Long = 0
)
