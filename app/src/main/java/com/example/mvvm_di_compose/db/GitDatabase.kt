package com.example.mvvm_di_compose.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mvvm_di_compose.model.GitIssueItem
import com.example.mvvm_di_compose.model.comment.Comment

@Database(entities = [GitIssueItem::class, Comment::class], version = 1)
@TypeConverters(Converters::class)
abstract class GitDatabase : RoomDatabase() {

    abstract fun gitDao() : GitDao

    companion object{
        @Volatile
        private var INSTANCE: GitDatabase? = null

        fun getDatabase(context: Context): GitDatabase {
            if (INSTANCE == null) {
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(context,
                        GitDatabase::class.java,
                        "gitDB")
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}