package kz.runtime.ticketservicespring.entities.dao.impl;

import kz.runtime.ticketservicespring.entities.Ticket;

public interface TicketDaoImpl {
    Ticket findById(long id);
    Ticket findByUserId(long userId);
    void save(Ticket ticket);
    void update(Ticket ticket);
    void delete(long id);
}
