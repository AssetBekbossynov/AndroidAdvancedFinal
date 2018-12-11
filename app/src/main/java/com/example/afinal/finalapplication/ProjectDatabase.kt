package com.example.afinal.finalapplication

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = arrayOf(), version = 1)
abstract class ProjectDatabase: RoomDatabase() {

}