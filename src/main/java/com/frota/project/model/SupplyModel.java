package com.frota.project.model;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "supply")
public class SupplyModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id_supply;

    @OneToOne
    @JoinColumn(name = "fk_car_frota", nullable = false)
    private CarFrotaModel fk_car_frota;

    @OneToOne
    @JoinColumn(name = "fk_user", nullable = false)
    private UserModel fk_user;

    @Column(name = "date_supply", nullable = true, insertable = false, updatable = false)
    private LocalDateTime date_supply;

    @Column(name = "litros", precision = 10, scale = 2, nullable = false)
    private BigDecimal litros;

    @Column(name = "value_total", precision = 10, scale = 2, nullable = false)
    private BigDecimal value_total;

    @Column(name = "km_record", nullable = false)
    private int km_record;

    @Column(name = "n_mov", insertable = false, updatable = false)
    private int n_mov;

    public SupplyModel(){

    }

    public SupplyModel(
            CarFrotaModel fk_car_frota,
            UserModel fk_user,
            BigDecimal value_total,
            BigDecimal litros,
            int km_record,
            int n_mov
    ){
        this.fk_car_frota = fk_car_frota;
        this.fk_user = fk_user;
        this.value_total = value_total;
        this.litros = litros;
        this.km_record = km_record;
        this.n_mov = n_mov;
    }

    public UUID getId_supply() {
        return id_supply;
    }

    public void setId_supply(UUID id_supply) {
        this.id_supply = id_supply;
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

    public LocalDateTime getDate_supply() {
        return date_supply;
    }

    public void setDate_supply(LocalDateTime date_supply) {
        this.date_supply = date_supply;
    }

    public BigDecimal getLitros() {
        return litros;
    }

    public void setLitros(BigDecimal litros) {
        this.litros = litros;
    }

    public BigDecimal getValue_total() {
        return value_total;
    }

    public void setValue_total(BigDecimal value_total) {
        this.value_total = value_total;
    }

    public int getKm_record() {
        return km_record;
    }

    public void setKm_record(int km_record) {
        this.km_record = km_record;
    }

    public int getN_mov() {
        return n_mov;
    }

    public void setN_mov(int n_mov) {
        this.n_mov = n_mov;
    }

    /* id_supply    | uuid                        |           | not null | gen_random_uuid()
 fk_car_frota | uuid                        |           | not null |
 fk_user      | uuid                        |           | not null |
 date_supply  | timestamp without time zone |           |          | CURRENT_TIMESTAMP
 litros       | numeric(10,2)               |           | not null |
 value_total  | numeric(10,2)               |           | not null |
 km_record    | integer                     |           | not null | */
}
