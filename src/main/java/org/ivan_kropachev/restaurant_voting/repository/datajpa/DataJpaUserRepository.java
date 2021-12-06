package org.ivan_kropachev.restaurant_voting.repository.datajpa;

import org.ivan_kropachev.restaurant_voting.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public class DataJpaUserRepository {
    private final CrudUserRepository repository;
    public DataJpaUserRepository(CrudUserRepository crudUserRepository) {
        this.repository = crudUserRepository;
    }
    public User save(User user) {
        return repository.save(user);
    }

    // false if not found
    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    // null if not found
    public User get(int id) {
        return repository.findById(id).orElse(null);
    }

    // null if not found
    public User getByEmail(String email) {
        return repository.findByEmail(email);
    }

    // null if not found
    public List<User> getAll() {
        return repository.findAll();
    }
}
