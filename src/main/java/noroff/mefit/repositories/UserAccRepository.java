package noroff.mefit.repositories;

import noroff.mefit.models.UserAcc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface UserAccRepository extends JpaRepository<UserAcc, Integer> {
    @Query("select s from UserAcc s where s.first_name LIKE %?1% OR s.last_name like %?1%")
    Set<UserAcc> findAllByName(String name);
}
