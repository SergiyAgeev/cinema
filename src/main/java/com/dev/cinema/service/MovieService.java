package com.dev.cinema.service;

import java.util.List;

import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.model.Movie;

public interface MovieService {
    Movie add(Movie movie) throws DataProcessingException;

    List<Movie> getAll() throws DataProcessingException;
}
