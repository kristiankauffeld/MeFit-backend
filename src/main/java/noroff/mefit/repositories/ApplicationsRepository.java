package noroff.mefit.repositories;

import noroff.mefit.models.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationsRepository extends JpaRepository<Application, Integer> {
}
