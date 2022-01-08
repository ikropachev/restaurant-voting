package org.ikropachev.voting.repository.jpa;

import org.hibernate.jpa.QueryHints;
import org.ikropachev.voting.model.Vote;
import org.ikropachev.voting.repository.VoteRepository;
import org.ikropachev.voting.util.exception.LateVoteException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.ikropachev.voting.util.CheckTimeUtil.checkTime;

@Repository
@Transactional(readOnly = true)
public class JpaVoteRepository implements VoteRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Vote save(int userId, int restaurantId) {
        Vote vote = new Vote(null, userId, restaurantId, LocalDate.now());
        em.persist(vote);
        return vote;
    }

    @Override
    @Transactional
    public Vote update(Vote previous, int restaurantId) {
        previous.setRestaurantId(restaurantId);
        em.merge(previous);
        return previous;
    }

    @Override
    public Vote get(int id) {
        return em.find(Vote.class, id);
    }

    @Override
    public List<Vote> getAllForToday() {
        LocalDate currentDate = LocalDate.now();
        return em.createQuery("SELECT v FROM Vote v WHERE v.date=:currentDate")
                .setParameter("currentDate", currentDate)
                .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
                .getResultList();
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
