package com.dev.cinema;

import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.lib.Injector;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.service.AuthenticationService;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.security.sasl.AuthenticationException;
import org.apache.log4j.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class);
    private static Injector injector = Injector.getInstance("com.dev.cinema");

    public static void main(String[] args) throws DataProcessingException, AuthenticationException {
        MovieService movieService = (MovieService)
                injector.getInstance(MovieService.class);

        Movie movie = new Movie();
        movie.setTittle("Fast and Furious");
        movieService.add(movie);
        try {
            movieService.getAll().forEach(System.out::println);
        } catch (DataProcessingException e) {
            LOGGER.error("can't get all movies " + e);
        }

        CinemaHallService cinemaHallService = (CinemaHallService)
                injector.getInstance(CinemaHallService.class);

        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(100);
        cinemaHallService.add(cinemaHall);
        try {
            cinemaHallService.getAll().forEach(System.out::println);
        } catch (DataProcessingException e) {
            LOGGER.error("can't get all cinema halls" + e);
        }

        MovieSession movieSession = new MovieSession();
        movieSession.setCinemaHall(cinemaHall);
        movieSession.setMovie(movie);
        movieSession.setShowTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 30)));
        MovieSessionService movieSessionService = (MovieSessionService)
                injector.getInstance(MovieSessionService.class);
        movieSessionService.add(movieSession);

        System.out.println("AVAILABLE SESSIONS");
        movieSessionService.findAvailableSessions(movie.getId(), LocalDate.now())
                .forEach(System.out::println);

        AuthenticationService authenticationService = (AuthenticationService)
                injector.getInstance(AuthenticationService.class);
        authenticationService.register("sergiyageev@gmail.com", "123");

        authenticationService.login("sergiyageev@gmail.com", "123");
    }
}
