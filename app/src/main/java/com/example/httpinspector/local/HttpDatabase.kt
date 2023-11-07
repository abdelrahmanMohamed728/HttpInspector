package com.example.httpinspector.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.httpinspector.model.HttpRequest

@Database(entities = [HttpRequest::class], version = 1, exportSchema = false)
@TypeConverters(HttpConverter::class)
abstract class HttpDatabase : RoomDatabase() {

    abstract fun requestsDao(): HttpRequestsDao

    companion object {
        @Volatile
        private var INSTANCE: HttpDatabase? = null
        private const val DATABASE_ID = "http_inspector_db"
        fun getDatabase(context: Context): HttpDatabase {
            return INSTANCE ?: synchronized(this) {
                val db = Room.databaseBuilder(
                    context.applicationContext,
                    HttpDatabase::class.java, DATABASE_ID
                ).build()

                INSTANCE = db
                db
            }
        }
    }

}