package uz.abdurashidov.dictionary.home.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.abdurashidov.dictionary.common.Resource
import uz.abdurashidov.dictionary.di.IoDispatcher
import uz.abdurashidov.dictionary.home.data.remote.ApiService
import uz.abdurashidov.dictionary.home.data.remote.model.DefinitionResponseModel
import javax.inject.Inject

class DefinitionRepositoryImp @Inject constructor(
    private val apiService: ApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : DefinitionRepository {
    override suspend fun getDefinition(word: String): Flow<Resource<List<DefinitionResponseModel>>> =
        flow {
            emit(Resource.Loading())
            when (val response = apiService.getDefinition(word = word)) {
                is Resource.Error -> {
                    emit(Resource.Error("Problem!!!"))
                }

                is Resource.Loading -> {
                    emit(Resource.Loading())
                }

                is Resource.Success -> {
                    val definitionResponse = response.data
                    emit(Resource.Success(definitionResponse))
                }
            }
        }.flowOn(ioDispatcher)
}