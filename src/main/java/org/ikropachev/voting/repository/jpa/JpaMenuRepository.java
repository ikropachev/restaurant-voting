package org.ikropachev.voting.repository.jpa;

import org.hibernate.jpa.QueryHints;
import org.ikropachev.voting.model.Menu;
import org.ikropachev.voting.model.Restaurant;
import org.ikropachev.voting.repository.MenuRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaMenuRepository implements MenuRepository {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Menu create(Menu menu, int restaurantId) {
        log.info("save menu {} for restaurant {}", menu, restaurantId);
        menu.setRestaurant(em.getReference(Restaurant.class, restaurantId));
        Menu previous = getByRestaurantIdAndDate(restaurantId, menu.getDate());
        if (previous == null) {
            em.persist(menu);
            return menu;
        } else {
            throw new IllegalArgumentException(menu + " must be new");
        }
    }

    @Override
    @Transactional
    public Menu update(Menu menu, int restaurantId) {
        log.info("update menu {} for restaurant {}", menu, restaurantId);
        menu.setRestaurant(em.getReference(Restaurant.class, restaurantId));
        Menu previous = getByRestaurantIdAndDate(restaurantId, menu.getDate());
        if (previous == null) {
            throw new IllegalArgumentException(menu + " must not be new");
        } else {
            menu.setId(previous.getId());
            return em.merge(menu);
        }
    }

    @Override
    public Menu get(int id, int restaurantId) {
        Menu menu = em.find(Menu.class, id);
        return menu != null && menu.getRestaurant().getId() == restaurantId ? menu : null;
    }

    @Override
    public List<Menu> getAll() {
        return em.createQuery("SELECT m FROM Menu m ORDER BY m.date DESC", Menu.class).getResultList();
    }

    @Override
    public List<Menu> getAllByDate(LocalDate date) {
        return em.createQuery("SELECT m FROM Menu m WHERE m.date=:date ORDER BY m.restaurant.name", Menu.class)
                .setParameter("date", date)
                .getResultList();
    }

    @Override
    @Transactional
    public boolean delete(int id, int restaurantId) {
        return em.createQuery("DELETE FROM Menu m WHERE m.id=:id AND m.restaurant.id=:restaurantId")
                .setParameter("id", id)
                .setParameter("restaurantId", restaurantId)
                .executeUpdate() != 0;
    }

    @Transactional
    public Menu getByRestaurantIdAndDate(int restaurantId, LocalDate date) {
        List<Menu> menus = em.createQuery("SELECT m FROM Menu m WHERE m.restaurant.id=:restaurantId AND m.date=:date")
                .setParameter("restaurantId", restaurantId)
                .setParameter("date", date)
                .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
                .getResultList();
        return DataAccessUtils.singleResult(menus);
    }
}
