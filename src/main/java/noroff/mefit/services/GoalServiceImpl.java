package noroff.mefit.services;

import noroff.mefit.models.Goal;
import noroff.mefit.repositories.GoalRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
public class GoalServiceImpl implements GoalService{
    private final GoalRepository goalRepository;

    public GoalServiceImpl(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    @Override
    public Collection<Goal> findByUserId(String userId) {
        return goalRepository.findByUserId(userId);
    }

    @Override
    public Goal findById(Integer id) {
        return goalRepository.findById(id).get();
    }

    @Override
    public Collection<Goal> findAll() {
        return goalRepository.findAll();
    }

    @Override
    public Goal add(Goal entity) {
        return goalRepository.save(entity);
    }

    @Override
    public Goal update(Goal entity) {
        return goalRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        Goal goal = findById(id);
        if(goal.getProfile()!= null){
            goal.getProfile().setGoal(null);
        }
        goal.getWorkouts().forEach(s->{
            Set tempSet = s.getGoals();
            tempSet.remove(goal);
            s.setGoals(tempSet);
        });
        goalRepository.delete(goal);
    }
}
