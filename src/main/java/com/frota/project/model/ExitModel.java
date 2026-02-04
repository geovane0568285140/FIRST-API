package com.frota.project.model;

import jakarta.persistence.*;


import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "exit_record")
public class ExitModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id_exit_record;

    @ManyToOne
    @JoinColumn(name = "fk_car_frota", nullable = false)
    private CarFrotaModel fk_car_frota;

    @ManyToOne
    @JoinColumn(name = "fk_user", nullable = false)
    private UserModel fk_user;

    @OneToOne()
    @JoinColumn(name = "fk_car_request")
    private CarRequestModel fk_car_request;

    @OneToOne()
    @JoinColumn(name = "fk_observation")
    private ObservationModel fk_observation;

    @Column(name = "date_exit", insertable = false)
    private LocalDateTime date_exit;

    @Column(name = "km_exit", nullable = false)
    private int km_exit;

    @Column(name = "n_mov", insertable = false)
    private int n_mov;

    public ExitModel(){
    }

    public ExitModel(
            CarFrotaModel _fk_car_frota,
            UserModel _fk_user,
            CarRequestModel _fk_car_request,
            ObservationModel _fk_observation,
            int _km_exit
    ){
        this.fk_car_frota = _fk_car_frota;
        this.fk_user = _fk_user;
        this.fk_car_request = _fk_car_request;
        this.fk_observation = _fk_observation;
        this.km_exit = _km_exit;
    }

    public UUID getId_exit_record() {
        return id_exit_record;
    }

    public void setId_exit_record(UUID id_exit_record) {
        this.id_exit_record = id_exit_record;
    }

    public CarFrotaModel getFk_car_frota() {
        return fk_car_frota;
    }

    public void setFk_car_frota(CarFrotaModel fk_car_frota) {
        this.fk_car_frota = fk_car_frota;
    }

    public UserModel getFk_user() {
        return fk_user;
    }

    public void setFk_user(UserModel fk_user) {
        this.fk_user = fk_user;
    }

    public CarRequestModel getFk_car_request() {
        return fk_car_request;
    }

    public void setFk_car_request(CarRequestModel fk_car_request) {
        this.fk_car_request = fk_car_request;
    }

    public ObservationModel getFk_observation() {
        return fk_observation;
    }

    public void setFk_observation(ObservationModel fk_observation) {
        this.fk_observation = fk_observation;
    }

    public LocalDateTime getDate_exit() {
        return date_exit;
    }

    public void setDate_exit(LocalDateTime date_exit) {
        this.date_exit = date_exit;
    }

    public int getKm_exit() {
        return km_exit;
    }

    public void setKm_exit(int km_exit) {
        this.km_exit = km_exit;
    }

    public int getN_mov() {
        return n_mov;
    }

    public void setN_mov(int n_mov) {
        this.n_mov = n_mov;
    }

 /* id_exit_record | uuid                        |           | not null | gen_random_uuid()
 id_car_frota   | uuid                        |           | not null |
 fk_user        | uuid                        |           | not null |
 fk_car_request | uuid                        |           |          |
 date_exit      | timestamp without time zone |           |          | CURRENT_TIMESTAMP
 km_exit        | integer                     |           | not null |
 fk_observation | uuid                        |           |          |
Indexes:*/
}
