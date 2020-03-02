package com.dev.cinema.model.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class CinemaHallRequestDto {
    @Min(10)
    private Integer capacity;
    @NotEmpty
    private String description;

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
