package com.hibernate.service.impl;

import com.hibernate.dao.BookDao;
import com.hibernate.lib.Inject;
import com.hibernate.lib.Service;
import com.hibernate.models.Author;
import com.hibernate.models.Book;
import com.hibernate.models.Genre;
import com.hibernate.service.BookService;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Inject
    private BookDao bookDao;

    @Override
    public Book add(Book book) {
        return bookDao.add(book);
    }

    @Override
    public Book getBookByTitle(String title) {
        return bookDao.getBookByTitle(title).get();
    }

    @Override
    public List<Book> getBooksByAuthor(Author author) {
        return bookDao.getBooksByAuthor(author);
    }

    @Override
    public List<Book> getBooksByGenre(Genre genre) {
        return bookDao.getBooksByGenre(genre);
    }
}
