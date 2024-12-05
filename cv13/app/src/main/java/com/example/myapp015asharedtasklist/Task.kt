package com.example.myapp015asharedtasklist

data class Task(
    var id: String = "",
    var name: String = "",
    var isCompleted: Boolean = false,
    var assignedTo: String = ""
)
