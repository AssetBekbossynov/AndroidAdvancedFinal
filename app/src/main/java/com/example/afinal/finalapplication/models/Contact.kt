package com.example.afinal.finalapplication.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

@Entity(foreignKeys = arrayOf(ForeignKey(entity = Group::class, parentColumns = arrayOf("id"), childColumns = arrayOf("groupId"))))
data class Contact(@PrimaryKey var id: Int,
                   var name: String,
                   var mobilePhone: String,
                   var homePhone: String,
                   var workPhone: String,
                   var image: String,
                   var groupId: Int) {
}