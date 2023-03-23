package edu.miu.eaFinalProject.Controller;

import edu.miu.eaFinalProject.dto.MembershipDTO;
import edu.miu.eaFinalProject.exception.ApiRequestException;
import edu.miu.eaFinalProject.service.MemberShipService;
import edu.miu.eaFinalProject.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class MemberShipController {

    @Autowired
    private MemberShipService memberShipService;

    @PostMapping("/memberships")
    public ResponseEntity<?> addMemberShip(@RequestBody MembershipDTO membershipDTO) {
        memberShipService.saveMemberShip(membershipDTO);
        return new ResponseEntity<>(new ResponseMessage("MemberShip successfully created"), HttpStatus.OK);
    }

    @GetMapping("/memberships")
    public ResponseEntity<?> getAllMemberShip() {
        List<MembershipDTO> membershipDTOList = memberShipService.getAllMemberShip();
        return new ResponseEntity<>(membershipDTOList, HttpStatus.OK);
    }

    @GetMapping("/memberships/{id}")
    public ResponseEntity<?> getMemberShipById(@PathVariable(name = "id") long id) {
        MembershipDTO membershipDTO = memberShipService.getMemberShipById(id);
        return new ResponseEntity<>(membershipDTO, HttpStatus.OK);
    }

    @PutMapping("/memberships/{id}")
    public ResponseEntity<?> updateMemberShipById(@RequestBody MembershipDTO membershipDTO, @PathVariable long id) {
        MembershipDTO updateMemberShip;
        try {
            updateMemberShip = memberShipService.updateMemberShip(id, membershipDTO);

        } catch (Exception e) {
            throw new ApiRequestException("MemberShip cannot updated ", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(updateMemberShip, HttpStatus.OK);
    }

    @DeleteMapping("/memberships/{id}")
    public ResponseEntity<?> deleteMemberShipById(@PathVariable long id) {

        try {
            memberShipService.deleteMemberShip(id);
        } catch (Exception e) {
            throw new ApiRequestException("MemberShip cannot updated ", HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(new ResponseMessage("Delete Successfully"), HttpStatus.OK);
    }
}