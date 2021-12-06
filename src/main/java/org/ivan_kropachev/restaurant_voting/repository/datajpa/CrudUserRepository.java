package org.ivan_kropachev.restaurant_voting.repository.datajpa;

import org.ivan_kropachev.restaurant_voting.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudUserRepository extends JpaRepository<User, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM User u WHERE u.id=:id")
    int delete (@Param("id") int id);

    @Override
    @Transactional
    User save (User user);

    @Override
    Optional<User> findById(Integer id);

    @Override
    List<User> findAll(Sort sort);

    User findByEmail(String email);
}
