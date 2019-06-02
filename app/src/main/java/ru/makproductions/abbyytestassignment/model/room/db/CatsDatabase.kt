package ru.makproductions.abbyytestassignment.model.room.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import ru.makproductions.abbyytestassignment.model.room.dao.CatsDao
import ru.makproductions.abbyytestassignment.model.room.entity.RoomCat


@Database(
    entities = [RoomCat::class],
    version = 1,
    exportSchema = false
)
abstract class CatsDatabase : RoomDatabase() {

    companion object {
        private val DB_NAME = "CatsDatabase.db"
        fun create(context: Context) {
            instance = Room.databaseBuilder<CatsDatabase>(context, CatsDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration().build()

        }

        @Volatile
        private lateinit var instance: CatsDatabase

        @Synchronized
        fun getInstance(): CatsDatabase? {
            instance.let {
                return it
            }
            throw NullPointerException("Database has not been created. Please call create()")
        }
    }


    abstract fun getCatsDao(): CatsDao
}