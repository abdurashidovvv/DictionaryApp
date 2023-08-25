package uz.abdurashidov.dictionary.home.presentation.uistate

import uz.abdurashidov.dictionary.home.data.remote.model.DefinitionResponseModel

data class DefinitionUiState(
    val definition: List<DefinitionResponseModel>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)