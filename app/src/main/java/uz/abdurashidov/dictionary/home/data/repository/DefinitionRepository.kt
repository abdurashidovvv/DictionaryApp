package uz.abdurashidov.dictionary.home.data.repository

import kotlinx.coroutines.flow.Flow
import uz.abdurashidov.dictionary.common.Resource
import uz.abdurashidov.dictionary.home.data.remote.model.DefinitionResponseModel

interface DefinitionRepository {

    suspend fun getDefinition(word: String): Flow<Resource<List<DefinitionResponseModel>>>
}