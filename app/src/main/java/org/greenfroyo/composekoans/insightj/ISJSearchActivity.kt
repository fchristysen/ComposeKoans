package org.greenfroyo.composekoans.insightj

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
            Screen()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Screen(modifier: Modifier = Modifier, viewModel: ISJSearchVM = viewModel()) {
    val state by viewModel.state.collectAsState()
    InsightJTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            BookList(books = state.books)
        }
    }
}

@Composable
private fun BookList(modifier: Modifier = Modifier, books: List<Book>) {
    LazyColumn(modifier = modifier) {
        itemsIndexed(books) { index, book ->
            if (index !=0) {
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

@Preview(showBackground = true)
@Composable
private fun BookListItem(
    @PreviewParameter(PPBook::class) book: Book,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = Modifier
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
                .clip(RoundedCornerShape(16.dp))

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

class PPBook: PreviewParameterProvider<Book> {
    override val values = sequenceOf(
        Book(
            cover = "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1684638853i/2429135.jpg",
            title = "The Girl with the Dragon Tattoo",
            author = "Stieg Larsson",
        ),
    )
}