package com.frota.project.service;

import com.frota.project.dtos.carRequest.InputCarRequestRecordDto;
import com.frota.project.dtos.carRequest.OutPutCarRequestRecordDto;
import com.frota.project.dtos.carRequest.OutPutCarRequestUUIDandStatus;
import com.frota.project.model.CarRequestModel;
import com.frota.project.model.UserModel;
import com.frota.project.model.UserRole;
import com.frota.project.repository.CarRequestRepository;
import com.frota.project.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class CarRequestService {


    @Autowired
    private CarRequestRepository carRequestRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public ResponseEntity create(InputCarRequestRecordDto dto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uuidUser = authentication.getName();
        UserModel user;
        try {
            user = userRepository.findById(UUID.fromString(uuidUser)).orElseThrow(() -> new RuntimeException(
                    "Usuário não encontrado"));
        } catch (Exception e) {
            return ResponseEntity.ok().body("ERROR - User não encontrado");
        }


        CarRequestModel request = new CarRequestModel(
                user,
                dto.origin(),
                dto.destination(),
                dto.reason(),
                "Pendente",
                true
        );

        carRequestRepository.save(request);
        return ResponseEntity.ok().build();
    }

    @Transactional
    public ResponseEntity<String> update(UUID uuid, InputCarRequestRecordDto dto) {
        try {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UUID uuidUser = UUID.fromString(authentication.getName());

            CarRequestModel carRequestModel;
            carRequestModel = getuuid(uuid);

            UserModel user = userRepository.findById(uuidUser).orElseThrow(() -> new RuntimeException(
                    "ERROR in findById -- User"));

            if (carRequestModel == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR - Request not found");
            }
            if (!carRequestModel.getStatus().equals("Pendente") && user.getType_user() != UserRole.ADMIN){
                return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("ERROR - User without permission");
            }
            if (user.getType_user() == UserRole.USER){
                return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("ERROR - User without permission");
            }

            if (dto.origin() != null) carRequestModel.setOrigin(dto.origin());
            if (dto.n_mov() != null) carRequestModel.setN_mov(dto.n_mov());
            if (dto.destination() != null) carRequestModel.setDestination(dto.destination());
            if (dto.origin() != null) carRequestModel.setReason(dto.origin());
            if (dto.status() != null) carRequestModel.setStatus(dto.status());
            if (dto.active() != null) carRequestModel.setActive(dto.active());

            carRequestRepository.save(carRequestModel);
            return ResponseEntity.status(HttpStatus.OK).body("success update");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("ERROR - methodo in class CarRequestService");
        }
    }

    //utilization in function the class controller
    @Transactional
    public Optional<OutPutCarRequestRecordDto> get(UUID uuid) throws RuntimeException {

        try {

            CarRequestModel c;
            c = carRequestRepository.findById(uuid).orElseThrow(() -> new RuntimeException("ERROR"));

            return Optional.of(new OutPutCarRequestRecordDto(
                    c.getId_car_request(),
                    c.getFk_user().getId_user(),
                    c.getOrigin(),
                    c.getDestination(),
                    c.getReason(),
                    c.getRequested_at(),
                    c.getStatus()));

        } catch (Exception e) {
            throw new RuntimeException("ERROR - method get in class CarRequestService");
        }

    }

    //utilization in function´s update and delete
    @Transactional
    public CarRequestModel getuuid(UUID uuid) {
        try {

            CarRequestModel carRequestModel;

            carRequestModel = carRequestRepository.findById(uuid).orElseThrow(() -> new RuntimeException(
                    "ERROR - FindById GET requests"));
            return carRequestModel;

        } catch (Exception e) {
            throw new RuntimeException("ERROR - Method getuuid in class CarRequestService");
        }
    }

    @Transactional
    public String delete(UUID uuid) {
        try {

            CarRequestModel carRequestModel;
            carRequestModel = getuuid(uuid);

            if (carRequestModel == null) {
                return "ERROR - request not found";
            }

            carRequestRepository.delete(carRequestModel);
            return "Success delete requests";

        } catch (Exception e) {
            return "ERROR in deleted request";
        }
    }


    public List<OutPutCarRequestUUIDandStatus> getRequests(int page, int size) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UUID uuidUser = UUID.fromString(authentication.getName());


            return carRequestRepository.findLastRequests(uuidUser,
                    PageRequest.of(page, size)).getContent().stream().map(e -> new OutPutCarRequestUUIDandStatus(
                    e.uuid(),
                    e.n_mov(),
                    e.requested_at(),
                    e.status())).toList();

        } catch (Exception e) {
            Logger.getLogger(e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<OutPutCarRequestUUIDandStatus> getRequestsFilterStatus(int page, int size, String filterStatus) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UUID uuidUser = UUID.fromString(authentication.getName());


            return carRequestRepository.findLastRequestsStatusInPending(uuidUser,
                    filterStatus,
                    PageRequest.of(page, size)).stream().map(e -> new OutPutCarRequestUUIDandStatus(
                    e.uuid(),
                    e.n_mov(),
                    e.requested_at(),
                    e.status())).toList();

        } catch (Exception e) {
            Logger.getLogger(e.getMessage());
            return new ArrayList<>();
        }
    }
}