package com.frota.project.model;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "car_request")
public class CarRequestModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id_car_request;

    @ManyToOne
    @JoinColumn(name = "fk_user", nullable = false)
    private UserModel fk_user;

    @Column(name = "origin", nullable = false)
    private String origin;

    @Column(name = "destination", nullable = false)
    private String destination;

    @Column(name = "reason", nullable = true)
    private String reason;

    @Column(name = "requested_at", nullable = true, insertable = false, updatable = false)
    private LocalDateTime requested_at;

    @Column(name = "status", nullable = true, length = 20, insertable = false)
    // Status is 'Pendent', 'Approval' OR 'Canceled'
    private String status;

    @Column(name = "n_mov", nullable = false, insertable = false, updatable = false)
    private int n_mov;

    @Column(name = "active")
    private Boolean active;

    public CarRequestModel(){
        //JPA Requer um construtur vazio
    }

    public CarRequestModel(UserModel fk_user, String origin, String destination, String reason, String status, Boolean active) {
        this.fk_user = fk_user;
        this.origin = origin;
        this.destination = destination;
        this.reason = reason;
        this.status = status;
        this.active = active;
    }

    public UUID getId_car_request() {
        return id_car_request;
    }

    public void setId_car_request(UUID id_car_request) {
        this.id_car_request = id_car_request;
    }

    public UserModel getFk_user() {
        return fk_user;
    }

    public void setFk_user(UserModel fk_user) {
        this.fk_user = fk_user;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getRequested_at() {
        return requested_at;
    }

    public void setRequested_at(LocalDateTime requested_at) {
        this.requested_at = requested_at;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getN_mov() {
        return n_mov;
    }

    public void setN_mov(int n_mov) {
        this.n_mov = n_mov;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    /* id_car_request | uuid                        |           | not null | gen_random_uuid()
 fk_user        | uuid                        |           | not null |
 origin         | text                        |           | not null |
 destination    | text                        |           | not null |
 reason         | text                        |           |          |
 requested_at   | timestamp without time zone |           |          | CURRENT_TIMESTAMP
 status         | character varying(20)       |           |          | 'Pendente'::character varying*/
}
