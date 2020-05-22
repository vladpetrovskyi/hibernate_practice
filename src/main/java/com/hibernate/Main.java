package com.hibernate;

import com.hibernate.lib.Injector;
import com.hibernate.models.Author;
import com.hibernate.models.Book;
import com.hibernate.models.Genre;
import com.hibernate.service.AuthorService;
import com.hibernate.service.BookService;
import com.hibernate.service.GenreService;
import java.util.List;

public class Main {

    private static final Injector INJECTOR = Injector.getInstance("com.hibernate");

    public static void main(String[] args) {

        Genre genreNovel = new Genre();
        genreNovel.setGenre("Novel");
        GenreService genreService = (GenreService) INJECTOR.getInstance(GenreService.class);
        genreNovel = genreService.add(genreNovel);

        Author authorTolstoi = new Author();
        authorTolstoi.setName("Lev");
        authorTolstoi.setSurname("Tolstoi");
        authorTolstoi.setYearOfBirth(1828);
        AuthorService authorService = (AuthorService) INJECTOR.getInstance(AuthorService.class);
        authorTolstoi = authorService.add(authorTolstoi);

        Book bookWarAndPeace = new Book();
        bookWarAndPeace.setAuthors(List.of(authorTolstoi));
        bookWarAndPeace.setGenre(genreNovel);
        bookWarAndPeace.setTitle("War and Peace");
        bookWarAndPeace.setPages(1225);
        BookService bookService = (BookService) INJECTOR.getInstance(BookService.class);
        bookService.add(bookWarAndPeace);

        Genre genreAdventure = new Genre();
        genreAdventure.setGenre("Adventure");
        genreAdventure = genreService.add(genreAdventure);

        Author authorZhulesVerne = new Author();
        authorZhulesVerne.setName("Zhules");
        authorZhulesVerne.setSurname("Verne");
        authorZhulesVerne.setYearOfBirth(1828);
        authorZhulesVerne = authorService.add(authorZhulesVerne);

        Book bookZhules = new Book();
        bookZhules.setAuthors(List.of(authorZhulesVerne));
        bookZhules.setGenre(genreAdventure);
        bookZhules.setTitle("Five Weeks in a Balloon");
        bookZhules.setPages(400);
        bookService.add(bookZhules);

        Author authorRowling = new Author();
        authorRowling.setName("Joanne");
        authorRowling.setSurname("Rowling");
        authorRowling.setYearOfBirth(1965);
        authorRowling = authorService.add(authorRowling);

        Book bookRowling = new Book();
        bookRowling.setAuthors(List.of(authorRowling));
        bookRowling.setGenre(genreNovel);
        bookRowling.setTitle("Harry Potter");
        bookRowling.setPages(300);
        bookService.add(bookRowling);

        System.out.println("By Title 1: " + bookService.getBookByTitle("War and Peace"));
        System.out.println("By Author 1: " + bookService.getBooksByAuthor(authorTolstoi));
        System.out.println("By Genre 1: " + bookService.getBooksByGenre(genreNovel));

        System.out.println("By Title 2: " + bookService.getBookByTitle("Five Weeks in a Balloon"));
        System.out.println("By Author 2: " + bookService.getBooksByAuthor(authorZhulesVerne));
        System.out.println("By Genre 2: " + bookService.getBooksByGenre(genreAdventure));

        System.out.println("By Title 3: " + bookService.getBookByTitle("Harry Potter"));
        System.out.println("By Author 3: " + bookService.getBooksByAuthor(authorRowling));
    }
}
