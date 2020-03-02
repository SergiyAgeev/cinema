package com.dev.cinema.controller;

import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.request.ShoppingCartRequestDto;
import com.dev.cinema.model.dto.response.ShoppingCartResponseDto;
import com.dev.cinema.model.dto.response.TicketResponseDto;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;

import java.security.Principal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shoppingcarts")
public class ShoppingCartController {
    private ShoppingCartService shoppingCartService;
    private UserService userService;
    private MovieSessionService movieSessionService;

    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  UserService userService,
                                  MovieSessionService movieSessionService) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.movieSessionService = movieSessionService;
    }

    @PostMapping("/addmoviesession")
    public ShoppingCartResponseDto addToShoppingCart(@RequestBody @Valid ShoppingCartRequestDto
                                                             shoppingCartRequestDto,
                                                     Principal principal) {
        MovieSession movieSession =
                movieSessionService.getById(shoppingCartRequestDto.getMovieSessionId());
        User user = userService.findByEmail(principal.getName());
        shoppingCartService.addSession(movieSession, user);
        return getShoppingCartResponseDto(shoppingCartService.getByUser(user));
    }

    @GetMapping("/byuser{userId}")
    public ShoppingCartResponseDto getShoppingCartByUser(@PathVariable Long userId,
                                                         Principal principal) {
        return getShoppingCartResponseDto(shoppingCartService
                .getByUser(userService.findByEmail(principal.getName())));
    }

    private ShoppingCartResponseDto getShoppingCartResponseDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto shoppingCartResponseDto = new ShoppingCartResponseDto();
        shoppingCartResponseDto.setTickets(getListTicketsResponseDto(shoppingCart));
        shoppingCartResponseDto.setUserId(shoppingCart.getUser().getId());
        return shoppingCartResponseDto;
    }

    private List<TicketResponseDto> getListTicketsResponseDto(ShoppingCart shoppingCart) {
        return shoppingCart.getTickets().stream()
                .map(this::getTicketResponseDto)
                .collect(Collectors.toList());
    }

    private TicketResponseDto getTicketResponseDto(Ticket ticket) {
        TicketResponseDto ticketResponseDto = new TicketResponseDto();
        ticketResponseDto.setUserId(ticket.getUser().getId());
        ticketResponseDto.setCinemaHall(ticket.getMovieSession().getCinemaHall().getDescription());
        ticketResponseDto.setMovie(ticket.getMovieSession().getMovie().getTitle());
        ticketResponseDto.setShowTime(ticket.getMovieSession().getShowTime()
                .format(DateTimeFormatter.ISO_LOCAL_TIME));
        return ticketResponseDto;
    }

}
