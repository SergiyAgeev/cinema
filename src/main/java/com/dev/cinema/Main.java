package com.dev.cinema;

import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.lib.Injector;
import com.dev.cinema.model.Movie;
import com.dev.cinema.service.MovieService;
import org.apache.log4j.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class);
    private static Injector injector = Injector.getInstance("com.dev.cinema");


    public static void main(String[] args) throws DataProcessingException {
        Movie movie = new Movie();
        movie.setTittle("Fast and Furious");
        MovieService movieService = (MovieService)
                injector.getInstance(MovieService.class);
        movieService.add(movie);
        try {
            movieService.getAll().forEach(System.out::println);
        } catch (DataProcessingException e) {
            LOGGER.error("can't get all movies " + e);
        }
    }


}
