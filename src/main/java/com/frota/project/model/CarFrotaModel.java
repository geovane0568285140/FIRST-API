package com.frota.project.model;


import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "car_frota")
public class CarFrotaModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id_frota;

    @Column(name = "license_plate", nullable = false, length = 20, unique = true)
    private String licensePlate;

    @Column(name = "model", nullable = false, length = 100)
    private String model;

    @Column(name = "created_at", insertable = false, updatable = false, nullable = false)
    private LocalDateTime created_at;

    @Column(name = "active", nullable = true)
    private boolean active;

    @Column(name = "mark", length = 20)
    private String mark;

    @Column(name = "manufaturing_year", length = 4)
    private String manufaturing_year;

    @Column(name = "model_year", length = 4)
    private String model_year;

    @Column(name = "color", length = 20)
    private String color;

    @Column(name = "category", length = 40)
    private String category;

    @Column(name = "fuel_type", length = 20)
    private String fuel_type;

    @Column(name = "current_mileage", length = 20, nullable = false)
    private String current_mileage;

    @Column(name = "num_crlv", length = 20)
    private String num_crlv;

    @Column(name = "date_licensing")
    private LocalDate date_licensing;

    @Column(name = "date_maturity_IPVA")
    private LocalDate date_maturity_IPVA;

    @Column(name = "num_car")
    private int num_car;

    public CarFrotaModel() {
    }

    public CarFrotaModel(String _licensePlate, String _model, Boolean _active, String _mark, String _manufaturing_year, String _model_year, String _color, String _category, String _fuel_type, String _current_mileage, String _num_crlv, LocalDate _date_licensing, LocalDate _date_maturity_IPVA, int _num_car) {
        this.licensePlate = _licensePlate;
        this.model = _model;
        this.active = _active;
        this.mark = _mark;
        this.manufaturing_year = _manufaturing_year;
        this.model_year = _model_year;
        this.color = _color;
        this.category = _category;
        this.fuel_type = _fuel_type;
        this.current_mileage = _current_mileage;
        this.num_crlv = _num_crlv;
        this.date_licensing = _date_licensing;
        this.date_maturity_IPVA = _date_maturity_IPVA;
        this.num_car = _num_car;
    }

    public UUID getId_frota() {
        return id_frota;
    }

    public void setId_frota(UUID id_frota) {
        this.id_frota = id_frota;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String license_plate) {
        this.licensePlate = license_plate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getManufaturing_year() {
        return manufaturing_year;
    }

    public void setManufaturing_year(String manufaturing_year) {
        this.manufaturing_year = manufaturing_year;
    }

    public String getModel_year() {
        return model_year;
    }

    public void setModel_year(String model_year) {
        this.model_year = model_year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFuel_type() {
        return fuel_type;
    }

    public void setFuel_type(String fuel_type) {
        this.fuel_type = fuel_type;
    }

    public String getCurrent_mileage() {
        return current_mileage;
    }

    public void setCurrent_mileage(String current_mileage) {
        this.current_mileage = current_mileage;
    }

    public String getNum_crlv() {
        return num_crlv;
    }

    public void setNum_crlv(String num_crlv) {
        this.num_crlv = num_crlv;
    }

    public LocalDate getDate_licensing() {
        return date_licensing;
    }

    public void setDate_licensing(LocalDate date_licensing) {
        this.date_licensing = date_licensing;
    }

    public LocalDate getDate_maturity_IPVA() {
        return date_maturity_IPVA;
    }

    public void setDate_maturity_IPVA(LocalDate date_maturity_IPVA) {
        this.date_maturity_IPVA = date_maturity_IPVA;
    }

    public int getNum_car() {
        return num_car;
    }

    public void setNum_car(int num_car) {
        this.num_car = num_car;
    }



 /* id_frota      | uuid                        |           | not null | gen_random_uuid()
    license_plate | character varying(20)       |           | not null |
    model         | character varying(100)      |           | not null |
    active        | boolean                     |           |          | true
    created_at    | timestamp without time zone |           |          | CURRENT_TIMESTAMP*/
}
