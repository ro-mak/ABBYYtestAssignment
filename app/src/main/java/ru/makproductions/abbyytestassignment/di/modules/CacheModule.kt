package ru.makproductions.abbyytestassignment.di.modules

import dagger.Module
import dagger.Provides
import ru.makproductions.abbyytestassignment.model.cache.ICache
import ru.makproductions.abbyytestassignment.model.room.cache.RoomCache

@Module
class CacheModule {
    private var roomCache: RoomCache? = null
    @Provides
    fun getCache(): ICache {
        if (roomCache == null) roomCache = RoomCache()
        return roomCache!!
    }
}
