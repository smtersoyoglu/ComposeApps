package com.example.simpletodoapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.simpletodoapp.entity.Todo

// Veritabanı sınıfı: Todo veritabanı tanımlaması.
@Database(entities = [Todo::class], version = 1) // Veritabanı entity'leri ve versiyonu tanımlanır.
@TypeConverters(Converters::class) // Veritabanında tip dönüştürücüler kullanılır.
abstract class TodoDatabase  : RoomDatabase() {

    abstract fun getTodoDao() : TodoDao  // TodoDao arayüzünü sağlayan soyut fonksiyon.

}
