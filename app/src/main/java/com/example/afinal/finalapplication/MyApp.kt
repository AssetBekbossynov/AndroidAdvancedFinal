package com.example.afinal.finalapplication

import android.arch.persistence.room.Room
import android.support.multidex.MultiDexApplication
import com.example.afinal.finalapplication.di.finalApp
import org.koin.android.ext.android.startKoin

class MyApp: MultiDexApplication() {

    lateinit var database: ProjectDatabase

    override fun onCreate() {
        super.onCreate()
        instance = this

        database = Room.databaseBuilder(applicationContext,
                ProjectDatabase::class.java,
                "ProjectDatabase")
                .fallbackToDestructiveMigration()
                .build()

        startKoin(this, finalApp)
    }

    companion object {
        @JvmStatic var instance: MyApp? = null
            private set
    }
}