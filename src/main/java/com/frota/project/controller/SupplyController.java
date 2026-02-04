package com.frota.project.controller;

import com.frota.project.dtos.SupplyRecord.InputSupplyRecordDto;
import com.frota.project.service.SupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/frotas/supply")
public class SupplyController {

    @Autowired
    private SupplyService supplyService;

    @PostMapping("/create/")
    public ResponseEntity create(@RequestBody InputSupplyRecordDto data){
        return ResponseEntity.status(HttpStatus.OK).body(supplyService.create(data));
    }
}
