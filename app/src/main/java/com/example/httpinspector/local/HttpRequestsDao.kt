package com.example.httpinspector.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.httpinspector.model.HttpRequest

@Dao
interface HttpRequestsDao {

    @Insert
    suspend fun insertRequest(request: HttpRequest): Long?

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateRequest(request: HttpRequest): Int

    @Delete
    suspend fun deleteRequest(request: HttpRequest)

    @Query("SELECT * FROM requests WHERE isSynced = 0")
    suspend fun getNotSyncedRequests(): List<HttpRequest>

    @Query("SELECT * FROM requests")
    suspend fun getRequests(): List<HttpRequest>

    @Query("UPDATE requests SET isSynced = 1")
    suspend fun markAsSynced()

}