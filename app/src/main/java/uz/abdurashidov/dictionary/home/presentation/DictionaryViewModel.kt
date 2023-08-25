package uz.abdurashidov.dictionary.home.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import uz.abdurashidov.dictionary.home.data.repository.DefinitionRepository
import javax.inject.Inject

class DictionaryViewModel @Inject constructor(
    private val definitionRepository: DefinitionRepository
):ViewModel(){

    private val _definitionUiState= MutableStateFlow()
}