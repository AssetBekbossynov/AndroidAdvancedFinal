package com.example.afinal.finalapplication.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Group(@PrimaryKey var id: Int,
                 var name: String,
                 var priority: String) {
}