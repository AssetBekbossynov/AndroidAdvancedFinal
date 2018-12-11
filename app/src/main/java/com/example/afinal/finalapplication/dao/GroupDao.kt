package com.example.afinal.finalapplication.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.afinal.finalapplication.models.Group

@Dao
interface GroupDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addGroup(group: Group)

    @Query("SELECT * from `group`")
    fun getAllGroup(): List<Group>

    @Query("SELECT id from `group`")
    fun getAllId(): List<Int>

    @Query("SELECT * from `group` where id=:id")
    fun getGroupById(id: Int): List<Group>
}