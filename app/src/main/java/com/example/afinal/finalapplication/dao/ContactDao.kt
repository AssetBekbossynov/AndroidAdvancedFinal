package com.example.afinal.finalapplication.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.afinal.finalapplication.models.Contact

@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addContact(contact: Contact)

    @Query("SELECT * from contact")
    fun getAllContact(): List<Contact>

    @Query("SELECT * from contact where id=:id")
    fun getContactById(id: Int): List<Contact>

    @Query("SELECT COUNT(id) FROM contact")
    fun getCount(): Int
}