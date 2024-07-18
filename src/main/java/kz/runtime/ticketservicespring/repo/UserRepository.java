package kz.runtime.ticketservicespring.repo;


import jakarta.transaction.Transactional;
import kz.runtime.ticketservicespring.entities.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Transactional
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findUserById(Long id);
    void deleteUserById(Long id);
    @Modifying
    @Query(value = "UPDATE users SET creation_date = :creationDate, username = :username where id = :id", nativeQuery = true)
    void updateUser(@Param("creationDate")LocalDate creationDate, @Param("username")String username, @Param("id")Long id);
    @Query(value = "INSERT INTO users (creation_date, username) VALUES(:creationDate, :username)", nativeQuery = true)
    void insertUser(@Param("creationDate")LocalDate creationDate, @Param("username")String username);
}
