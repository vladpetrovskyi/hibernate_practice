package com.hibernate.service.impl;

import com.hibernate.dao.GenreDao;
import com.hibernate.lib.Inject;
import com.hibernate.lib.Service;
import com.hibernate.models.Genre;
import com.hibernate.service.GenreService;

@Service
public class GenreServiceImpl implements GenreService {

    @Inject
    private GenreDao genreDao;

    @Override
    public Genre add(Genre genre) {
        return genreDao.add(genre);
    }
}
