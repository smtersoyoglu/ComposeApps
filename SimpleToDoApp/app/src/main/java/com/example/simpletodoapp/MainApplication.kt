package com.example.simpletodoapp

import android.app.Application
import androidx.room.Room
import com.example.simpletodoapp.room.TodoDatabase

// MainApplication sınıfı: Uygulama için ana sınıf. Uygulama başlatıldığında yapılacak işlemler.
class MainApplication : Application() {

    companion object {
        lateinit var todoDatabase: TodoDatabase // Veritabanı nesnesi
    }

    override fun onCreate() {
        super.onCreate()
        // Veritabanı oluşturuluyor.
        todoDatabase = Room.databaseBuilder(
            applicationContext,
            TodoDatabase::class.java,
            "todo_database"
        ).build()
    }
}
