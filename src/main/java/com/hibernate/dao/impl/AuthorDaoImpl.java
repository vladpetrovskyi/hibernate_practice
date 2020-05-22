package com.hibernate.dao.impl;

import com.hibernate.dao.AuthorDao;
import com.hibernate.exceptions.DataProcessingException;
import com.hibernate.lib.Dao;
import com.hibernate.models.Author;
import com.hibernate.util.HibernateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class AuthorDaoImpl implements AuthorDao {

    private static final Logger LOGGER = LogManager.getLogger(BookDaoImpl.class);

    @Override
    public Author add(Author author) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Long authorId = (Long) session.save(author);
            transaction.commit();
            LOGGER.info(author + "is inserted into DB.");
            author.setId(authorId);
            return author;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Could not insert author entity.", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
