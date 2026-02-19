package com.frota.project.service;

import com.frota.project.dtos.users.InputUserRecordDto;
import com.frota.project.dtos.users.OutPutFirstNameUser;
import com.frota.project.dtos.users.OutPutUserRecordDto;
import com.frota.project.dtos.users.RegisterRecordDto;
import com.frota.project.model.UserModel;
import com.frota.project.model.UserRole;
import com.frota.project.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    /* private final UserRepository userRepository_; */

    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository_) {
        userRepository = userRepository_;
    }

    @Transactional
    public ResponseEntity<List<OutPutFirstNameUser>> getFirstsUser(int page, int size) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            UserModel user = userRepository.findById(UUID.fromString(authentication.getName())).orElseThrow(() -> new RuntimeException(
                    "ERROR in findById -- User"));

            if (user.getType_user() != UserRole.ADMIN) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Collections.emptyList());
            }

            return ResponseEntity.status(HttpStatus.OK).body(userRepository.findFirstsNameUsers(PageRequest.of(page,
                    size)).getContent().stream().map(e -> new OutPutFirstNameUser(e.uuidUser(),
                    e.nameUser())).toList());

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());

        }
    }

    @Transactional
    public UserModel getUser(String email) {
        return (UserModel) userRepository.findByEmail(email);
    }

    @Transactional
    public ResponseEntity<OutPutUserRecordDto> getName_User(String nameUser) {
        try {
            UserModel u;
            u = userRepository.findByNameUser(nameUser);

            if (u != null)
                return ResponseEntity.status(HttpStatus.OK).body(new OutPutUserRecordDto(
                        u.getName(),
                        u.getNameUser(),
                        u.getPassword_hash(),
                        u.getEmail(),
                        u.getType_user(),
                        u.isActive(),
                        u.getCpf(),
                        u.getDate_brith(),
                        u.getNum_cnh(),
                        u.getCategory_cnh(),
                        u.getDate_emission_cnh(),
                        u.getDate_validity_cnh(),
                        u.getRegistration_renach_cnh()));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public ResponseEntity createUser(RegisterRecordDto dto) {
        var name = this.userRepository.findByEmail(dto.email());
        if (name != null) return ResponseEntity.badRequest().build();

        String encryptePassword = new BCryptPasswordEncoder().encode(dto.password());
        UserModel newUser = new UserModel(dto.full_name(),
                dto.name_user(),
                encryptePassword,
                dto.email(),
                dto.type_user(),
                dto.active(),
                dto.cpf(),
                dto.date_brith(),
                dto.num_cnh(),
                dto.category_cnh(),
                dto.date_emission_cnh(),
                dto.date_validity_cnh(),
                dto.registration_renach_cnh());

        this.userRepository.save(newUser);
        return ResponseEntity.ok().build();
    }

    public String updateUser(UUID uuid, InputUserRecordDto dto) {
        try {
            UserModel usermodel;
            try {
                usermodel = userRepository.findById(uuid).orElseThrow(() -> new RuntimeException(
                        "ERROR - finById in runtime"));
            } catch (Exception e) {
                return "ERROR - in localization from car";
            }

            usermodel.setActive(dto.active());
            usermodel.setType_user(dto.type_user());
            usermodel.setName(dto.full_name());
            usermodel.setEmail(dto.email());

            String encrytePassword = new BCryptPasswordEncoder().encode(dto.password());
            usermodel.setPassword_hash(encrytePassword);

            userRepository.save(usermodel);
            return "Success in update";

        } catch (Exception e) {
            return "ERROR - update to User";
        }
    }

    @Transactional
    public String deleteUser(UUID uuid) {
        try {
            UserModel userModel;
            try {
                userModel = userRepository.findById(uuid).orElseThrow(() -> new RuntimeException(
                        "ERROR - findById in runtime"));
            } catch (Exception e) {
                return "ERROR - in localization from user";
            }
            userRepository.delete(userModel);
        } catch (Exception e) {
            return "ERROR - deleted user to db";
        }
        return "Success in delete";
    }
}
