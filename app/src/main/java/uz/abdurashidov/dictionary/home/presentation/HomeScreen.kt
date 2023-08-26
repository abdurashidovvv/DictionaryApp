package uz.abdurashidov.dictionary.home.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.collectLatest
import uz.abdurashidov.dictionary.R
import uz.abdurashidov.dictionary.common.UiEvents
import uz.abdurashidov.dictionary.home.data.remote.model.Meaning
import uz.abdurashidov.dictionary.home.presentation.components.SearchTextFieldComponent
import uz.abdurashidov.dictionary.home.presentation.uistate.DefinitionUiState
import uz.abdurashidov.dictionary.ui.theme.DictionaryTheme

@Composable
fun HomeScreen(
    dictionaryViewModel: DictionaryViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        dictionaryViewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvents.SnackBarEvent -> {
                    scaffoldState.snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    val definitionUiState = dictionaryViewModel.definitionUiState.collectAsState().value
    val typeWord = dictionaryViewModel.typeWord.value
    val definitions =
        if (definitionUiState.definition?.isNotEmpty() == true) definitionUiState.definition[0].meanings
            ?: emptyList()
        else emptyList()


    DictionaryTheme {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        fontSize = 16.sp,
                                        fontFamily = FontFamily(Font(R.font.nunitosans_regular)),
                                        color = Color.White
                                    )
                                ) {
                                    append("Dictionary")
                                }
                            }
                        )
                    },
                    backgroundColor = Color(0xFF4C7AF2)
                )
            }
        ) { paddingValues ->
            HomeContent()
        }
    }
}

@Composable
fun HomeContent(
    definitionUiState: DefinitionUiState,
    typeWord: String,
    setWordToBeSearched: (String) -> Unit,
    searchWord: () -> Unit,
    meanings: List<Meaning>,
    paddingValues: PaddingValues = PaddingValues(0.dp)
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        LazyColumn(contentPadding = PaddingValues(14.dp)){
            item {

            }
        }
    }
}