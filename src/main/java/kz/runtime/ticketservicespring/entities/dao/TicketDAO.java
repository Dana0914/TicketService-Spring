package kz.runtime.ticketservicespring.entities.dao;

import kz.runtime.ticketservicespring.entities.Ticket;
import kz.runtime.ticketservicespring.entities.dao.impl.TicketDaoImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class TicketDAO implements TicketDaoImpl {
    SessionFactory sessionFactory;
    public TicketDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    

    @Override
    public Ticket findById(long id) {
        Ticket ticket = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            ticket = session.get(Ticket.class, id);
            System.out.println(ticket);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ticket;
    }

    @Override
    public Ticket findByUserId(long userId) {
        Ticket ticket = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            ticket = session.get(Ticket.class, userId);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ticket;
    }

    @Override
    public void save(Ticket ticket) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createNativeQuery("INSERT INTO Ticket (ticket_type, creation_date) VALUES(CAST(?1 AS ticket_type), ?2)", Ticket.class)
                    .setParameter(1, ticket.getTicketType())
                    .setParameter(2, ticket.getCreationDate())
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Ticket ticket) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createNativeQuery("UPDATE ticket SET ticket_type = CAST(?1 AS ticket_type) WHERE id = ?2")
                    .setParameter(1, ticket.getTicketType())
                    .setParameter(2, ticket.getId()).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createNativeQuery("DELETE FROM ticket where id = ?1")
                    .setParameter(1, id).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
