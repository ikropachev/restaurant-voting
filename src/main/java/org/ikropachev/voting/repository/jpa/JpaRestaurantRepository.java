package org.ikropachev.voting.repository.jpa;

import org.ikropachev.voting.model.Restaurant;
import org.ikropachev.voting.repository.RestaurantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaRestaurantRepository implements RestaurantRepository {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Restaurant save(Restaurant restaurant) {
        log.info("save restaurant {}", restaurant);
        if (restaurant.isNew()) {
            em.persist(restaurant);
            return restaurant;
        } else {
            return em.merge(restaurant);
        }
    }

    @Override
    public Restaurant get(int id) {
        log.info("get restaurant with id {}", id);
        return em.find(Restaurant.class, id);
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        log.info("delete restaurant with id {}", id);
        return em.createQuery("DELETE FROM Restaurant r WHERE r.id=:id")
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public List<Restaurant> getAll() {
        log.info("get all restaurants");
        return em.createQuery("SELECT r FROM Restaurant r ORDER BY r.name").getResultList();
    }
}
