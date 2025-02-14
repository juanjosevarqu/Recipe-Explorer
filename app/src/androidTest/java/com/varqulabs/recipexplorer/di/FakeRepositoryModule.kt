package com.varqulabs.recipexplorer.di

import com.varqulabs.recipexplorer.domain.repository.MealsRepository
import com.varqulabs.recipexplorer.domain.usecase.GetRecipeById
import com.varqulabs.recipexplorer.domain.usecase.GetRecipesByFirstLetter
import com.varqulabs.recipexplorer.domain.usecase.GetRecipesByName
import com.varqulabs.recipexplorer.fakes.FakeMealsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class, ApiModule::class]
)
object FakeRepositoryModule {

    @Provides
    @Singleton
    fun provideFakeMealsRepository(): MealsRepository {
        return FakeMealsRepository()
    }

    @Provides
    @Singleton
    fun provideGetRecipesByNameUseCase(repository: MealsRepository): GetRecipesByName {
        return GetRecipesByName(repository)
    }

    @Provides
    @Singleton
    fun provideGetRecipesByFirstLetterUseCase(repository: MealsRepository): GetRecipesByFirstLetter {
        return GetRecipesByFirstLetter(repository)
    }

    @Provides
    @Singleton
    fun provideGetRecipeByIdUseCase(repository: MealsRepository): GetRecipeById {
        return GetRecipeById(repository)
    }
}


