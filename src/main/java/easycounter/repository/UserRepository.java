package easycounter.repository;

import easycounter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Modifying
    @Query("delete from User as user where user.id = :id")
    int delete(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("""
        update User u
        set u.name = :#{#user.name},
        u.login = :#{#user.login},
        u.password = :#{user.password}
        where u.id = :#{#user.id}
        """)
    int update(@Param("user") User user);

    Optional<User> findByName(String name);

    Optional<User> findByLogin(String login);

    Boolean existsByName(String name);

    Boolean existsByLogin(String login);
}
