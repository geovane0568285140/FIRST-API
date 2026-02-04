package com.frota.project.controller;


import com.frota.project.dtos.cars.frotas.InputCarFrotaRecordDto;
import com.frota.project.dtos.cars.frotas.OutputUUIDAndNumCarRecordDto;
import com.frota.project.service.CarFrotaService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/frotas/cars")
public class CarFrotaController {


    private final CarFrotaService carFrotaService;

    public CarFrotaController(CarFrotaService carFrotaService) {
        this.carFrotaService = carFrotaService;
    }


    @GetMapping("/getCarsUuidNumCars")
    public ResponseEntity<List<OutputUUIDAndNumCarRecordDto>> getCarsUUIDNumCar(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.status(HttpStatus.OK).body(carFrotaService.getCarsUUIDNumCar(page, size));
    }

    @GetMapping("/getLastUsedCars")
    public ResponseEntity<List<OutputUUIDAndNumCarRecordDto>> getLastUsedCars(HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(carFrotaService.getCarReferencesByUserSortedByDateDesc(request));
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody InputCarFrotaRecordDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(carFrotaService.create(dto));
    }

    @DeleteMapping("delete/{uuid}")
    public ResponseEntity<String> delete(@PathVariable UUID uuid) {
        return ResponseEntity.status(HttpStatus.OK).body(carFrotaService.delete(uuid));
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<String> update(@PathVariable UUID uuid, @RequestBody InputCarFrotaRecordDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(carFrotaService.update(uuid, dto));
    }


}
