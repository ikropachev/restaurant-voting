package org.ivan_kropachev.restaurant_voting.repository.jdbc;

import org.ivan_kropachev.restaurant_voting.model.Vote;
import org.ivan_kropachev.restaurant_voting.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;


public class JdbcVoteRepository implements VoteRepository {
    private static final BeanPropertyRowMapper<Vote> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Vote.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertVote;

    @Autowired
    public JdbcVoteRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertVote = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("vote")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Vote save(Vote vote) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", vote.getId())
                .addValue("user_id", vote.getUserId())
                .addValue("restaurant_id", vote.getRestaurantId())
                .addValue("date_time", vote.getDateTime());

        if (vote.isNew()) {
            Number newKey = insertVote.executeAndReturnKey(map);
            vote.setId(newKey.intValue());
        } else if (namedParameterJdbcTemplate.update(
                "UPDATE vote SET user_id=:user_id, restaurant_id=:restaurant_id, date_time=:date_time", map) == 0) {
            return null;
        }
        return vote;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM vote WHERE id=?", id) != 0;
    }

    @Override
    public Vote get(int id) {
        List<Vote> votes = jdbcTemplate.query("SELECT * FROM vote WHERE id=?", ROW_MAPPER, id);
        return DataAccessUtils.singleResult(votes);
    }

    @Override
    public List<Vote> getAll() {
        return jdbcTemplate.query("SELECT * FROM vote ORDER BY date_time DESC", ROW_MAPPER);
    }
}
