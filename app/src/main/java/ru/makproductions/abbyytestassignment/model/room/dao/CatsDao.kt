package ru.makproductions.abbyytestassignment.model.room.dao

import android.arch.persistence.room.*
import ru.makproductions.abbyytestassignment.model.room.entity.RoomCat

@Dao
interface CatsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(newsItem: RoomCat)

    @Update
    fun update(newsItem: RoomCat)

    @Delete
    fun delete(newsItem: RoomCat)

    @Query("SELECT * FROM roomcat WHERE id = :id LIMIT 1")
    fun findRoomCat(id: Int): RoomCat

    @Query("SELECT * FROM roomcat")
    fun findAllCats(): List<RoomCat>
}