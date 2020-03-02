package com.dev.cinema.model.dto.response;

import javax.validation.constraints.NotEmpty;

public class TicketResponseDto {
    @NotEmpty
    private String cinemaHall;
    @NotEmpty
    private String showTime;
    @NotEmpty
    private String movie;
    private Long userId;

    public String getCinemaHall() {
        return cinemaHall;
    }

    public void setCinemaHall(String cinemaHall) {
        this.cinemaHall = cinemaHall;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
