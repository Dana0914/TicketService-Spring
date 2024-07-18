package kz.runtime.ticketservicespring.main;

import kz.runtime.ticketservicespring.config.MyCustomConfiguration;
import kz.runtime.ticketservicespring.entities.Ticket;
import kz.runtime.ticketservicespring.entities.TicketType;
import kz.runtime.ticketservicespring.entities.User;
import kz.runtime.ticketservicespring.service.TicketService;
import kz.runtime.ticketservicespring.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyCustomConfiguration.class);
        MyCustomConfiguration customConfiguration = context.getBean(MyCustomConfiguration.class);
        System.out.println(customConfiguration.thisIsMyFirstConditionalBean());
        TicketService ticketService = context.getBean(TicketService.class);
        UserService userService = context.getBean(UserService.class);
        User userId = userService.findUserById(5L);
        userId.setUsername("Drake");
        userId.setCreationDate(LocalDate.of(2024,5,8));
        userService.updateUser(userId.getId(), userId);
        Ticket ticketById = new Ticket();
        ticketById.setCreationDate(LocalDate.now());
        ticketById.setTicketType(TicketType.DAY);
        ticketById.setUserId(4L);
        ticketService.updateTicket(10L, ticketById);
    }
}
