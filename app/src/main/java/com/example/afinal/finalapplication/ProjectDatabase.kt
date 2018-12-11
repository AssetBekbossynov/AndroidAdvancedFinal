package com.example.afinal.finalapplication

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.afinal.finalapplication.dao.ContactDao
import com.example.afinal.finalapplication.dao.GroupDao

@Database(entities = arrayOf(), version = 1)
abstract class ProjectDatabase: RoomDatabase() {
    abstract fun contactDao() : ContactDao
    abstract fun groupDao() : GroupDao
}