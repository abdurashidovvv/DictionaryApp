package uz.abdurashidov.dictionary.common

sealed class UiEvents {
    data class SnackBarEvent(val message: String) : UiEvents()
}