package uz.abdurashidov.dictionary.home.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import uz.abdurashidov.dictionary.common.Resource
import uz.abdurashidov.dictionary.common.UiEvents
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

    private val _eventFlow = MutableSharedFlow<UiEvents>()
    val eventFlow: SharedFlow<UiEvents> = _eventFlow.asSharedFlow()

    fun getDefinition() {
        _definitionUiState.value =
            definitionUiState.value.copy(
                isLoading = true
            )

        val word = typeWord.value

        if (word.isNotEmpty()) {
            viewModelScope.launch {
                definitionRepository.getDefinition(word).collect { response ->
                    when (response) {
                        is Resource.Error -> {
                            _definitionUiState.value = definitionUiState.value.copy(
                                isLoading = false,
                                definition = emptyList()
                            )

                            _eventFlow.emit(
                                UiEvents.SnackBarEvent(
                                    message = response.message ?: "Something went wrong!"
                                )
                            )
                        }

                        is Resource.Success -> {
                            _definitionUiState.value = definitionUiState.value.copy(
                                isLoading = false,
                                definition = response.data
                            )
                        }

                        else -> definitionUiState
                    }
                }
            }
        } else {
            showErrorMessage()
        }
    }

    private fun showErrorMessage() {
        viewModelScope.launch {
            _definitionUiState.value =
                definitionUiState.value.copy(
                    isLoading = false
                )

            _eventFlow.emit(
                UiEvents.SnackBarEvent(
                    message = "Please enter a word"
                )
            )
        }
    }
}
