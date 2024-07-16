package kz.runtime.ticketservicespring.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@Entity
@Data
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id", insertable=false, updatable=false)
    private Long userId;
    @Enumerated(EnumType.STRING)
    @Column(name = "ticket_type")
    private TicketType ticketType;
    @Column(name = "creation_date")
    private LocalDate creationDate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id) && Objects.equals(ticketType, ticket.ticketType) && Objects.equals(creationDate, ticket.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ticketType, creationDate);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", ticketType=" + ticketType +
                ", creationDate=" + creationDate +
                '}';
    }

}
