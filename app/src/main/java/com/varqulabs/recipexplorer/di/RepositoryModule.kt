package com.varqulabs.recipexplorer.di

import com.varqulabs.recipexplorer.data.remote.MealDbService
import com.varqulabs.recipexplorer.data.repository.MealsRepositoryImpl
import com.varqulabs.recipexplorer.domain.repository.MealsRepository
import com.varqulabs.recipexplorer.domain.usecase.GetRecipesByName
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMealsRepository(service: MealDbService): MealsRepository {
        return MealsRepositoryImpl(service)
    }

    @Provides
    @Singleton
    fun provideGetRecipesByNameUseCase(repository: MealsRepository): GetRecipesByName {
        return GetRecipesByName(repository)
    }

}