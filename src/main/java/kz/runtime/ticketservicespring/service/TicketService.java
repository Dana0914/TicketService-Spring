package kz.runtime.ticketservicespring.service;


import kz.runtime.ticketservicespring.entities.Ticket;
import kz.runtime.ticketservicespring.repo.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }
    public Optional<Ticket> findTicketById(Long id) {
        return ticketRepository.findById(id);
    }
    public Optional<Ticket> findTicketByUserId(Long id) {
        return ticketRepository.findByUserId(id);
    }
    public void deleteTicketById(Long id) {
        Optional<Ticket> ticketId = ticketRepository.findById(id);
        if (ticketId.isPresent()) {
            ticketRepository.delete(ticketId.get());
        } else {
            throw new NoSuchElementException("Ticket not found");
        }

    }
    public Ticket saveTicket(Ticket ticket) {
        ticketRepository.save(ticket.getTicketType().name(), ticket.getCreationDate(), ticket.getUserId());
        return ticket;

    }
    public List<Ticket> fetchAllTickets() {
        return (List<Ticket>) ticketRepository.findAll();
    }
    public Optional<Ticket> updateTicket(Long id, Ticket ticket) {
        Optional<Ticket> ticketId = ticketRepository.findById(id);
        if (ticket.getUserId() == null || ticket.getCreationDate() == null || ticket.getTicketType() == null) {
            throw new NoSuchElementException("Ticket not found");
        }
        if (ticketId.isEmpty()) {
            throw new NoSuchElementException("No ticket found with id: " + id);
        }
        ticketId.get().setTicketType(ticket.getTicketType());
        ticketId.get().setCreationDate(ticket.getCreationDate());
        ticketId.get().setUserId(ticket.getUserId());
        ticketRepository.updateTicket(ticketId.get().getTicketType().name(),
                ticketId.get().getCreationDate(), ticketId.get().getUserId(), ticketId.get().getId());
        return ticketId;
    }
}
