package com.frota.project.repository;

import com.frota.project.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {

    Optional<UserModel> findById(UUID uuid);

    UserDetails findByEmail(String email);

    UserModel findByNameUser(String name_user);

}
