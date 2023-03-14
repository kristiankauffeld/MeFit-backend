package noroff.mefit.services;

import noroff.mefit.models.UserAcc;
import noroff.mefit.repositories.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AddressServiceImpl implements AddressService{
    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public UserAcc findById(Integer integer) {
        return null;
    }

    @Override
    public Collection<UserAcc> findAll() {
        return null;
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
    public void deleteById(Integer integer) {

    }
}
