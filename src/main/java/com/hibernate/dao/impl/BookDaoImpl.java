package com.hibernate.dao.impl;

import com.hibernate.dao.BookDao;
import com.hibernate.exceptions.DataProcessingException;
import com.hibernate.lib.Dao;
import com.hibernate.models.Author;
import com.hibernate.models.Book;
import com.hibernate.models.Genre;
import com.hibernate.util.HibernateUtil;
import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class BookDaoImpl implements BookDao {

    private static final Logger LOGGER = LogManager.getLogger(BookDaoImpl.class);

    @Override
    public Book add(Book book) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Long bookId = (Long) session.save(book);
            transaction.commit();
            LOGGER.info(book + "is inserted into DB.");
            book.setId(bookId);
            return book;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Could not insert book entity.", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<Book> getBookByTitle(String title) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Book> criteriaQuery = cb.createQuery(Book.class);
            Root<Book> bookRoot = criteriaQuery.from(Book.class);
            criteriaQuery.select(bookRoot).where(cb.equal(bookRoot.get("title"), title));
            return session.createQuery(criteriaQuery).uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Error retrieving book by title.", e);
        }
    }

    @Override
    public List<Book> getBooksByAuthor(Author author) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Book> criteriaQuery = cb.createQuery(Book.class);
            Root<Book> bookRoot = criteriaQuery.from(Book.class);
            criteriaQuery.select(bookRoot).where(cb.equal(bookRoot.get("author"), author));
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Error retrieving books by author.", e);
        }
    }

    @Override
    public List<Book> getBooksByGenre(Genre genre) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Book> criteriaQuery = cb.createQuery(Book.class);
            Root<Book> bookRoot = criteriaQuery.from(Book.class);
            criteriaQuery.select(bookRoot).where(cb.equal(bookRoot.get("genre"), genre));
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Error retrieving books by genre.", e);
        }
    }
}
