package org.ivan_kropachev.restaurant_voting.repository.jpa;

import org.ivan_kropachev.restaurant_voting.model.Dish;
import org.ivan_kropachev.restaurant_voting.model.Menu;
import org.ivan_kropachev.restaurant_voting.repository.DishRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaDishRepository implements DishRepository {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Dish save(Dish dish, int menuId) {
        if (!dish.isNew() && get(dish.getId(), menuId) == null) {return null;
        }
        dish.setMenu(em.getReference(Menu.class, menuId));
        log.info("create dish {} for menu {}", dish, menuId);
        if (dish.isNew()) {
            em.persist(dish);
            return dish;
        } else {
            return em.merge(dish);
        }
    }

    @Override
    public Dish get(int id) {
        return em.find(Dish.class, id);
    }

    public Dish get(int id, int menuId) {
        Dish dish = em.find(Dish.class, id);
        return dish != null && dish.getMenu().getId() == menuId ? dish : null;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createQuery("DELETE FROM Dish d WHERE d.id=:id")
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public List<Dish> getAll() {
        return em.createQuery("SELECT d FROM Dish d ORDER BY d.menu.id DESC").getResultList();
    }
}
