package edu.miu.eaFinalProject.dto.adapter;

import edu.miu.eaFinalProject.domain.Plan;
import edu.miu.eaFinalProject.dto.PlanDTO;
import java.util.List;

public class PlanAdapter {
    public static PlanDTO getPlanDTOFromPlan(Plan plan) {
        if(plan == null) return null;
        PlanDTO planDTO = new PlanDTO();

        planDTO.setId(plan.getId());
        planDTO.setName(plan.getName());
        planDTO.setDescription(plan.getDescription());
        planDTO.setRoles(RoleAdapter.getRoleDTOListFromRoleList(plan.getRoles()));
       // planDTO.setLocations(LocationAdapter.getLocationDTOListFromLocationList(plan.getLocations()));

        return planDTO;
    }

    public static Plan getPlanFromPlanDTO(PlanDTO planDTO) {
        if(planDTO == null) return null;
        Plan plan = new Plan();

        plan.setId(planDTO.getId());
        plan.setName(planDTO.getName());
        plan.setDescription(planDTO.getDescription());
        plan.setRoles(RoleAdapter.getRoleListFromRoleDTOList(planDTO.getRoles()));
      //  plan.setLocations((LocationAdapter.getLocationListFromLocationDTOList(planDTO.getLocations())));

        return plan;
    }

    public static List<PlanDTO> getPlanDTOListFromPlanList(List<Plan> plans) {
        if(plans==null) return null;
        return plans.stream().map(plan -> getPlanDTOFromPlan(plan)).toList();
    }

    public static List<Plan> getPlanListFromPlanDTOList(List<PlanDTO> planDTOS) {
        if(planDTOS==null) return null;
        return planDTOS.stream().map(planDTO -> getPlanFromPlanDTO(planDTO)).toList();
    }
}
