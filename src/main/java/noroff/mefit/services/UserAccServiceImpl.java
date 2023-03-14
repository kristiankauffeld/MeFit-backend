package noroff.mefit.services;

import noroff.mefit.models.UserAcc;
import noroff.mefit.repositories.UserAccRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;

@Service
public class UserAccServiceImpl implements UserAccService {

    private final UserAccRepository userAccRepository;

    public UserAccServiceImpl(UserAccRepository userAccRepository) {
            this.userAccRepository = userAccRepository;
    }


    @Override
    public UserAcc findById(Integer id) { return userAccRepository.findById(id).get(); }

    @Override
    public Collection<UserAcc> findAll() {
        return userAccRepository.findAll();
    }
    @Override
    public UserAcc add(UserAcc entity) {
        return null;
    }

    @Override
    public UserAcc update(UserAcc entity) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {
        UserAcc userAcc = findById(id);
        //for this work, make sure to delete all data in relations
        userAccRepository.delete(userAcc);


    }
}
