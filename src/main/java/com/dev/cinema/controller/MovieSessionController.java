package com.dev.cinema.controller;

import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.dto.request.MovieSessionRequestDto;
import com.dev.cinema.model.dto.response.MovieSessionResponseDto;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/moviesessions")
@RestController
public class MovieSessionController {

    private MovieSessionService movieSessionService;
    private MovieService movieService;
    private CinemaHallService cinemaHallService;

    public MovieSessionController(MovieSessionService movieSessionService,
                                  MovieService movieService, CinemaHallService cinemaHallService) {
        this.movieSessionService = movieSessionService;
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
    }

    @PostMapping
    public MovieSessionResponseDto createMovieSession(
            @RequestBody @Valid MovieSessionRequestDto movieSessionRequestDto) {
        MovieSession movieSession = getMovieSession(movieSessionRequestDto);
        return getMovieSessionResponseDto(movieSession);
    }

    @GetMapping("/available")
    public List<MovieSessionResponseDto> getAllMovieSessionResponseDto(@RequestParam Long movieId,
                                                                       @RequestParam String date) {
        return movieSessionService.findAvailableSessions(movieId, LocalDate.parse(date)).stream()
                .map(this::getMovieSessionResponseDto)
                .collect(Collectors.toList());
    }

    private MovieSession getMovieSession(MovieSessionRequestDto movieSessionRequestDto) {
        MovieSession movieSession = new MovieSession();
        movieSession.setShowTime(LocalDateTime.parse(movieSessionRequestDto.getShowTime()));
        movieSession.setMovie(movieService.getById(movieSessionRequestDto.getMovieId()));
        movieSession.setCinemaHall(cinemaHallService.getById(movieSessionRequestDto
                .getCinemaHallId()));
        movieSessionService.add(movieSession);
        return movieSession;
    }

    private MovieSessionResponseDto getMovieSessionResponseDto(MovieSession movieSession) {
        MovieSessionResponseDto movieSessionResponseDto = new MovieSessionResponseDto();
        movieSessionResponseDto.setCinemaHallId(movieSession.getCinemaHall().getId());
        movieSessionResponseDto.setMovieId(movieSession.getMovie().getId());
        movieSessionResponseDto.setShowTime(movieSession.getShowTime().toString());
        return movieSessionResponseDto;
    }
}
