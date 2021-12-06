package org.ivan_kropachev.restaurant_voting.repository.jpa;

import org.ivan_kropachev.restaurant_voting.model.Dish;
import org.ivan_kropachev.restaurant_voting.model.Vote;
import org.ivan_kropachev.restaurant_voting.repository.VoteRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaVoteRepository implements VoteRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Vote save(Vote vote) {
        if (vote.isNew()) {
            em.persist(vote);
            return vote;
        } else {
            return em.merge(vote);
        }
    }

    @Override
    public Vote get(int id) {
        return em.find(Vote.class, id);
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createQuery("DELETE FROM Vote v WHERE v.id=:id")
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public List<Vote> getAll() {
        return em.createQuery("SELECT v FROM Vote v ORDER BY v.dateTime DESC").getResultList();
    }
}
