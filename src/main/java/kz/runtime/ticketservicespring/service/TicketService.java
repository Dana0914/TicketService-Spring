package kz.runtime.ticketservicespring.service;


import kz.runtime.ticketservicespring.entities.Ticket;
import kz.runtime.ticketservicespring.entities.TicketType;
import kz.runtime.ticketservicespring.repo.TicketRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        int save = ticketRepository.save(ticket.getTicketType().name(), ticket.getCreationDate(), ticket.getUserId());
        return save > 0 ? ticket : null;


    }
    public List<Ticket> fetchAllTickets() {
        return (List<Ticket>) ticketRepository.findAll();
    }
    public Ticket updateTicket(Long id, Ticket ticket) {
        Ticket ticketId = ticketRepository.findById(id).orElseThrow();
        ticketId.setTicketType(ticket.getTicketType());
        ticketId.setCreationDate(ticket.getCreationDate());
        ticketId.setUserId(ticket.getUserId());
        ticketRepository.updateTicket(ticketId.getTicketType().name(), ticketId.getCreationDate(), ticketId.getUserId(), ticketId.getId());
        return ticketId;
    }
}
