package com.elevate.app.takeaway.service;

import com.elevate.app.takeaway.dto.user.User;
import com.elevate.app.takeaway.dto.user.UserAddress;
import com.elevate.app.takeaway.exceptions.CustomException;
import com.elevate.app.takeaway.model.UserAddressModel;
import com.elevate.app.takeaway.model.UserModel;
import com.elevate.app.takeaway.repository.UserAddressRepository;
import com.elevate.app.takeaway.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserAddressRepository userAddressRepository;

    @Override
    public long createUser(UserModel userModel) {
        try {
            User user = new User();
            user.setName(userModel.getName());
            user.setEmail(userModel.getEmail());
            user.setMobileNumber(userModel.getMobileNumber());
            user.setPassword(userModel.getPassword());
            user.setCreatedAt(new Date());
            return userRepository.save(user).getUserId();
        } catch(Exception e) {
            throw new CustomException("Error storing user record");
        }
    }

    @Override
    public long createUserAddress(UserAddressModel userAddressModel) {
        try {
            UserAddress userAddress = new UserAddress();
            userAddress.setUserId(userAddressModel.getUserId());
            userAddress.setCity(userAddressModel.getCity());
            userAddress.setLandmark(userAddressModel.getLandmark());
            userAddress.setLine(userAddressModel.getLine());
            userAddress.setPincode(userAddressModel.getPincode());
            userAddress.setState(userAddressModel.getState());
            userAddress.setCreatedAt(new Date());
            return userAddressRepository.save(userAddress).getAddressId();
        } catch(Exception e) {
            throw new CustomException("Error storing user record");
        }
    }

    @Override
    public long updateUser(UserModel userModel, long userId) {
        try {
            User user = new User();
            user.setUserId(userId);
            user.setName(userModel.getName());
            user.setEmail(userModel.getEmail());
            user.setMobileNumber(userModel.getMobileNumber());
            user.setPassword(userModel.getPassword());
            user.setModifiedAt(new Date());
            return userRepository.save(user).getUserId();
        } catch(Exception e) {
            throw new CustomException("Error storing user record");
        }
    }

    @Override
    public long updateUserAddress(UserAddressModel userAddressModel, long addressId) {
        try {
            UserAddress userAddress = new UserAddress();
            userAddress.setAddressId(addressId);
            userAddress.setUserId(userAddressModel.getUserId());
            userAddress.setCity(userAddressModel.getCity());
            userAddress.setLandmark(userAddressModel.getLandmark());
            userAddress.setLine(userAddressModel.getLine());
            userAddress.setPincode(userAddressModel.getPincode());
            userAddress.setState(userAddressModel.getState());
            userAddress.setModifiedAt(new Date());
            return userAddressRepository.save(userAddress).getAddressId();
        } catch(Exception e) {
            throw new CustomException("Error storing user record");
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers().orElseThrow(() -> new CustomException("Error fetching user records"));
    }

    @Override
    public List<User> getUsersByCity(String city) {
        return userRepository.getUserByCity(city).orElseThrow(() -> new CustomException("Error fetching user records"));
    }

    @Override
    public User getUsersById(long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new CustomException("Error fetching user record"));
    }

    @Override
    public List<UserAddress> getUserAddressById(long userId) {
        return userAddressRepository.findByUserId(userId).orElseThrow(() -> new CustomException("Error fetching user records"));
    }

    @Override
    public User signIn(String name, String password){
      return userRepository.findByNameAndPassword(name, password).orElseThrow(() -> new CustomException(" user not found"));
    }

    @Override
    public void delete(long userId){
      userRepository.deleteById(userId);
    }
}
