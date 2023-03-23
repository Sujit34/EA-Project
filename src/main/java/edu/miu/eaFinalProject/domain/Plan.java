package edu.miu.eaFinalProject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@ToString
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
public class Plan {
    @Id
    @GeneratedValue
    private long id;

    private String name;

    private String description;

    @ManyToMany
    @JoinTable(name="plan_role")
    private List<Role> roles;


}
