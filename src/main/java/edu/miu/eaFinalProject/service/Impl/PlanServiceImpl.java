package edu.miu.eaFinalProject.service.Impl;


import edu.miu.eaFinalProject.domain.Location;
import edu.miu.eaFinalProject.domain.Membership;
import edu.miu.eaFinalProject.domain.Plan;
import edu.miu.eaFinalProject.domain.Role;
import edu.miu.eaFinalProject.dto.LocationDTO;
import edu.miu.eaFinalProject.dto.MembershipDTO;
import edu.miu.eaFinalProject.dto.PlanDTO;
import edu.miu.eaFinalProject.dto.adapter.LocationAdapter;
import edu.miu.eaFinalProject.dto.adapter.MembershipAdapter;
import edu.miu.eaFinalProject.dto.adapter.PlanAdapter;
import edu.miu.eaFinalProject.dto.adapter.RoleAdapter;
import edu.miu.eaFinalProject.repository.LocationRepository;
import edu.miu.eaFinalProject.repository.MemberShipRepository;
import edu.miu.eaFinalProject.repository.PlanRepository;
import edu.miu.eaFinalProject.repository.RoleRepository;
import edu.miu.eaFinalProject.service.MemberService;
import edu.miu.eaFinalProject.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PlanServiceImpl implements PlanService {
    @Autowired
    PlanRepository planRepository;
    @Autowired
    private MemberShipRepository memberShipRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public PlanDTO createPlan(PlanDTO planDTO) {
        Plan plan = PlanAdapter.getPlanFromPlanDTO(planDTO);
        planRepository.save(plan);
        return PlanAdapter.getPlanDTOFromPlan(plan);
    }

    @Override
    public PlanDTO getPlanById(Long id) {
        return PlanAdapter.getPlanDTOFromPlan(planRepository.findById(id).orElse(null));
    }

    @Override
    public List<PlanDTO> getAllPlan() {
        return PlanAdapter.getPlanDTOListFromPlanList(planRepository.findAll());
    }

    @Override
    public PlanDTO updatePlan(PlanDTO planDTO) {
        Plan plan = planRepository.findById(planDTO.getId());

        plan.setName(planDTO.getName());
        plan.setDescription(planDTO.getDescription());
        List<Role> roles = RoleAdapter.getRoleListFromRoleDTOList(planDTO.getRoles());
        plan.setRoles(roles);
        planRepository.save(plan);


        return PlanAdapter.getPlanDTOFromPlan(plan);
    }

    @Override
    public void deletePlan(Long id) {
        List<Location> locationList = locationRepository.findLocationByPlanId(id);
        locationRepository.deleteAll(locationList);
        planRepository.deleteById(id);
    }

    @Override
    public List<LocationDTO> findAllLocationByPlanId(Long id) {
        List<Location> locationList = locationRepository.findLocationByPlanId(id);
        if (locationList == null) return null;
        return LocationAdapter.getLocationDTOListFromLocationList(locationList);
    }

    @Override
    public List<PlanDTO> getListAllCheckerPlans(long memberId){
        List<Membership> memberships = memberShipRepository.findAllByMemberId(memberId);
        List<MembershipDTO> membershipDTOs = MembershipAdapter.getMembershipDTOListFromMembershipList(memberships);
        List<PlanDTO> planDTOs = new ArrayList<>();
        for(MembershipDTO m: membershipDTOs){
            if(m.getType().equals("CHECKER")){
               planDTOs.add(m.getPlan());
            }
        }
        return planDTOs;
    }
}
