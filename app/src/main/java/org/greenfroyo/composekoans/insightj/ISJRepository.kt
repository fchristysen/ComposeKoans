package org.greenfroyo.composekoans.insightj

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.greenfroyo.composekoans.insightj.model.Book

class ISJRepository {
    fun getBooks(): Flow<List<Book>> = flow{
        delay(1000)
        emit(_books())
    }

    private fun _books() = listOf(
        Book(
            cover = "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1684638853i/2429135.jpg",
            title = "The Girl with the Dragon Tattoo",
            author = "Stieg Larsson",
        ),
        Book(
            cover = "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1638425885i/16299.jpg",
            title = "And Then There Were None",
            author = "Agatha Christie",
        ),
        Book(
            cover = "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1639587647i/960.jpg",
            title = "Angels & Demons",
            author = "Dan Brown",
        ),
        Book(
            cover = "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1386605169i/17899948.jpg",
            title = "Rebecca",
            author = "Daphne du Maurier",
        ),
        Book(
            cover = "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1424931136i/168642.jpg",
            title = "In Cold Blood",
            author = "Truman Capote",
        ),
        Book(
            cover = "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1394988109i/22034.jpg",
            title = "The Godfather",
            author = "Mario Puzo",
        ),
        Book(
            cover = "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1457810586i/12232938.jpg",
            title = "The Lovely Bones",
            author = "Alice Sebold",
        ),
        Book(
            cover = "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1554086139i/19288043.jpg",
            title = "Gone Girl",
            author = "Gillian Flynn",
        ),
        Book(
            cover = "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1415375471i/119073.jpg",
            title = "The Name of the Rose",
            author = "Umberto Eco",
        ),
        Book(
            cover = "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1329269081i/21686.jpg",
            title = "Shutter Island",
            author = "Dennis Lehane",
        ),
    )

}