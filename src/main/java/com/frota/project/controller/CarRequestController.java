package com.frota.project.controller;


import com.frota.project.dtos.carRequest.InputCarRequestRecordDto;
import com.frota.project.dtos.carRequest.OutPutCarRequestRecordDto;
import com.frota.project.dtos.carRequest.OutPutCarRequestUUIDandStatus;
import com.frota.project.service.CarRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
    @RequestMapping("/frotas/requests")
public class CarRequestController {

    @Autowired
    private CarRequestService carRequestService;

    @PostMapping({"/create"})
    public ResponseEntity createRequest(@RequestBody InputCarRequestRecordDto data) {
        return carRequestService.create(data);
    }

    @PutMapping({"update/{uuid}"})
    public ResponseEntity<?> updateRequest(@PathVariable UUID uuid, @RequestBody InputCarRequestRecordDto data) {
        return carRequestService.update(uuid, data);
    }

    @DeleteMapping("delete/{uuid}")
    public ResponseEntity delete(@PathVariable UUID uuid) {
        return ResponseEntity.status(HttpStatus.OK).body(carRequestService.delete(uuid));
    }

    @GetMapping({"/{uuid}"})
    public Optional<OutPutCarRequestRecordDto> getRequest(@PathVariable UUID uuid) {
        return carRequestService.get(uuid);
    }

    @GetMapping("/getLastsRequestsALL")
    public ResponseEntity<List<OutPutCarRequestUUIDandStatus>> getRequestsALLLast(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.status(HttpStatus.OK).body(carRequestService.getRequests(page, size));
    }

    @GetMapping("/getRequestsFilterStatus")
    public ResponseEntity<List<OutPutCarRequestUUIDandStatus>> getRequestsFilter(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "Pendente") String filterStatus) {
        return ResponseEntity.status(HttpStatus.OK).body(carRequestService.getRequestsFilterStatus(page, size, filterStatus));
    }


}
