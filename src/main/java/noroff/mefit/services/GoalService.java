package noroff.mefit.services;

import noroff.mefit.models.Goal;

import java.util.Collection;

public interface GoalService extends CrudService<Goal, Integer>{
    Collection<Goal> findByUserId(String userId);
}
