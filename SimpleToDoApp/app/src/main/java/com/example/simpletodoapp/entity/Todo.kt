package com.example.simpletodoapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

// Todo veri sınıfı: Her bir yapılacak işin (todo) id, başlık ve oluşturulma tarihini saklar.
@Entity(tableName = "todo_table")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title: String,
    var createdAt: Date,
)
