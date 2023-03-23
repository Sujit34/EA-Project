package edu.miu.eaFinalProject.domain;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
public class Badge {
    @Id
    @GeneratedValue
    private long id;
    private String badgeId;
    private boolean active;
}
