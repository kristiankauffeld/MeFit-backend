package noroff.mefit.services;

import noroff.mefit.models.Workout;
import noroff.mefit.repositories.WorkoutRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
public class WorkoutServiceImpl implements WorkoutService{
    private final WorkoutRepository workoutRepository;

    public WorkoutServiceImpl(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    @Override
    public Workout findById(Integer id) {
        return workoutRepository.findById(id).get();
    }

    @Override
    public Collection<Workout> findAll() {
        return workoutRepository.findAll();
    }

    @Override
    public Workout add(Workout entity) {
        return workoutRepository.save(entity);
    }

    @Override
    public Workout update(Workout entity) {
        return workoutRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        Workout workout = findById(id);
        //make sure to delete relations

        workoutRepository.delete(workout);

    }
}
