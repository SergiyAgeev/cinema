package com.dev.cinema.controller;

import com.dev.cinema.model.Order;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.dto.response.OrderResponseDto;
import com.dev.cinema.model.dto.response.TicketResponseDto;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.UserService;

import java.security.Principal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private OrderService orderService;
    private UserService userService;

    public OrderController(OrderService orderService,
                           UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @PostMapping("/complete")
    public OrderResponseDto completeOrder(Authentication authentication) {
        Order order = orderService.completeOrder(
                userService.findByEmail(authentication.getName()));
        return getOrderResponseDto(order);
    }

    @GetMapping("/{userId}")
    public List<Order> getAllOrders(Principal principal) {
        return orderService.getOrderHistory(userService.findByEmail(principal.getName()));
    }

    private OrderResponseDto getOrderResponseDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setTickets(getListTicketsResponseDto(order));
        orderResponseDto.setOrderId(order.getId());
        orderResponseDto.setUserId(order.getUser().getId());
        return orderResponseDto;
    }

    private List<TicketResponseDto> getListTicketsResponseDto(Order order) {
        List<Ticket> tickets = order.getTickets();
        return tickets.stream()
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
