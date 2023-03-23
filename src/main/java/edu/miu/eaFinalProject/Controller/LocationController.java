package edu.miu.eaFinalProject.Controller;

import edu.miu.eaFinalProject.domain.Location;
import edu.miu.eaFinalProject.dto.LocationDTO;
import edu.miu.eaFinalProject.exception.ApiRequestException;
import edu.miu.eaFinalProject.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/locations")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @GetMapping("")
    public ResponseEntity<?> getAllLocations() {
        Collection<Location> locations = null;
        try {
            locations = locationService.getAllLocations();
        } catch (DataAccessResourceFailureException e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<Collection<Location>>(locations, HttpStatus.OK);
    }

    @GetMapping("/{locationId}")
    public ResponseEntity<?> getLocation(@PathVariable long locationId) {
        LocationDTO locationDTO = null;
        try {
            locationDTO = locationService.getLocation(locationId);
        } catch (NullPointerException e) {
            throw new ApiRequestException("Location with id " + locationId + " not found!", HttpStatus.NOT_FOUND);
        } catch (DataAccessResourceFailureException e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<LocationDTO>(locationDTO, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> addLocation(@RequestBody LocationDTO locationDTO) {
        try {
            locationService.addLocation(locationDTO);
        } catch (DataAccessResourceFailureException e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception e) {
            throw new ApiRequestException("Error in adding location.!!", HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{locationId}")
    public ResponseEntity<?> deleteLocation(@PathVariable long locationId) {
        String message;
        try {
            message = locationService.deleteLocation(locationId);
        } catch (NullPointerException e) {
            throw new ApiRequestException("Location with id " + locationId + " not found!", HttpStatus.NOT_FOUND);
        } catch (DataAccessResourceFailureException e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<?> updateLocation(@RequestBody LocationDTO locationDTO) {

        try {
            locationService.updateLocation(locationDTO);
        } catch (NullPointerException e) {
            throw new ApiRequestException("Location with id " + locationDTO.getId() + " not found!", HttpStatus.NOT_FOUND);
        } catch (DataAccessResourceFailureException e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception e) {
            throw new ApiRequestException("Error updating location.!!", HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<LocationDTO>(HttpStatus.OK);
    }

    @PatchMapping("/{locationId}/openLocation")
    public ResponseEntity<?> openLocation(@PathVariable long locationId) {
        try {
            locationService.openLocations(locationId);
        } catch (NullPointerException e) {
            throw new ApiRequestException("Location with id " + locationId + " not found!", HttpStatus.NOT_FOUND);
        } catch (DataAccessResourceFailureException e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception e) {
            throw new ApiRequestException("Error opening location.!!", HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{locationId}/closeLocation")
    public ResponseEntity<?> closeLocation(@PathVariable long locationId) {
        try {
            locationService.closeLocations(locationId);
        } catch (NullPointerException e) {
            throw new ApiRequestException("Location with id " + locationId + " not found!", HttpStatus.NOT_FOUND);
        } catch (DataAccessResourceFailureException e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception e) {
            throw new ApiRequestException("Error closing location.!!", HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
