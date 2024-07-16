package kz.runtime.ticketservicespring.repo;


import kz.runtime.ticketservicespring.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findUserById(Long id);
    void deleteUserById(Long id);
}
