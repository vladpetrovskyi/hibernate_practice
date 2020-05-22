package com.hibernate.service;

import com.hibernate.models.Author;
import com.hibernate.models.Book;
import com.hibernate.models.Genre;
import java.util.List;

public interface BookService {
    Book add(Book book);

    Book getBookByTitle(String title);

    List<Book> getBooksByAuthor(Author author);

    List<Book> getBooksByGenre(Genre genre);
}
