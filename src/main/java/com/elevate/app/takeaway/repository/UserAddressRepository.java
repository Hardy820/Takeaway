package com.elevate.app.takeaway.repository;

import com.elevate.app.takeaway.dto.user.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {
    Optional<List<UserAddress>> findByUserId(long userId);

}
