package edu.miu.eaFinalProject.repository;

import edu.miu.eaFinalProject.domain.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
    @Query("SELECT p FROM Plan p where p.id= :planId")
    public Plan findById(@Param("planId")long planId);

}
