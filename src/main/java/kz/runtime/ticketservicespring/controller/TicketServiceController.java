package kz.runtime.ticketservicespring.controller;


import kz.runtime.ticketservicespring.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/ticket_service_controller")
public class TicketServiceController {
    private final TicketService ticketService;

    @GetMapping(value = "/get_ticket")
    public Object getTicket(@RequestParam(name = "id") Long id) {
        return ticketService.findTicketById(id);
    }

}
