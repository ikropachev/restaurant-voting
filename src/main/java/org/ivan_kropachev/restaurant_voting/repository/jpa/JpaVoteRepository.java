package org.ivan_kropachev.restaurant_voting.repository.jpa;

import org.hibernate.jpa.QueryHints;
import org.ivan_kropachev.restaurant_voting.model.Vote;
import org.ivan_kropachev.restaurant_voting.repository.VoteRepository;
import org.ivan_kropachev.restaurant_voting.util.exception.LateVoteException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaVoteRepository implements VoteRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Vote save(int userId, int restaurantId) throws LateVoteException {
        Vote previous = getByUserIdAndDate(userId, LocalDate.now());
        if (previous == null) {
            Vote vote = new Vote(null, userId, restaurantId, LocalDate.now());
            em.persist(vote);
            return vote;
        } else {
            previous.setRestaurantId(restaurantId);
            em.merge(previous);
            return previous;
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
        return em.createQuery("SELECT v FROM Vote v ORDER BY v.date DESC").getResultList();
    }

    public Vote getByUserIdAndDate(int userId, LocalDate date) {
        List<Vote> votes = em.createQuery("SELECT v FROM Vote v WHERE v.userId=:userId AND v.date=:date")
                .setParameter("userId", userId)
                .setParameter("date", date)
                .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
                .getResultList();
        return DataAccessUtils.singleResult(votes);
    }
}
