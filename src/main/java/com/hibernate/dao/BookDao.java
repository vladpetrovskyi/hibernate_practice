package com.hibernate.dao;

import com.hibernate.models.Author;
import com.hibernate.models.Book;
import com.hibernate.models.Genre;
import java.util.List;
import java.util.Optional;

public interface BookDao {
    Book add(Book book);

    Optional<Book> getBookByTitle(String title);

    List<Book> getBooksByAuthor(Author author);

    List<Book> getBooksByGenre(Genre genre);
}
