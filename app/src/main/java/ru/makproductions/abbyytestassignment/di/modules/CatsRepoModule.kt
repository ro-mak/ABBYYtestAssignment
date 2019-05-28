package ru.makproductions.abbyytestassignment.di.modules

import dagger.Module
import dagger.Provides
import ru.makproductions.abbyytestassignment.model.repo.CatsRepo

@Module
class CatsRepoModule {
    @Provides
    fun getCatsRepo() = CatsRepo()
}