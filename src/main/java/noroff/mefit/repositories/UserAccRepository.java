package noroff.mefit.repositories;

import noroff.mefit.models.UserAcc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccRepository extends JpaRepository<UserAcc, String> {
}
