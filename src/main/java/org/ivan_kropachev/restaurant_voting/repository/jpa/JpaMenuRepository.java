package org.ivan_kropachev.restaurant_voting.repository.jpa;

import org.ivan_kropachev.restaurant_voting.model.Menu;
import org.ivan_kropachev.restaurant_voting.model.Restaurant;
import org.ivan_kropachev.restaurant_voting.repository.MenuRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public Menu save(Menu menu, int restaurantId) {
        menu.setRestaurant(em.getReference(Restaurant.class, restaurantId));
        log.info("create menu {} for restaurant {}", menu, restaurantId);
        if (menu.isNew()) {
            em.persist(menu);
            return menu;
        } else {
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
        return em.createQuery("SELECT m FROM Menu m", Menu.class).getResultList();
    }

    @Override
    public List<Menu> getAllByDate(LocalDate date) {
        return em.createQuery("SELECT m FROM Menu m WHERE m.date=:date", Menu.class)
                .setParameter("date", date)
                .getResultList();
    }

    @Override
    public List<Menu> getAllByDateAndRestaurantId(LocalDate date, int restaurantId) {
        return em.createQuery("SELECT m FROM Menu m WHERE m.date=:date AND m.restaurant.id=:restaurantId", Menu.class)
                .setParameter("date", date)
                .setParameter("restaurantId", restaurantId)
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

    @Override
    @Transactional
    public void deleteAll() {
        em.createQuery("DELETE FROM Menu m").executeUpdate();
    }
}
