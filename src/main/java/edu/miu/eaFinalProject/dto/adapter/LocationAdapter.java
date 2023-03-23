package edu.miu.eaFinalProject.dto.adapter;

import edu.miu.eaFinalProject.domain.Location;
import edu.miu.eaFinalProject.dto.LocationDTO;
import java.util.List;

public class LocationAdapter {
    public static LocationDTO getLocationDTOFromLocation(Location location) {
        if(location == null){
            return null;
        }
        LocationDTO locationDTO = new LocationDTO();

        locationDTO.setId(location.getId());
        locationDTO.setName(location.getName());
        locationDTO.setDescription(location.getDescription());
        locationDTO.setCapacity(location.getCapacity());
        locationDTO.setType(location.getType());
        locationDTO.setTimeSlots(TimeSlotAdapter.getTimeSlotDTOListFromTimeSlotList(location.getTimeSlots()));
        locationDTO.setPlan(PlanAdapter.getPlanDTOFromPlan(location.getPlan()));

        return locationDTO;
    }

    public static Location getLocationFromLocationDTO(LocationDTO locationDTO) {
        if(locationDTO == null) return null;
        Location location = new Location();

        location.setId(locationDTO.getId());
        location.setName(locationDTO.getName());
        location.setDescription(locationDTO.getDescription());
        location.setCapacity(locationDTO.getCapacity());
        location.setType(locationDTO.getType());
        location.setTimeSlots(TimeSlotAdapter.getTimeSlotListFromTimeSlotDTO(locationDTO.getTimeSlots()));
        location.setPlan(PlanAdapter.getPlanFromPlanDTO(locationDTO.getPlan()));

        return location;
    }

    public static List<LocationDTO> getLocationDTOListFromLocationList(List<Location> locations) {
        if(locations==null) return null;
        return locations.stream().map(location -> getLocationDTOFromLocation(location)).toList();
    }

    public static List<Location> getLocationListFromLocationDTOList(List<LocationDTO> locationDTOS) {
        if(locationDTOS==null) return null;
        return locationDTOS.stream().map(locationDTO -> getLocationFromLocationDTO(locationDTO)).toList();
    }
}
