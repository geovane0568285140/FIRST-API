package com.frota.project.repository;

import com.frota.project.dtos.carRequest.OutPutCarRequestUUIDandStatus;
import com.frota.project.dtos.users.OutPutFirstNameUser;
import com.frota.project.model.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {

    Optional<UserModel> findById(UUID uuid);

    UserDetails findByEmail(String email);

    UserModel findByNameUser(String name_user);

    @Query("SELECT u.id_user, u.nameUser FROM UserModel u")
    Page<OutPutFirstNameUser> findFirstsNameUsers(Pageable pageable);

    /*@Query("SELECT c.id_car_request, c.n_mov, c.requested_at, c.status " +
            "FROM CarRequestModel c WHERE c.fk_user.id_user = :userFk " +
            "ORDER BY c.requested_at DESC")
    Page<OutPutCarRequestUUIDandStatus> findLastRequests(@Param("userFk") UUID userFk, Pageable pageable);*/
}
