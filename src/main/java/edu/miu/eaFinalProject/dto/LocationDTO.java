package edu.miu.eaFinalProject.dto;

import edu.miu.eaFinalProject.domain.LocationType;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class LocationDTO {
    private long id;
    private String name;

    private String description;

    private int capacity;

    private LocationType type;
    private List<TimeSlotDTO> timeSlots = new ArrayList<TimeSlotDTO>();
    private PlanDTO plan;

}
