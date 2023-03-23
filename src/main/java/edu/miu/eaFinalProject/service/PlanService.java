package edu.miu.eaFinalProject.service;

import edu.miu.eaFinalProject.domain.Plan;
import edu.miu.eaFinalProject.dto.LocationDTO;
import edu.miu.eaFinalProject.dto.PlanDTO;


import java.util.List;

public interface PlanService {
    public PlanDTO createPlan(PlanDTO planDTO);

    public PlanDTO getPlanById(Long id);

    public List<PlanDTO> getAllPlan();

    public PlanDTO updatePlan(PlanDTO planDTO);

    public void deletePlan(Long id);

    public List<LocationDTO> findAllLocationByPlanId(Long id);
    public List<PlanDTO> getListAllCheckerPlans(long memberId);
}
