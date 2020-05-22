package com.hibernate.dao.impl;

import com.hibernate.dao.GenreDao;
import com.hibernate.exceptions.DataProcessingException;
import com.hibernate.lib.Dao;
import com.hibernate.models.Genre;
import com.hibernate.util.HibernateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class GenreDaoImpl implements GenreDao {

    private static final Logger LOGGER = LogManager.getLogger(BookDaoImpl.class);

    @Override
    public Genre add(Genre genre) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Long genreId = (Long) session.save(genre);
            transaction.commit();
            LOGGER.info(genre + "is inserted into DB.");
            genre.setId(genreId);
            return genre;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Could not insert genre entity.", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
