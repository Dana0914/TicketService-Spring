package kz.runtime.ticketservicespring.main;


import kz.runtime.ticketservicespring.entities.Ticket;
import kz.runtime.ticketservicespring.entities.TicketType;
import kz.runtime.ticketservicespring.entities.User;
import kz.runtime.ticketservicespring.entities.dao.TicketDAO;
import kz.runtime.ticketservicespring.entities.dao.UserDAO;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import java.time.LocalDate;


public class Main {
    public static void main(String[] args) {
        Configuration config = new Configuration()
                .addAnnotatedClass(Ticket.class)
                .addAnnotatedClass(User.class);
        try (SessionFactory sessionFactory = config.buildSessionFactory()) {
            Ticket ticket = new Ticket();
            TicketDAO ticketDao = new TicketDAO(sessionFactory);
            ticket.setTicketType(TicketType.DAY);
            ticket.setCreationDate(LocalDate.of(2024, 7, 5));
            ticket.setId(9L);
            //ticketDao.update(ticket);
            ticketDao.findById(5L);
            User user = new User();
            UserDAO userDao = new UserDAO(sessionFactory);

        }

    }
}
