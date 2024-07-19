package kz.runtime.ticketservicespring;

import kz.runtime.ticketservicespring.entities.Ticket;
import kz.runtime.ticketservicespring.entities.TicketType;
import kz.runtime.ticketservicespring.repo.TicketRepository;
import kz.runtime.ticketservicespring.service.TicketService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {
    @InjectMocks
    private TicketService ticketService;
    @Mock
    private TicketRepository ticketRepository;
    private Ticket ticket;
    @BeforeEach
    public void init() {
        ticket = new Ticket();
        ticket.setId(1L);
        ticket.setTicketType(TicketType.MONTH);
        ticket.setUserId(45L);
        ticket.setCreationDate(LocalDate.now());
    }

    @Test
    public void testFindTicketById_ifIdFoundWithExistentTicket_Found() {
        Ticket ticketToFind = new Ticket();
        ticketToFind.setId(8L);
        ticketToFind.setTicketType(TicketType.MONTH);
        ticketToFind.setCreationDate(LocalDate.of(2024, 7, 18));
        when(ticketRepository.findById(ticketToFind.getId())).thenReturn(Optional.of(ticketToFind));
        Optional<Ticket> foundTicket = ticketService.findTicketById(ticketToFind.getId());
        System.out.println(ticketToFind);
        System.out.println(foundTicket);
        Assertions.assertTrue(foundTicket.isPresent());
        Assertions.assertEquals(ticketToFind, foundTicket.get());
    }
    @Test
    public void testFindTicketById_ifIdFoundWithNonExistentTicket_ShouldReturnFoundTicket() {
        Ticket ticketToFind = new Ticket();
        ticketToFind.setId(10L);
        when(ticketRepository.findById(ticketToFind.getId())).thenReturn(Optional.empty());
        Optional<Ticket> foundTicket = ticketService.findTicketById(ticketToFind.getId());
        Assertions.assertFalse(foundTicket.isPresent());
    }
    @Test
    public void testFindTicketById_ifIdIsNull_ShouldThrowException(){
        Ticket ticketToFind = new Ticket();
        ticketToFind.setId(null);
        when(ticketRepository.findById(ticketToFind.getId())).thenReturn(Optional.empty()).thenThrow();
        Optional<Ticket> foundTicket = ticketService.findTicketById(ticketToFind.getId());
        Assertions.assertThrows(NoSuchElementException.class, foundTicket::get);
    }
    @Test
    public void testFindTicketByUserId_ifIdFoundWithExistentTicket_ShouldReturnFoundTicket() {
        Ticket ticketToFind = new Ticket();
        ticketToFind.setUserId(1L);
        when(ticketRepository.findByUserId(ticketToFind.getUserId())).thenReturn(Optional.of(ticketToFind));
        Optional<Ticket> foundTicket = ticketService.findTicketByUserId(ticketToFind.getUserId());
        Assertions.assertTrue(foundTicket.isPresent());
        Assertions.assertEquals(ticketToFind, foundTicket.get());
    }
      @Test
      public void testFindTicketByUserId_ifIdFoundWithNonExistentTicket_ShouldReturnEmptyOptional() {
          Ticket ticketToFind = new Ticket();
          ticketToFind.setUserId(4L);
          when(ticketRepository.findByUserId(ticketToFind.getUserId())).thenReturn(Optional.empty());
          Optional<Ticket> foundTicket = ticketService.findTicketByUserId(ticketToFind.getUserId());
          System.out.println(ticketRepository.findById(ticketToFind.getId()));
          System.out.println(ticketService.findTicketByUserId(ticketToFind.getUserId()));
          Assertions.assertFalse(foundTicket.isPresent());
      }
    @Test
    public void testFindTicketByUserId_ifUserIdIsNull_ShouldThrowException() {
        Ticket ticketToFind = new Ticket();
        ticketToFind.setUserId(null);
        when(ticketRepository.findByUserId(ticketToFind.getUserId())).thenReturn(Optional.empty()).thenThrow();
        Optional<Ticket> foundTicket = ticketService.findTicketByUserId(ticketToFind.getUserId());
        Assertions.assertThrows(NoSuchElementException.class, foundTicket::get);
    }
    @Test
    public void testDeleteTicketById_ifDeletedIdWithExistentTicket_ShouldReturnTrueIfDeleted() {
        Ticket ticketToDelete = new Ticket();
        ticketToDelete.setId(1L);
        when(ticketRepository.findById(ticketToDelete.getId())).thenReturn(Optional.of(ticketToDelete));
        ticketService.deleteTicketById(ticketToDelete.getId());

        when(ticketRepository.findById(ticketToDelete.getId())).thenReturn(Optional.empty());
        Optional<Ticket> deletedTicket = ticketService.findTicketById(ticketToDelete.getId());
        Assertions.assertTrue(deletedTicket.isEmpty());
    }
    @Test
    public void testDeleteTicketById_ifDeletedIdWithNonExistentTicket_ShouldReturnFalseIfNotDeleted() {
        Ticket ticketToDelete = new Ticket();
        ticketToDelete.setId(1L);
        when(ticketRepository.findById(ticketToDelete.getId())).thenReturn(Optional.empty());
        ticketService.deleteTicketById(ticketToDelete.getId());
        Optional<Ticket> deletedTicket = ticketService.findTicketById(ticketToDelete.getId());
        Assertions.assertFalse(deletedTicket.isPresent());
    }
    @Test
    public void testDeleteTicketById_IfIdIsNull_ShouldThrowException() {
        Ticket ticketToDelete = new Ticket();
        ticketToDelete.setId(null);
        Assertions.assertThrows(NoSuchElementException.class, () -> ticketService.deleteTicketById(ticketToDelete.getId()));
    }
    @Test
    public void testSaveTicket_ifTicketExists_ShouldSaveTicket() {
        Ticket ticketToSave = new Ticket();
        ticketToSave.setTicketType(TicketType.MONTH);
        ticketToSave.setCreationDate(LocalDate.now());
        ticketToSave.setUserId(5L);
        ticketToSave.setId(18L);
        when(ticketRepository.save(ticketToSave.getTicketType().name(),ticketToSave.getCreationDate(),
                ticketToSave.getUserId())).thenReturn(1);
        Ticket savedTicket = ticketService.saveTicket(ticketToSave);
        Assertions.assertEquals(ticketToSave,savedTicket);

    }


}
