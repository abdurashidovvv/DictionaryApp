package uz.abdurashidov.dictionary.home.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import uz.abdurashidov.dictionary.home.data.repository.DefinitionRepository
import uz.abdurashidov.dictionary.home.presentation.uistate.DefinitionUiState
import javax.inject.Inject

class DictionaryViewModel @Inject constructor(
    private val definitionRepository: DefinitionRepository
) : ViewModel() {

    private val _definitionUiState = MutableStateFlow(DefinitionUiState())
    val definitionUiState: StateFlow<DefinitionUiState> = _definitionUiState.asStateFlow()

    private val _typeWord = mutableStateOf("")
    val typeWord: State<String> = _typeWord

    fun setTypeWord(typeWord: String) {
        _typeWord.value = typeWord
    }

    private val _eventFlow= MutableSharedFlow<Ui>()
}