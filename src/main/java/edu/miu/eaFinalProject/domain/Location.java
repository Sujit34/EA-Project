package edu.miu.eaFinalProject.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    @Id
    @GeneratedValue
    private long id;
    private String name;

    private String description;

    private int capacity;

    @ManyToOne(fetch = FetchType.EAGER)
    private LocationType type;
    @OneToMany
    @JoinColumn(name="location_id")
    private List<TimeSlot> timeSlots;

    @ManyToOne
    @JoinColumn(name="plan_id")
    private Plan plan;

    private boolean open;

}
