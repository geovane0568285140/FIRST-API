package com.frota.project.service;

import com.frota.project.dtos.cars.frotas.InputCarFrotaRecordDto;
import com.frota.project.dtos.cars.frotas.OutputUUIDAndNumCarRecordDto;
import com.frota.project.infra.security.SecurityFilter;
import com.frota.project.infra.security.TokenService;
import com.frota.project.model.CarFrotaModel;
import com.frota.project.repository.CarFrotaRepository;
import com.frota.project.repository.ExitRecordRepository;
import com.frota.project.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CarFrotaService {

    private static final Logger log = LoggerFactory.getLogger(CarFrotaService.class);

    @Autowired
    private CarFrotaRepository carFrotaRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ExitRecordRepository exitRecordRepository;

    @Autowired
    private UserRepository userRepository;

    public CarFrotaService(CarFrotaRepository carFrotaRepository) {
        this.carFrotaRepository = carFrotaRepository;
    }

    @Transactional
    public List<OutputUUIDAndNumCarRecordDto> getCarsUUIDNumCar(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        //List<CarFrotaModel> car = carFrotaRepository.findAllOrdered(pageable);
        try{
            return carFrotaRepository.findAllOrdered(pageable).getContent().stream().map(e -> new OutputUUIDAndNumCarRecordDto(
                    e.id_frota(),
                    e.num_car())).toList();
        } catch (Exception e){
            return new ArrayList<>();
        }

        // car.stream().map(c -> new OutputUUIDAndNumCarRecordDto(c.getId_frota(), c.getLicensePlate(), c.getModel(), c.isActive(), c.getCreated_at())).toList();
    }

    public List<OutputUUIDAndNumCarRecordDto> getCarReferencesByUserSortedByDateDesc(HttpServletRequest request) {

        Pageable pageable = PageRequest.of(0, 5);

        String token = new SecurityFilter().recoverToken(request);
        String idUserString = tokenService.validationToken(token);
        UUID uuidUser = UUID.fromString(idUserString);

        try {
            return exitRecordRepository.findLastUsedCars(uuidUser,
                    pageable).getContent().stream().map(e -> new OutputUUIDAndNumCarRecordDto(e.id_frota(),
                    e.num_car())).toList();
        } catch (Exception e) {
            LoggerFactory.getLogger(CarFrotaService.class).error(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Transactional
    public String create(InputCarFrotaRecordDto dto) {
        CarFrotaModel carFrotaModel = new CarFrotaModel(dto.license_plate(),
                dto.model(),
                dto.active(),
                dto.mark(),
                dto.manufaturing_year(),
                dto.model_year(),
                dto.color(),
                dto.category(),
                dto.fuel_type(),
                dto.current_mileage(),
                dto.num_crlv(),
                dto.date_licensing(),
                dto.date_maturity_IPVA(),
                dto.num_car());

        try {
            carFrotaRepository.save(carFrotaModel);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        {
            return "Created - Success";
        }
    }

    @Transactional
    public String delete(UUID uuid) {
        try {

            CarFrotaModel carfrotamodel;

            try {
                carfrotamodel = carFrotaRepository.findById(uuid).orElseThrow(() -> new RuntimeException(
                        "ERROR - FinById in runtime"));
            } catch (Exception e) {
                return "ERROR - rty runtime line 65 method delete - class:CarFrotaService";
            }

            carFrotaRepository.delete(carfrotamodel);
            return "Success delete";

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Transactional
    public String update(UUID uuid, InputCarFrotaRecordDto dto) {
        try {
            CarFrotaModel carmodel;
            try {
                carmodel = carFrotaRepository.findById(uuid).orElseThrow(() -> new RuntimeException(
                        "findById error in getmodelcar function"));
            } catch (Exception e) {
                return "Erro ao localizar o carro";
            }

            carmodel.setLicensePlate(dto.license_plate());
            carmodel.setModel(dto.model());
            carmodel.setActive(dto.active());
            carmodel.setMark(dto.mark());
            carmodel.setManufaturing_year(dto.manufaturing_year());
            carmodel.setModel_year(dto.model_year());
            carmodel.setColor(dto.color());
            carmodel.setCategory(dto.category());
            carmodel.setFuel_type(dto.fuel_type());
            carmodel.setCurrent_mileage(dto.current_mileage());
            carmodel.setNum_crlv(dto.num_crlv());
            carmodel.setDate_licensing(dto.date_licensing());
            carmodel.setDate_maturity_IPVA(dto.date_maturity_IPVA());
            carmodel.setNum_car(dto.num_car());

            carFrotaRepository.save(carmodel);
            return "Success in update";
        } catch (RuntimeException e) {
            return "Deu ruim aqui kk";
        }
    }


}