package noroff.mefit.services;


import noroff.mefit.models.Profile;
import noroff.mefit.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;

    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Profile findById(String id) {
        return profileRepository.findById(id).get();
    }

    @Override
    public Collection<Profile> findAll() {
        return profileRepository.findAll();
    }

    @Override
    public Profile add(Profile entity) {
        if(profileRepository.findById(entity.getId()).isPresent()){
            System.out.println("hej");
            return entity;
        }
        return profileRepository.save(entity);
    }

    @Override
    public Profile update(Profile entity) {
        return profileRepository.save(entity);
    }


    @Override
    public void deleteById(String id) {
        Profile profile = findById(id);
        if(profile != null){
            if(profile.getAddress()!=null){
                profile.getAddress().setProfile(null);
            }

            profileRepository.delete(profile);
        }
    }
}
