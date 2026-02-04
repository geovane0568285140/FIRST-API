package com.frota.project.model;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "arrival_record")
public class ArrivalModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id_arrival_record;

    @OneToOne
    @JoinColumn(name = "fk_exit_record")
    private ExitModel fk_exit_record;

    @OneToOne
    @JoinColumn(name = "fk_observation", nullable = true)
    private ObservationModel fk_observation;

    @ManyToOne
    @JoinColumn(name = "fk_user", nullable = false, unique = false)
    private UserModel fk_user;

    @Column(name = "created_at", nullable = false, insertable = false, updatable = false, unique = false)
    private LocalDateTime created_at;

    @Column(name = "km_arrival", nullable = false, unique = false)
    private int km_arrival;

    @Column(name = "n_mov", insertable = false, updatable = false, unique = false)
    private int n_mov;

    public ArrivalModel(){}

    public ArrivalModel(
            ExitModel fk_exit_record,
            ObservationModel fk_observation,
            UserModel fk_user,
            int km_arrival
            ){
        this.fk_exit_record = fk_exit_record;
        this.fk_observation = fk_observation;
        this.fk_user = fk_user;
        this.km_arrival = km_arrival;
        this.n_mov = n_mov;
    }

    public UUID getId_arrival_record() {
        return id_arrival_record;
    }

    public void setId_arrival_record(UUID id_arrival_record) {
        this.id_arrival_record = id_arrival_record;
    }

    public ExitModel getFk_exit_record() {
        return fk_exit_record;
    }

    public void setFk_exit_record(ExitModel fk_exit_record) {
        this.fk_exit_record = fk_exit_record;
    }

    public ObservationModel getFk_observation() {
        return fk_observation;
    }

    public void setFk_observation(ObservationModel fk_observation) {
        this.fk_observation = fk_observation;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public int getKm_arrival() {
        return km_arrival;
    }

    public void setKm_arrival(int km_arrival) {
        this.km_arrival = km_arrival;
    }

    public int getN_mov() {
        return n_mov;
    }

    public void setN_mov(int n_mov) {
        this.n_mov = n_mov;
    }

    public UserModel getFk_user() {
        return fk_user;
    }

    public void setFk_user(UserModel fk_user) {
        this.fk_user = fk_user;
    }

    /* id_arrival_record | uuid                        |           | not null | gen_random_uuid()
 fk_exit_record    | uuid                        |           | not null |
 date_arrival      | timestamp without time zone |           |          | CURRENT_TIMESTAMP
 km_arrival        | integer                     |           | not null |
 fk_observation    | uuid                        |           |          | */
}
