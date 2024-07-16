package kz.runtime.ticketservicespring.main;

import kz.runtime.ticketservicespring.config.MyCustomConfiguration;
import kz.runtime.ticketservicespring.entities.Ticket;
import kz.runtime.ticketservicespring.entities.TicketType;
import kz.runtime.ticketservicespring.service.TicketService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyCustomConfiguration.class);
        MyCustomConfiguration customConfiguration = context.getBean(MyCustomConfiguration.class);
        System.out.println(customConfiguration.thisIsMyFirstConditionalBean());
        TicketService ticketService = context.getBean(TicketService.class);
        Ticket ticketById = ticketService.findTicketById(1L);
        Ticket ticket1 = new Ticket();
        ticket1.setTicketType(TicketType.WEEK);
        ticket1.setUserId(1L);
        ticket1.setCreationDate(LocalDate.now());
        System.out.println(ticketService.saveTicket(ticket1));

    }
}
