package kz.runtime.ticketservicespring.main;


import kz.runtime.ticketservicespring.config.MyApplicationContextConfiguration;
import kz.runtime.ticketservicespring.customexception.UserNotFoundException;
import kz.runtime.ticketservicespring.entities.Ticket;
import kz.runtime.ticketservicespring.entities.User;
import kz.runtime.ticketservicespring.entities.dao.TicketDAO;
import kz.runtime.ticketservicespring.entities.dao.UserDAO;
import kz.runtime.ticketservicespring.serviice.UserService;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;

import java.io.*;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) throws UserNotFoundException, IOException {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyApplicationContextConfiguration.class);
        Configuration config = context.getBean(Configuration.class);
        config.addAnnotatedClass(Ticket.class).addAnnotatedClass(User.class);
        SessionFactory sessionFactory = context.getBean(SessionFactory.class);


        Resource resource = context.getResource("file.txt");
        List<String> resourceList = new ArrayList<>();
        resourceList.add(getResource(resource));
        System.out.println(resourceList);

        Ticket ticket = new Ticket();
        TicketDAO ticketDao = new TicketDAO(sessionFactory);
        User user = new User();
        UserDAO userDao = new UserDAO(sessionFactory);
        ticket.setTicketType("MONTH");
        ticket.setCreationDate(LocalDate.of(2024, 8, 4));
        ticket.setUserId(3L);

        user.setId(1L);
        user.setUsername("Olly");
        user.setCreationDate(LocalDate.now());


        UserService userService = new UserService(ticketDao, userDao, true);
        userService.updateUserAndCreateTicket(ticket, user);
    }
    public static String getResource(Resource resource) throws IOException {
        return new String(Files.readAllBytes(resource.getFile().toPath()));
    }

}
