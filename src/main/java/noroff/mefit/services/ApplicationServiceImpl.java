package noroff.mefit.services;

import noroff.mefit.models.Application;
import noroff.mefit.repositories.ApplicationsRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
public class ApplicationServiceImpl implements ApplicationService{
    private final ApplicationsRepository applicationsRepository;

    public ApplicationServiceImpl(ApplicationsRepository applicationsRepository) {
        this.applicationsRepository = applicationsRepository;
    }

    @Override
    public Application findById(Integer id) {
        return applicationsRepository.findById(id).get();
    }

    @Override
    public Collection<Application> findAll() {
        return applicationsRepository.findAll();
    }

    @Override
    public Application add(Application entity) {
        return applicationsRepository.save(entity);
    }

    @Override
    public Application update(Application entity) {
        return applicationsRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        Application application = findById(id);
        if(application.getProfile()!= null){
            application.getProfile().setApplication(null);
        }
        applicationsRepository.deleteById(id);
    }
}
