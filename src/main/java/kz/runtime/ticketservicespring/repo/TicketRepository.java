package kz.runtime.ticketservicespring.repo;

import jakarta.transaction.Transactional;
import kz.runtime.ticketservicespring.entities.Ticket;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Transactional
@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {
    Ticket findTicketById(Long id);
    Ticket findTicketByUserId(Long id);
    void deleteTicketById(Long id);
    void deleteUserById(Long id);
    @Modifying
    @Query(value = "INSERT INTO ticket (ticket_type, creation_date, user_id) VALUES(CAST(:ticket_type AS ticket_type), :creation_date, :user_id)",  nativeQuery = true)
    void insertUser(@Param("ticket_type")String ticketType, @Param("creation_date")LocalDate creationDate, @Param("user_id")Long userId);
    @Modifying
    @Query(value = "UPDATE ticket SET ticket_type = CAST(:ticket_type AS ticket_type), creation_date = :creation_data," +
            "user_id = :user_id where id = :id", nativeQuery = true)
    void updateTicket(@Param("ticket_type")String ticketType,@Param("creation_data")LocalDate creationDate,
                   @Param("user_id")Long userId,
                        @Param("id")Long id);
}
