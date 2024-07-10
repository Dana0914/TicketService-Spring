package kz.runtime.ticketservicespring.config;

import kz.runtime.ticketservicespring.entities.dao.TicketDAO;
import kz.runtime.ticketservicespring.entities.dao.UserDAO;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.util.List;

@Configuration
public class MyApplicationContextConfiguration {

    @Bean
    public SessionFactory sessionFactory() {
        return getHibernateConfiguration().buildSessionFactory();
    }

    @Bean
    public org.hibernate.cfg.Configuration getHibernateConfiguration() {
        return new org.hibernate.cfg.Configuration();
    }

    @Bean
    public TicketDAO ticketDAO() {
        return new TicketDAO(sessionFactory());
    }
    @Bean
    public UserDAO userDAO() {
       return new UserDAO(sessionFactory());
    }
    @Bean
    public boolean isEnabled() {
        return true;
    }
    @Bean()
    public File file() {
        return new File("src/main/resources/file.txt");
    }

}
