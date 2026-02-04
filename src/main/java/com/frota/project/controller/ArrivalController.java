package com.frota.project.controller;

import com.frota.project.dtos.arrivalRecord.InputArrivalRecordDto;
import com.frota.project.dtos.arrivalRecord.OutputArrivalRecordDto;
import com.frota.project.model.ArrivalModel;
import com.frota.project.service.ArrivalService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/frotas/arrival")
public class ArrivalController {

    @Autowired
    private ArrivalService arrivalService;

    @PostMapping("/create")
    public ResponseEntity create(HttpServletRequest request, @RequestBody InputArrivalRecordDto data) {
        return arrivalService.create(request, data);
    }

    @PutMapping("/update/{uuid}")
    public ResponseEntity update(HttpServletRequest request, @PathVariable UUID uuid, @RequestBody InputArrivalRecordDto data) {
        return arrivalService.update(request, uuid, data);
    }

    @GetMapping("/get")
    public ResponseEntity<List<OutputArrivalRecordDto>> getRequest(HttpServletRequest request, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.status(HttpStatus.OK).body(arrivalService.getArrivals(request, page, size));
    }
}
