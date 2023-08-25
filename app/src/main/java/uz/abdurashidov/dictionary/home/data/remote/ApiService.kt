package uz.abdurashidov.dictionary.home.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import uz.abdurashidov.dictionary.common.Resource
import uz.abdurashidov.dictionary.home.data.remote.model.Definition
import uz.abdurashidov.dictionary.home.data.remote.model.DefinitionResponseModel

interface ApiService {

    @GET("api/v2/entries/en/{word}")
    suspend fun getDefinition(@Path("word") word:String):Resource<List<DefinitionResponseModel>>
}