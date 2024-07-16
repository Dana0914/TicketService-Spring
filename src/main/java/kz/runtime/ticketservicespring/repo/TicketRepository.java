package kz.runtime.ticketservicespring.repo;

import kz.runtime.ticketservicespring.entities.Ticket;
import kz.runtime.ticketservicespring.entities.TicketType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {
    Ticket findTicketById(Long id);
    Ticket findTicketByUserId(Long id);
    void deleteTicketById(Long id);
    void deleteUserById(Long id);
    @Modifying
    @Query("UPDATE Ticket t SET t.ticketType = :ticket_type, t.creationDate = :creation_data," +
            "t.userId = :user_id where t.id = :id")
    Ticket updateTicket(Ticket ticket, @Param("user_id")String userId,
                        @Param("id")Long id, @Param("ticket_type")TicketType ticketType,
                        @Param("creation_data")LocalDate creationDate);
}
