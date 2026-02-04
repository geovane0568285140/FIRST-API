package com.frota.project.controller;

import com.frota.project.dtos.ExitRecord.InputCreateExitRecordDto;
import com.frota.project.dtos.ExitRecord.InputUpdateExitRecordDto;
import com.frota.project.dtos.ExitRecord.OutputUUIDAndLocalDateTimeExitsRecord;
import com.frota.project.dtos.ExitRecord.OutputExitRecord;
import com.frota.project.service.ExitService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/frotas/exit")
public class ExitController {

    @Autowired
    private ExitService exitService;

    @PostMapping("/create")
    public ResponseEntity createRequest(HttpServletRequest request, @RequestBody InputCreateExitRecordDto data) {
        return exitService.create(request, data);
    }

    @PutMapping("/update/{uuid}")
    public ResponseEntity<String> updateRquest(HttpServletRequest request, @PathVariable UUID uuid, @RequestBody InputUpdateExitRecordDto data) {
        return ResponseEntity.status(HttpStatus.OK).body(exitService.update(request, uuid, data));
    }

    @GetMapping("/get/{uuid}")
    public ResponseEntity<OutputExitRecord> getExitRequest(@PathVariable UUID uuid) {
        return ResponseEntity.status(HttpStatus.OK).body(exitService.getExitModel(uuid));
    }

    @GetMapping("/get")
    public ResponseEntity<List<OutputExitRecord>> getRequest(HttpServletRequest request, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.status(HttpStatus.OK).body(exitService.getExits(request, page, size));
    }

    @GetMapping("/getExitsWithoutArrival")
    public ResponseEntity<List<OutputUUIDAndLocalDateTimeExitsRecord>> getExitsWithoutArrival(HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(exitService.getExitsBeingFkNullInArrival(request));
    }

}
