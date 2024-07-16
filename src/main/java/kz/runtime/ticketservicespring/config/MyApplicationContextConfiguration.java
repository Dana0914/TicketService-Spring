package kz.runtime.ticketservicespring.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;




@Configuration
@ComponentScan
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
    public boolean isEnabled() {
        return true;
    }
}
