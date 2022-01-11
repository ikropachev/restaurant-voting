package org.ivan_kropachev.restaurant_voting.repository.datajpa;

import org.ivan_kropachev.restaurant_voting.model.Dish;
import org.ivan_kropachev.restaurant_voting.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {
    @Query("SELECT v FROM Vote v ORDER BY v.date DESC")
    List<Vote> findAll();

    @Query("SELECT v FROM Vote v WHERE v.userId=:userId AND v.date=:date")
    Vote getByUserIdAndDate(@Param("userId") int userId, @Param("date") LocalDate date);
}
