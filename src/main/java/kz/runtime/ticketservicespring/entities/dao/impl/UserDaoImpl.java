package kz.runtime.ticketservicespring.entities.dao.impl;

import kz.runtime.ticketservicespring.entities.User;

public interface UserDaoImpl {
    User findById(long id);
    void save(User user);
    void update(User user);
    void delete(long id);
}
