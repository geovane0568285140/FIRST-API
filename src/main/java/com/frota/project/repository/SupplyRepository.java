package com.frota.project.repository;

import com.frota.project.model.SupplyModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SupplyRepository extends JpaRepository<SupplyModel, UUID> {
}
