package kz.runtime.ticketservicespring.service;


import kz.runtime.ticketservicespring.entities.Ticket;
import kz.runtime.ticketservicespring.repo.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;


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
    public void saveTicket(Ticket ticket) {
        ticketRepository.insertUser(ticket.getTicketType().name(), ticket.getCreationDate(), ticket.getUserId());

    }
    public List<Ticket> fetchAllTickets() {
        return (List<Ticket>) ticketRepository.findAll();
    }
    public Ticket updateTicket(Long id, Ticket ticket) {
        Ticket ticketId = ticketRepository.findTicketById(id);
        ticketId.setTicketType(ticket.getTicketType());
        ticketId.setCreationDate(ticket.getCreationDate());
        ticketId.setUserId(ticket.getUserId());
        ticketRepository.updateTicket(ticketId.getTicketType().name(), ticketId.getCreationDate(), ticketId.getUserId(), ticketId.getId());
        return ticketId;
    }



}
