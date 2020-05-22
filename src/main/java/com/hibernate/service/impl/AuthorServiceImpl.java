package com.hibernate.service.impl;

import com.hibernate.dao.AuthorDao;
import com.hibernate.lib.Inject;
import com.hibernate.lib.Service;
import com.hibernate.models.Author;
import com.hibernate.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Inject
    private AuthorDao authorDao;

    @Override
    public Author add(Author author) {
        return authorDao.add(author);
    }
}
