package org.ivan_kropachev.restaurant_voting.repository.datajpa;

import org.ivan_kropachev.restaurant_voting.model.Vote;

import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//@Repository
public class DataJpaVoteRepository {
    private final CrudVoteRepository repository;
    public DataJpaVoteRepository(CrudVoteRepository crudVoteRepository) {
        this.repository = crudVoteRepository;
    }

    @PersistenceContext
    private EntityManager em;

    public Vote save(Vote vote) {
        return repository.save(vote);
    }

    public Vote get(Integer id){
        return repository.findById(id).orElse(null);
    }
}
