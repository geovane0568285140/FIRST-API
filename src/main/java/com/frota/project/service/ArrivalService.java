package com.frota.project.service;


import com.frota.project.dtos.arrivalRecord.InputArrivalRecordDto;
import com.frota.project.dtos.arrivalRecord.OutputArrivalRecordDto;
import com.frota.project.infra.security.SecurityFilter;
import com.frota.project.infra.security.TokenService;
import com.frota.project.model.ArrivalModel;
import com.frota.project.model.ExitModel;
import com.frota.project.model.ObservationModel;
import com.frota.project.model.UserModel;
import com.frota.project.repository.ArrivalRecordRepository;
import com.frota.project.repository.ExitRecordRepository;
import com.frota.project.repository.ObservationRepository;
import com.frota.project.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ArrivalService {

    @Autowired
    private ArrivalRecordRepository arrivalRecordRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObservationRepository observationRepository;

    @Autowired
    private ExitRecordRepository exitRecordRepository;

    @Autowired
    private TokenService tokenService;

    @Transactional
    public ResponseEntity create(HttpServletRequest request, InputArrivalRecordDto dto) {
        try {

            String token = new SecurityFilter().recoverToken(request);
            String stringUuidToken = tokenService.validationToken(token);
            UUID uuidUser = UUID.fromString(stringUuidToken);

            UserModel userModel;
            ExitModel exitModel;

            try {
                userModel = userRepository.findById(uuidUser).orElseThrow(() -> new RuntimeException(
                        "ERROR - Not found userModel"));
                exitModel = exitRecordRepository.findById(dto.fk_exit_record()).orElseThrow(() -> new RuntimeException(
                        "ERROR - Not found exitModel"));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            ObservationModel obs = null;
            if (dto.observation() != null && !dto.observation().equals("")) {
                obs = observationRepository.save(new ObservationModel(dto.observation()));
            }

            ArrivalModel arrivalModel = new ArrivalModel(exitModel, obs, //obs = observation
                    userModel, dto.km_arrival());

            arrivalRecordRepository.save(arrivalModel);

            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
        }
    }

    @Transactional
    public ResponseEntity update(HttpServletRequest request, UUID uuidArrival, InputArrivalRecordDto dto) {
        try {

            String token = new SecurityFilter().recoverToken(request);
            String stringUuidToken = tokenService.validationToken(token);
            UUID uuidUser = UUID.fromString(stringUuidToken);

            ArrivalModel arrivalModel;
            UserModel userModel;
            ExitModel exitModel;
            ObservationModel observationModel;

            try {
                arrivalModel = arrivalRecordRepository.findById(uuidArrival).orElseThrow(() -> new RuntimeException(
                        "ERROR - Not found arrivalModel"));
                userModel = userRepository.findById(uuidUser).orElseThrow(() -> new RuntimeException(
                        "ERROR - Not found userModel"));
                exitModel = exitRecordRepository.findById(dto.fk_exit_record()).orElseThrow(() -> new RuntimeException(
                        "ERROR - Not found exitModel"));
                observationModel = observationRepository.findById(arrivalModel.getFk_observation(
                ).getId_observation()).orElseThrow(() -> new RuntimeException(
                        "ERROR - Not Found observationModel"));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            observationModel.setText_observation(dto.observation());

            arrivalModel.setFk_exit_record(exitModel);
            arrivalModel.setFk_user(userModel);
            arrivalModel.setFk_observation(observationModel);
            arrivalModel.setKm_arrival(dto.km_arrival());
            arrivalModel.setN_mov(2);

            return ResponseEntity.status(HttpStatus.CREATED).body(arrivalRecordRepository.save(arrivalModel));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<OutputArrivalRecordDto> getArrivals(HttpServletRequest request, int page, int size) {

        try {

            String token = new SecurityFilter().recoverToken(request);
            String uuidUserStrign = tokenService.validationToken(token);
            UUID uuidUser = UUID.fromString(uuidUserStrign);

            Optional<UserModel> userModel = userRepository.findById(uuidUser);
            Pageable pageable = PageRequest.of(page, size);
            List<ArrivalModel> arrivals = arrivalRecordRepository.findAllOrdered(userModel, pageable).getContent();

            return arrivals.stream().map(e -> new OutputArrivalRecordDto(e.getId_arrival_record(),
                    e.getFk_exit_record() != null ? e.getFk_exit_record().getId_exit_record() : null,
                    e.getFk_observation() != null ? e.getFk_observation().getId_observation() : null,
                    e.getFk_user() != null ? e.getFk_user().getId_user() : null,
                    e.getCreated_at(),
                    e.getKm_arrival(),
                    e.getN_mov())).toList();

        } catch (Exception e) {
            return new ArrayList<>();
        }

    }
}
