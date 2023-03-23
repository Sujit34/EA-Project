package edu.miu.eaFinalProject.repository;

import edu.miu.eaFinalProject.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    public Location getLocationById(long locationId);

    public List<Location> findLocationByPlanId(long planId);
}
