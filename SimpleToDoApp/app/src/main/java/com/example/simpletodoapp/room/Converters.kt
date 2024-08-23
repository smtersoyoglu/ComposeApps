package com.example.simpletodoapp.room

import androidx.room.TypeConverter
import java.util.Date

// Converters sınıfı: Date ve Long türleri arasında dönüşüm yapmak için kullanılır.
class Converters {

    @TypeConverter // Date türünden Long türüne dönüşüm sağlar.
    fun fromDate(date : Date) : Long{
        return date.time            // Date'in time değerini döner.
    }

    @TypeConverter // Long türünden Date türüne dönüşüm sağlar.
    fun toDate(time : Long) : Date{
        return Date(time)           // Long değeri bir Date nesnesine dönüştürülür.
    }
}
