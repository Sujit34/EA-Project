package edu.miu.eaFinalProject.service;

import edu.miu.eaFinalProject.domain.Location;
import edu.miu.eaFinalProject.dto.LocationDTO;

import java.util.Collection;

public interface LocationService {
    public Collection<Location> getAllLocations();
    public LocationDTO getLocation(long locationId);
    public LocationDTO addLocation(LocationDTO locationDTO);
    public String deleteLocation(long locationId) throws Exception;
    public LocationDTO updateLocation(LocationDTO locationDTO);
    public void openLocations(long id);
    public void closeLocations(long id);
}
