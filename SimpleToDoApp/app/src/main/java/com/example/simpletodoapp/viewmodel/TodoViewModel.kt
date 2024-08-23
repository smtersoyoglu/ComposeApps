package com.example.simpletodoapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpletodoapp.MainApplication
import com.example.simpletodoapp.entity.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date

// TodoViewModel: Yapılacak işler listesi için ViewModel sınıfı.
class TodoViewModel : ViewModel() {

    // Veritabanı erişimi için DAO nesnesi alınıyor.
    private val todoDao = MainApplication.todoDatabase.getTodoDao()

    // Yapılacak işler listesi LiveData olarak saklanır.
    val todoList: LiveData<List<Todo>> = todoDao.getAllTodo()

    // Yeni bir yapılacak iş eklemek için fonksiyon.
    fun addTodo(title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.addTodo(Todo(title = title, createdAt = Date())) // Mevcut zamanla yeni bir Todo ekler.
        }
    }

    // Bir yapılacak işi silmek için fonksiyon.
    fun deleteTodo(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val todo = todoList.value?.find { it.id == id }
            if (todo != null) {
                todoDao.deleteTodo(todo)
            }
        }
    }

    // Bir yapılacak işi güncellemek için fonksiyon.
    fun updateTodoTitle(id: Int, newTitle: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val todo = todoList.value?.find { it.id == id }
            if (todo != null) {
                todo.title = newTitle
                todo.createdAt = Date() // createdAt değerini güncel zamana ayarlıyoruz
                todoDao.updateTodoTitle(todo)
            }
        }
    }
}