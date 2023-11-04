package com.example.httpinspector.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.httpinspector.model.HttpRequest

@Database(entities = [HttpRequest::class], version = 1, exportSchema = false)
abstract class HttpDatabase: RoomDatabase() {

    abstract fun requestsDao(): HttpRequestsDao

    companion object {
        private const val DATABASE_ID = "http_inspector_db"
        fun create(applicationContext: Context): HttpDatabase {
            return Room.databaseBuilder(applicationContext, HttpDatabase::class.java, DATABASE_ID)
                .fallbackToDestructiveMigration()
                .build()
        }
    }

}