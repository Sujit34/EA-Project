package edu.miu.eaFinalProject.Controller;


import edu.miu.eaFinalProject.dto.LocationDTO;
import edu.miu.eaFinalProject.dto.PlanDTO;
import edu.miu.eaFinalProject.exception.ApiRequestException;
import edu.miu.eaFinalProject.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.EmptyStackException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/plans")
public class PlanController {
    @Autowired
    PlanService planService;

    @PostMapping("")
    public ResponseEntity<?> createPlan(@RequestBody PlanDTO planDTO) {
        PlanDTO planDTOObj = null;
        try {
            planDTOObj = planService.createPlan(planDTO);
        } catch (Exception e) {
            throw new ApiRequestException("Error while creating plan.!!", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{planId}")
    public ResponseEntity<?> getPlanById(@PathVariable("planId") long planId) {
        PlanDTO planDTOObj = null;
        try {
            planDTOObj = planService.getPlanById(planId);
        } catch (NullPointerException nullPointerException) {
            throw new ApiRequestException("No plan found with this id.!!", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            throw new ApiRequestException("Error getting plan by id.!!", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<PlanDTO>(planDTOObj, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllPlans() {
        List<PlanDTO> planDTOs = null;
        try {
            planDTOs = planService.getAllPlan();
        } catch (Exception e) {
            throw new ApiRequestException("Error getting plans.!!", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<List<PlanDTO>>(planDTOs, HttpStatus.OK);
    }


    @PutMapping("")
    public ResponseEntity<?> updatePlan(@RequestBody PlanDTO planDTO) {
        try {
            planService.updatePlan(planDTO);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{planId}")
    public ResponseEntity<?> deletePlan(@PathVariable("planId") long planId) {
        try {
            planService.deletePlan(planId);
        } catch (Exception e) {
            throw new ApiRequestException("Error while deleting plan.!!", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{planId}/locations")
    public ResponseEntity<?> getAllLocationsByPlanId(@PathVariable("planId") long planId) {
        List<LocationDTO> locations;
        try {
            locations = planService.findAllLocationByPlanId(planId);
        } catch (Exception e) {
            throw new ApiRequestException("Error getting locations.!!", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }

    @GetMapping("/{memberId}/checkerPlans")
    public ResponseEntity<?> getListAllCheckerPlans(@PathVariable("memberId") long memberId) {
        List<PlanDTO> planDTOS = null;
        try {
            planDTOS = planService.getListAllCheckerPlans(memberId);
        } catch (EmptyStackException e) {
            throw new ApiRequestException("No plan found for this member!", HttpStatus.NOT_FOUND);
        } catch (NullPointerException e) {
            throw new ApiRequestException("Member with id " + memberId + " not found!", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<List<PlanDTO>>(planDTOS, HttpStatus.OK);
    }

}
