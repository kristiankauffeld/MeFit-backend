package noroff.mefit.repositories;

import noroff.mefit.models.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Integer> {
    Collection<Goal> findByUserId(String userId);
}
