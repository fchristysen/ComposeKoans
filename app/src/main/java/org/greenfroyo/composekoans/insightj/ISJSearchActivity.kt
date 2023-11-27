package org.greenfroyo.composekoans.insightj

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import org.greenfroyo.composekoans.R
import org.greenfroyo.composekoans.insightj.model.Book

class ISJSearchActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            screen()
        }
    }
}


@Composable
private fun screen(viewModel: ISJSearchVM = viewModel(), modifier: Modifier = Modifier) {
    val state by viewModel.state.collectAsState()
    _screen(state = state,
        onKeywordChanged = {newValue -> viewModel.setKeyword(newValue)},
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun _screen(
    @PreviewParameter(PPScreen::class) state: ISJSearchState,
    onKeywordChanged: (text: String) -> Unit = { },
    modifier: Modifier = Modifier
){
    InsightJTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column {
                SearchBar(searchKeywords = state.searchKeywords,
                    onKeywordChanged = onKeywordChanged,
                    modifier = Modifier.fillMaxWidth())
                BookList(books = state.getFilteredBooks())
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchBar(searchKeywords: String = "",
                      onKeywordChanged: (text: String) -> Unit = { },
                      modifier: Modifier = Modifier) {
    Row(modifier = modifier
        .padding(24.dp)
        .fillMaxWidth()) {
        TextField(
            value = searchKeywords,
            onValueChange = onKeywordChanged,
            leadingIcon = { Image(painter = painterResource(
                id = R.drawable.ic_search_24),
                contentDescription = "Search Icon",
                colorFilter = ColorFilter.tint(Color(0xFF666462))
            )},
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                containerColor = Color(0xFFEAE9E5)
            ),
            trailingIcon = { Image(painter = painterResource(
                id = R.drawable.ic_cancel_24),
                contentDescription = "Clear Text",
                modifier = Modifier.clickable {
                    onKeywordChanged("")
                },
                colorFilter = ColorFilter.tint(Color(0xFF666462))
            )},
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun BookList(books: List<Book> = listOf(), modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        itemsIndexed(books) { index, book ->
            if (index != 0) {
                Divider(
                    modifier = Modifier.padding(horizontal = 32.dp),
                    color = divider,
                    thickness = 1.dp
                )
            }
            BookListItem(book = book)
        }
    }
}

@Composable
private fun BookListItem(
    book: Book,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(book.cover)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.isj_book_cover_placeholder),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .size(82.dp)
                .clip(RoundedCornerShape(16.dp)),
            colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0.1f) })

        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier) {
            Text(
                text = book.title,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = book.author,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

class PPScreen : PreviewParameterProvider<ISJSearchState> {
    private val books = listOf(
        Book(
            cover = "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1684638853i/2429135.jpg",
            title = "The Girl with the Dragon Tattoo",
            author = "Stieg Larsson",
        ),
        Book(
            cover = "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1684638853i/2429135.jpg",
            title = "The Girl with the Dragon Tattoo",
            author = "Stieg Larsson",
        ),
        Book(
            cover = "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1684638853i/2429135.jpg",
            title = "The Girl with the Dragon Tattoo",
            author = "Stieg Larsson",
        ),
    )

    override val values = sequenceOf(
        ISJSearchState(
            searchKeywords = "Tatoo",
            books = books
        )
    )
}