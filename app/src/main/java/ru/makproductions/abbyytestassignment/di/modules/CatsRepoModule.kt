package ru.makproductions.abbyytestassignment.di.modules

import dagger.Module
import dagger.Provides
import ru.makproductions.abbyytestassignment.model.cache.ICache
import ru.makproductions.abbyytestassignment.model.repo.CatsRepo

@Module
class CatsRepoModule {
    private var catsRepo: CatsRepo? = null
    @Provides
    fun getCatsRepo(cache: ICache): CatsRepo {
        if (catsRepo == null) catsRepo = CatsRepo(cache)
        return catsRepo!!
    }
}