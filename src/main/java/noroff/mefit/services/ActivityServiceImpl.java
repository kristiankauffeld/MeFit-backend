package noroff.mefit.services;

import noroff.mefit.models.Activity;
import noroff.mefit.repositories.ActivityRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ActivityServiceImpl implements ActivityService{
    private final ActivityRepository activityRepository;

    public ActivityServiceImpl(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public Activity findById(Integer id) {
        if( activityRepository.findById(id).isPresent()){
            return activityRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public Collection<Activity> findAll() {
        return activityRepository.findAll();
    }

    @Override
    public Activity add(Activity entity) {
        return activityRepository.save(entity);
    }

    @Override
    public Activity update(Activity entity) {
        return activityRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        activityRepository.deleteById(id);
    }
}
