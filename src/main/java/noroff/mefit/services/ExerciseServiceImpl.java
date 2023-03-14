package noroff.mefit.services;

import noroff.mefit.models.Exercise;
import noroff.mefit.repositories.ExerciseRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
public class ExerciseServiceImpl implements ExerciseService{
    private final ExerciseRepository exerciseRepository;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public Exercise findById(Integer id) {
        return exerciseRepository.findById(id).get();
    }

    @Override
    public Collection<Exercise> findAll() {
        return exerciseRepository.findAll();
    }

    @Override
    public Exercise add(Exercise entity) {
        return exerciseRepository.save(entity);
    }

    @Override
    public Exercise update(Exercise entity) {
        return exerciseRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        Exercise exercise = findById(id);
        exercise.getSetCounts().forEach(s -> s.setExercise(null));
        exerciseRepository.delete(exercise);

    }
}
