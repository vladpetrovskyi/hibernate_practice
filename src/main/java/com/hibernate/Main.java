package com.hibernate;

import com.hibernate.lib.Injector;
import com.hibernate.models.Author;
import com.hibernate.models.Book;
import com.hibernate.models.Genre;
import com.hibernate.service.AuthorService;
import com.hibernate.service.BookService;
import com.hibernate.service.GenreService;

public class Main {

    private static final Injector INJECTOR = Injector.getInstance("com.hibernate");

    public static void main(String[] args) {

        Genre genre = new Genre();
        genre.setGenre("Novel");
        GenreService genreService = (GenreService) INJECTOR.getInstance(GenreService.class);
        genre = genreService.add(genre);

        Author author = new Author();
        author.setName("Lev");
        author.setSurname("Tolstoi");
        author.setYearOfBirth(1828);
        AuthorService authorService = (AuthorService) INJECTOR.getInstance(AuthorService.class);
        author = authorService.add(author);

        Book book = new Book();
        book.setAuthor(author);
        book.setGenre(genre);
        book.setTitle("War and Peace");
        book.setPages(1225);
        BookService bookService = (BookService) INJECTOR.getInstance(BookService.class);
        bookService.add(book);

        System.out.println("By Title: " + bookService.getBookByTitle("War and Peace"));
        System.out.println("By Author: " + bookService.getBooksByAuthor(author));
        System.out.println("By Genre: " + bookService.getBooksByGenre(genre));
    }
}
