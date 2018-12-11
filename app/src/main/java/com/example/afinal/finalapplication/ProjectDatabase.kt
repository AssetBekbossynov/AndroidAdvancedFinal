package com.example.afinal.finalapplication

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.afinal.finalapplication.dao.ContactDao
import com.example.afinal.finalapplication.dao.GroupDao
import com.example.afinal.finalapplication.models.Contact
import com.example.afinal.finalapplication.models.Group

@Database(entities = arrayOf(Contact::class, Group::class), version = 1)
abstract class ProjectDatabase: RoomDatabase() {
    abstract fun contactDao() : ContactDao
    abstract fun groupDao() : GroupDao
}