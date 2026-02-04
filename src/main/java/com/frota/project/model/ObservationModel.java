package com.frota.project.model;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.security.PublicKey;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "observation")
public class ObservationModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id_observation;

    @Column(name = "text_observation", nullable = false)
    private String text_observation;

    @Column(name = "created_at", nullable = true, insertable = false, updatable = false)
    private LocalDateTime created_at;

    public ObservationModel(){}

    public ObservationModel(String _text_observation){
        this.text_observation = _text_observation;
    }

    public UUID getId_observation() {
        return id_observation;
    }

    public void setId_observation(UUID id_observation) {
        this.id_observation = id_observation;
    }

    public String getText_observation() {
        return text_observation;
    }

    public void setText_observation(String text_observation) {
        this.text_observation = text_observation;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    /* id_observation   | uuid                        |           | not null | gen_random_uuid()
 text_observation | text                        |           | not null |
 create_in        | timestamp without time zone |           |          | CURRENT_TIMESTAMP*/
}
