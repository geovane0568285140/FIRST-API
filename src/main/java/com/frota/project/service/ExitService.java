package com.frota.project.service;

import com.frota.project.dtos.ExitRecord.InputCreateExitRecordDto;
import com.frota.project.dtos.ExitRecord.InputUpdateExitRecordDto;
import com.frota.project.dtos.ExitRecord.OutputUUIDAndLocalDateTimeExitsRecord;
import com.frota.project.dtos.ExitRecord.OutputExitRecord;
import com.frota.project.infra.security.SecurityFilter;
import com.frota.project.infra.security.TokenService;
import com.frota.project.model.*;
import com.frota.project.repository.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExitService {

    @Autowired
    private ExitRecordRepository exitRecordRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarRequestRepository carRequestRepository;

    @Autowired
    private ObservationRepository observationRepository;

    @Autowired
    private CarFrotaRepository carFrotaRepository;

    @Autowired
    private TokenService tokenService;

    @Transactional
    public ResponseEntity create(HttpServletRequest request, InputCreateExitRecordDto dto) {
        try {

            String token = new SecurityFilter().recoverToken(request);
            String idUser = tokenService.validationToken(token);
            UUID uuidUser = UUID.fromString(idUser);

            UserModel fk_userModel;
            CarFrotaModel fk_carFrotaModel;
            CarRequestModel fk_carRequestModel = null;
            try {
                fk_userModel = userRepository.findById(uuidUser).orElseThrow(() -> new RuntimeException(
                        "ERROR - findById UserModel in runtime"));
                fk_carFrotaModel = carFrotaRepository.findById(dto.fk_car_frota()).orElseThrow(() -> new RuntimeException(
                        "ERROR - findById CarFrotaModel in runtime"));
                if (dto.fk_car_request() != null)
                    fk_carRequestModel = carRequestRepository.findById(dto.fk_car_request()).orElseThrow(() -> new RuntimeException(
                            "ERROR - findById in runtime"));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            ObservationModel obs = null;
            if(dto.observation() != null && !dto.observation().equals("")){
                obs = observationRepository.save(new ObservationModel(dto.observation()));
            }

            ExitModel requestExit = new ExitModel(fk_carFrotaModel,
                    fk_userModel,
                    fk_carRequestModel,
                    obs,
                    dto.km_exit());

            exitRecordRepository.save(requestExit);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
        }
    }

    @Transactional
    public String update(HttpServletRequest request, UUID uuid, InputUpdateExitRecordDto dto) {
        try {

            String token = new SecurityFilter().recoverToken(request);
            String idUser = tokenService.validationToken(token);
            UUID uuidUser = UUID.fromString(idUser);

            ExitModel exitModel;
            CarFrotaModel fk_carFrotaModel;
            UserModel fk_userModel;
            CarRequestModel fk_carRequestModel = null;
            ObservationModel fk_observationModel = null;
            try {
                exitModel = exitRecordRepository.findById(uuid).orElseThrow(() -> new RuntimeException(
                        "ERROR - FindById ExitModel in runtime"));
                fk_userModel = userRepository.findById(uuidUser).orElseThrow(() -> new RuntimeException(
                        "ERROR - FindById UserModel in runtime"));
                fk_carFrotaModel = carFrotaRepository.findById(dto.fk_car_frota()).orElseThrow(() -> new RuntimeException(
                        "ERROR - FindById CarFrotaModel in runtime"));
                if (dto.fk_car_request() != null)
                    fk_carRequestModel = carRequestRepository.findById(dto.fk_car_request()).orElseThrow(() -> new RuntimeException(
                            "ERROR - FindById CarRequest in runtime"));
                if (dto.fk_observation() != null)
                    fk_observationModel = observationRepository.findById(dto.fk_observation()).orElseThrow(() -> new RuntimeException(
                            "ERROR - FindById ObservationModel in runtime"));
            } catch (Exception e) {
                return "ERROR - Found data's in DB";
            }

            exitModel.setFk_user(fk_userModel);
            exitModel.setFk_car_frota(fk_carFrotaModel);
            exitModel.setFk_car_request(fk_carRequestModel);
            exitModel.setFk_observation(fk_observationModel);
            exitModel.setKm_exit(dto.km_exit());

            exitRecordRepository.save(exitModel);
            return "Success update";
        } catch (Exception e) {
            return "ERROR - Method update in class ExitService";
        }
    }

    @Transactional
    public OutputExitRecord getExitModel(UUID uuid) {
        try {

            ExitModel ex = exitRecordRepository.findById(uuid).orElseThrow(() -> new RuntimeException("ERROR"));


            Optional<UUID> fk_car_request = Optional.ofNullable(ex.getFk_car_request().getId_car_request());

            UUID fk_car_requests = null;
            UUID fk_observation = null;

            if (ex.getFk_car_request() != null) fk_car_requests = ex.getFk_car_request().getId_car_request();

            if (ex.getFk_observation() != null) fk_observation = ex.getFk_observation().getId_observation();

            return new OutputExitRecord(ex.getId_exit_record(),
                    ex.getFk_car_frota().getId_frota(),
                    ex.getFk_user().getId_user(),
                    fk_car_requests,
                    fk_observation,
                    ex.getDate_exit(),
                    ex.getKm_exit(),
                    ex.getN_mov());

        } catch (Exception e) {
            throw new RuntimeException("ERROR");
        }
    }

    @Transactional
    public List<OutputExitRecord> getExits(HttpServletRequest request, int page, int size) {

        try {

            Pageable pageable = PageRequest.of(page, size);

            String token = new SecurityFilter().recoverToken(request);
            String idToken = tokenService.validationToken(token);
            UUID uuidUser = UUID.fromString(idToken);

            UserModel userModel = userRepository.findById(uuidUser).orElseThrow(() -> new RuntimeException(
                    "ERROR - find userModel"));
            List<ExitModel> exits = exitRecordRepository.findAllOrdered(userModel, pageable).getContent();
            return exits.stream().map(e -> new OutputExitRecord(e.getId_exit_record(),
                    e.getFk_car_frota() != null ? e.getFk_car_frota().getId_frota() : null,
                    e.getFk_user() != null ? e.getFk_user().getId_user() : null,
                    e.getFk_car_request() != null ? e.getFk_car_request().getId_car_request() : null,
                    e.getFk_observation() != null ? e.getFk_observation().getId_observation() : null,
                    e.getDate_exit(),
                    e.getKm_exit(),
                    e.getN_mov())).toList();

        } catch (Exception e) {
            return new ArrayList<>();
        }

    }

    @Transactional
    public List<OutputUUIDAndLocalDateTimeExitsRecord> getExitsBeingFkNullInArrival(HttpServletRequest request) {
        try {

            String token = new SecurityFilter().recoverToken(request);
            String idTokenString = tokenService.validationToken(token);
            UUID idUser = UUID.fromString(idTokenString);

            Pageable pageable = PageRequest.of(0, 5);

            List<OutputUUIDAndLocalDateTimeExitsRecord> listUUIDExits = exitRecordRepository.findIsNullIdArrival(idUser, pageable).getContent().stream().map(
                    e -> new OutputUUIDAndLocalDateTimeExitsRecord(e.id_exit_record(),
                            e.date_exit())).toList();
            return listUUIDExits;
        } catch (Exception e) {
            LoggerFactory.getLogger(ExitService.class).error(e.getMessage());
            return new ArrayList<>();
        }
    }
}