package uz.abdurashidov.dictionary.home.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchTextFieldComponent(
    setWordToBeSearched: (String) -> Unit,
    searchWord: () -> Unit,
    typeWord: String
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = typeWord,
        onValueChange = { wordEntered ->
            setWordToBeSearched(wordEntered)
        },
        modifier = Modifier
            .fillMaxWidth()
            .semantics { contentDescription = "SearchTextField" },
        placeholder = {
            Text(text = "Search here")
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Search, 
                contentDescription = null
            )
        },
        trailingIcon = {
            if(typeWord.isNotEmpty()){
                Icon(
                    imageVector = Icons.Outlined.Clear,
                    contentDescription = null
                )
            }
        },
        shape = RoundedCornerShape(4.dp),
        colors = TextFieldDefaults
            .textFieldColors(
                backgroundColor =  Color(0xFFEBE7E7),
                unfocusedIndicatorColor = Color(0xFFEBE7E7),
                focusedIndicatorColor =  Color(0xFF4C7AF2)
            ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                searchWord()
                keyboardController?.hide()
                focusManager.clearFocus()
            }
        )
    )
}