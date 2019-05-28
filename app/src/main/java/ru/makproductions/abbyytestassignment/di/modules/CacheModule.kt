package ru.makproductions.abbyytestassignment.di.modules

import dagger.Module
import dagger.Provides
import ru.makproductions.abbyytestassignment.model.cache.ICache
import ru.makproductions.abbyytestassignment.model.room.cache.RoomCache

@Module
class CacheModule {
    @Provides
    fun getCache(): ICache {
        return RoomCache()
    }
}
