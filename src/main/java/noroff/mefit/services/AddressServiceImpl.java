package noroff.mefit.services;

import noroff.mefit.models.Address;
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
    public Address findById(Integer id) {
        return addressRepository.findById(id).get();
    }

    @Override
    public Collection<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address add(Address entity) {
        return addressRepository.save(entity);
    }

    @Override
    public Address update(Address entity) {
        return addressRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        Address address = findById(id);
        if(address.getProfile()!= null){
            address.getProfile().setAddress(null);
        }
        addressRepository.delete(address);
    }
}
