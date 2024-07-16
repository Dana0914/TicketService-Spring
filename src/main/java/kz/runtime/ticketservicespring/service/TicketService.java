package kz.runtime.ticketservicespring.service;


import kz.runtime.ticketservicespring.entities.Ticket;
import kz.runtime.ticketservicespring.repo.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }
    public Ticket findTicketById(Long id) {
        return ticketRepository.findTicketById(id);
    }
    Ticket findTicketByUserId(Long id) {
        return ticketRepository.findTicketByUserId(id);
    }
    public void deleteTicketById(Long id) {
        ticketRepository.deleteTicketById(id);
    }
    public void deleteUserById(Long id) {
        ticketRepository.deleteUserById(id);
    }
    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }
    public List<Ticket> fetchAllTickets() {
        return (List<Ticket>) ticketRepository.findAll();
    }
    public Ticket updateTicketByType(Ticket ticket) {
        return ticketRepository.save(ticket);
    }



}
