package com.frota.project.service;

import com.frota.project.dtos.SupplyRecord.InputSupplyRecordDto;
import com.frota.project.model.CarFrotaModel;
import com.frota.project.model.SupplyModel;
import com.frota.project.model.UserModel;
import com.frota.project.repository.CarFrotaRepository;
import com.frota.project.repository.SupplyRepository;
import com.frota.project.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SupplyService {

    @Autowired
    private SupplyRepository supplyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarFrotaRepository carFrotaRepository;

    @Transactional
    public ResponseEntity create(InputSupplyRecordDto dto) {

        try {
            UserModel userModel;
            CarFrotaModel carFrotaModel;
            try {
                userModel = userRepository.findById(dto.fk_user()).orElseThrow(() ->
                        new RuntimeException("ERROR - Not found userModel"));
                carFrotaModel = carFrotaRepository.findById(dto.fk_car_frota()).orElseThrow(() ->
                        new RuntimeException("ERROR - Not Found carFrotaModel"));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return ResponseEntity.status(HttpStatus.OK).body(supplyRepository.save(
                    new SupplyModel(
                            carFrotaModel,
                            userModel,
                            dto.value_total(),
                            dto.litros(),
                            dto.km_record(),
                            dto.n_mov()
                    )
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
        }
    }

    @Transactional
    public ResponseEntity update(UUID uuid, InputSupplyRecordDto dto) {
        try {
            UserModel userModel;
            CarFrotaModel carFrotaModel;
            SupplyModel supplyModel;

            try {
                supplyModel = supplyRepository.findById(uuid).orElseThrow(() ->
                        new RuntimeException("ERROR - Not found SupplyModel"));
                userModel = userRepository.findById(dto.fk_user()).orElseThrow(() ->
                        new RuntimeException("ERROR - Not found userModel"));
                carFrotaModel = carFrotaRepository.findById(dto.fk_car_frota()).orElseThrow(() ->
                        new RuntimeException("ERROR - Not Found carFrotaModel"));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            supplyModel.setFk_car_frota(carFrotaModel);
            supplyModel.setFk_user(userModel);
            supplyModel.setLitros(dto.litros());
            supplyModel.setLitros(dto.litros());
            supplyModel.setKm_record(dto.km_record());
            supplyModel.setN_mov(dto.n_mov());

            return ResponseEntity.status(HttpStatus.OK).body(supplyRepository.save(supplyModel));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
        }
    }
}
