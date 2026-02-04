package com.frota.project.dtos.arrivalRecord;

import com.frota.project.model.ExitModel;
import com.frota.project.model.ObservationModel;
import com.frota.project.model.UserModel;

import java.util.UUID;

public record InputArrivalRecordDto (
        UUID fk_exit_record,
        String observation,
        int km_arrival
){
}
