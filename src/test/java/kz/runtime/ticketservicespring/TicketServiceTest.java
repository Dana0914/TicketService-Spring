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

    @Test
    public void testFindTicketById_ifIdFoundWithExistentTicket_Found() {
        Ticket ticketToFind = new Ticket();
        ticketToFind.setId(8L);
        ticketToFind.setTicketType(TicketType.MONTH);
        ticketToFind.setCreationDate(LocalDate.of(2024, 7, 18));
        when(ticketRepository.findById(ticketToFind.getId())).thenReturn(Optional.of(ticketToFind));
        Optional<Ticket> foundTicket = ticketService.findTicketById(ticketToFind.getId());
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
    public void testDeleteTicketById_ifDeletedIdWithNonExistentTicket_ShouldThrowException() {
        Ticket ticketToDelete = new Ticket();
        ticketToDelete.setId(1L);
        when(ticketRepository.findById(ticketToDelete.getId())).thenReturn(Optional.empty()).thenThrow();
        Assertions.assertThrows(NoSuchElementException.class, () -> ticketService.deleteTicketById(ticketToDelete.getId()));
    }
    @Test
    public void testDeleteTicketById_IfIdIsNull_ShouldThrowException() {
        Ticket ticketToDelete = new Ticket();
        ticketToDelete.setId(null);
        when(ticketRepository.findById(ticketToDelete.getId())).thenReturn(Optional.empty()).thenThrow();
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
    @Test
    public void testSaveTicket_ifTicketUserIdIsNull_ShouldThrowException() {
        Ticket ticketToSave = new Ticket();
        ticketToSave.setId(10L);
        ticketToSave.setTicketType(TicketType.MONTH);
        ticketToSave.setCreationDate(LocalDate.now());
        ticketToSave.setUserId(null);
        when(ticketRepository.save(ticketToSave.getTicketType().name(), ticketToSave.getCreationDate(),
                ticketToSave.getUserId()))
                .thenThrow(new NullPointerException());

        Assertions.assertThrows(NullPointerException.class, () -> ticketService.saveTicket(ticketToSave));
    }
    @Test
    public void testSaveTicket_ifTicketDateIsNull_ShouldThrowException() {
        Ticket ticketToSave = new Ticket();
        ticketToSave.setId(15L);
        ticketToSave.setTicketType(TicketType.MONTH);
        ticketToSave.setUserId(5L);
        ticketToSave.setCreationDate(null);
        when(ticketRepository.save(ticketToSave.getTicketType().name(), ticketToSave.getCreationDate(),
                ticketToSave.getUserId()))
                .thenThrow(new NullPointerException());

        Assertions.assertThrows(NullPointerException.class, () -> ticketService.saveTicket(ticketToSave));
    }
    @Test
    public void testUpdateTicket_updateTicketType_ShouldUpdateTicket() {
        Ticket ticketToUpdate = new Ticket();
        ticketToUpdate.setId(1L);
        ticketToUpdate.setTicketType(TicketType.MONTH);
        ticketToUpdate.setUserId(5L);
        ticketToUpdate.setCreationDate(LocalDate.of(2023, 1, 1));

        Ticket updatedTicket = new Ticket();
        updatedTicket.setId(1L);
        updatedTicket.setTicketType(TicketType.YEAR);
        updatedTicket.setUserId(5L);
        updatedTicket.setCreationDate(LocalDate.of(2024,5,7));

        when(ticketRepository.findById(ticketToUpdate.getId())).thenReturn(Optional.of(ticketToUpdate));

        when(ticketRepository.updateTicket(updatedTicket.getTicketType().name(),
                updatedTicket.getCreationDate(), updatedTicket.getUserId(),
                updatedTicket.getId())).thenReturn(1);

        Optional<Ticket> updated = ticketService.updateTicket(ticketToUpdate.getId(), updatedTicket);

        Assertions.assertEquals(updatedTicket.getTicketType(), updated.get().getTicketType());
    }
    @Test
    public void testUpdateTicket_ifTicketUserIdAndDateAreNull_ShouldThrowException() {
        Ticket ticketToUpdate = new Ticket();
        ticketToUpdate.setId(1L);
        ticketToUpdate.setTicketType(TicketType.MONTH);
        ticketToUpdate.setCreationDate(LocalDate.now());
        ticketToUpdate.setUserId(5L);

        Ticket updatedTicket = new Ticket();
        updatedTicket.setId(1L);
        updatedTicket.setTicketType(TicketType.YEAR);
        updatedTicket.setUserId(null);
        updatedTicket.setCreationDate(null);

        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticketToUpdate));
        Assertions.assertThrows(NoSuchElementException.class, () -> ticketService.updateTicket(1L, updatedTicket));
    }

}
