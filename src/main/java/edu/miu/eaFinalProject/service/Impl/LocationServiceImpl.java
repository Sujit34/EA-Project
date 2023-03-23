package edu.miu.eaFinalProject.service.Impl;

import edu.miu.eaFinalProject.domain.Location;
import edu.miu.eaFinalProject.dto.LocationDTO;
import edu.miu.eaFinalProject.dto.adapter.LocationAdapter;
import edu.miu.eaFinalProject.dto.adapter.PlanAdapter;
import edu.miu.eaFinalProject.repository.LocationRepository;
import edu.miu.eaFinalProject.service.LocationService;
import edu.miu.eaFinalProject.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private PlanService planService;
    @Override
    public Collection<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    @Override
    public LocationDTO getLocation(long locationId) {
        Location location = locationRepository.getLocationById(locationId);
        LocationDTO locationDTO = LocationAdapter.getLocationDTOFromLocation(location);
        return locationDTO;
    }

    @Override
    public LocationDTO addLocation(LocationDTO locationDTO) {
        Location ll =  locationRepository.save(LocationAdapter.getLocationFromLocationDTO(locationDTO));
        return locationDTO;
    }

    @Override
    public String deleteLocation(long locationId) throws Exception {
        try {
            locationRepository.deleteById(locationId);
            return "Location with id " + locationId + " deleted successfully!";
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public LocationDTO updateLocation(LocationDTO locationDTO) {
        Location location = locationRepository.getLocationById(locationDTO.getId());
        location.setName(locationDTO.getName());
        location.setDescription(locationDTO.getDescription());
        location.setCapacity(locationDTO.getCapacity());
        location.setType(locationDTO.getType());
        location.setPlan(PlanAdapter.getPlanFromPlanDTO(locationDTO.getPlan()));
        locationRepository.save(location);
        return LocationAdapter.getLocationDTOFromLocation(location);
    }

    @Override
    public void openLocations(long locationId){
        Location location = locationRepository.findById(locationId).orElse(null);
        location.setOpen(true);
        locationRepository.save(location);
    }

    @Override
    public void closeLocations(long locationId){
        Location location = locationRepository.findById(locationId).orElse(null);
        location.setOpen(false);
        locationRepository.save(location);
    }

}
