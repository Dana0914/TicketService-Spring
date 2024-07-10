package kz.runtime.ticketservicespring.entities.dao;

import jakarta.transaction.Transactional;
import kz.runtime.ticketservicespring.customexception.UserNotFoundException;
import kz.runtime.ticketservicespring.entities.Ticket;
import kz.runtime.ticketservicespring.entities.User;
import kz.runtime.ticketservicespring.entities.dao.impl.TicketDaoImpl;
import kz.runtime.ticketservicespring.entities.dao.impl.UserDaoImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private final TicketDaoImpl ticketDao;
    private final UserDaoImpl userDao;

    @Value("${feature.updateUserAndCreateTicket.enabled}")
    private boolean isEnabled;

    public UserService(TicketDAO ticketDao, UserDAO userDao, boolean isEnabled) {
        this.ticketDao = ticketDao;
        this.userDao = userDao;
        this.isEnabled = isEnabled;
    }

    @Transactional
    public void updateUserAndCreateTicket(Ticket ticket, User user) throws UserNotFoundException {
        if (!isEnabled) {
            throw new UserNotFoundException("User not found");
        }
        userDao.update(user);
        ticketDao.save(ticket);
    }
}
