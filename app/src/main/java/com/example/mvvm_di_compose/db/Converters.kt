package com.example.mvvm_di_compose.db

import androidx.room.TypeConverter
import com.example.mvvm_di_compose.model.User
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun fromJsonToUser(value: String?): User? {
        return value?.let { Gson().fromJson(it, User::class.java) }
    }

    @TypeConverter
    fun userToJson(user: User?): String? {
        return Gson().toJson(user)
    }
}