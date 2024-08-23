package com.example.simpletodoapp.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import java.util.Date
import com.example.simpletodoapp.entity.Todo

@Dao
interface TodoDao {

    // Tüm yapılacak işleri tarihe göre azalan sırayla getirir.
    @Query("SELECT * FROM todo_table ORDER BY createdAt DESC")
    fun getAllTodo() : LiveData<List<Todo>>

    @Insert
    fun addTodo(todo: Todo)

    /*
    @Query("DELETE FROM Todo where id = id")
    suspend fun deleteTodo(todo: Todo)
     */

    @Delete
    fun deleteTodo(todo: Todo)

    @Update
    fun updateTodoTitle(todo: Todo)
}