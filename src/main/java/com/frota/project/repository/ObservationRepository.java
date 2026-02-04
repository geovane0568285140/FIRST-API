package com.frota.project.repository;

import com.frota.project.model.ObservationModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ObservationRepository extends JpaRepository<ObservationModel, UUID> {
}
