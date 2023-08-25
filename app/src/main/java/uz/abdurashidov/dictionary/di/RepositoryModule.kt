package uz.abdurashidov.dictionary.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import uz.abdurashidov.dictionary.home.data.remote.ApiService
import uz.abdurashidov.dictionary.home.data.repository.DefinitionRepository
import uz.abdurashidov.dictionary.home.data.repository.DefinitionRepositoryImp
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDefinitionRepository(
        apiService: ApiService,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): DefinitionRepository {
        return DefinitionRepositoryImp(
            apiService,
            ioDispatcher
        )
    }
}