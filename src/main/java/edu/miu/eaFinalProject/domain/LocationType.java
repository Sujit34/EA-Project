package edu.miu.eaFinalProject.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class LocationType {
    @Id
    @GeneratedValue
    private long id;
    private String type;
}
