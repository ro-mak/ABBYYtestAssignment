package ru.makproductions.abbyytestassignment.model.room.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class RoomCat(@PrimaryKey var id: Int, var name: String)