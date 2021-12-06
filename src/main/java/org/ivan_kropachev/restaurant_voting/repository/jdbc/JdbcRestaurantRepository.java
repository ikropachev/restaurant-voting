package org.ivan_kropachev.restaurant_voting.repository.jdbc;

import org.ivan_kropachev.restaurant_voting.model.User;
import org.ivan_kropachev.restaurant_voting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.ivan_kropachev.restaurant_voting.model.Restaurant;
import org.ivan_kropachev.restaurant_voting.repository.RestaurantRepository;

import java.util.List;


public class JdbcRestaurantRepository implements RestaurantRepository {
    private static final BeanPropertyRowMapper<Restaurant> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Restaurant.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertRestaurant;

    @Autowired
    public JdbcRestaurantRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertRestaurant = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("restaurant")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", restaurant.getId())
                .addValue("name", restaurant.getName());

        if (restaurant.isNew()) {
            Number newKey = insertRestaurant.executeAndReturnKey(map);
            restaurant.setId(newKey.intValue());
        } else if (namedParameterJdbcTemplate.update(
                "UPDATE restaurant SET name=:name WHERE id=:id", map) == 0) {
            return null;
        }
        return restaurant;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM restaurant WHERE id=?", id) != 0;
    }

    @Override
    public Restaurant get(int id) {
        List<Restaurant> restaurant = jdbcTemplate.query("SELECT * FROM restaurant WHERE id=?", ROW_MAPPER, id);
        return DataAccessUtils.singleResult(restaurant);
    }

    @Override
    public List<Restaurant> getAll() {
        return jdbcTemplate.query("SELECT * FROM restaurant ORDER BY id", ROW_MAPPER);
    }
}
