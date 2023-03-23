package edu.miu.eaFinalProject.Controller;

import edu.miu.eaFinalProject.domain.Member;
import edu.miu.eaFinalProject.dto.BadgeDTO;
import edu.miu.eaFinalProject.dto.MemberDTO;
import edu.miu.eaFinalProject.dto.MembershipDTO;
import edu.miu.eaFinalProject.dto.PlanDTO;
import edu.miu.eaFinalProject.dto.TransactionDTO;
import edu.miu.eaFinalProject.exception.ApiRequestException;
import edu.miu.eaFinalProject.repository.MemberRepository;
import edu.miu.eaFinalProject.service.MemberService;
import edu.miu.eaFinalProject.service.MemberShipService;
import edu.miu.eaFinalProject.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EmptyStackException;
import java.util.List;

@RestController
@RequestMapping("members")
public class MemberController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private MemberShipService memberShipService;

    @GetMapping
    public ResponseEntity<?> getAllMembers() {
        Collection<MemberDTO> members;
        try {
            members = memberService.getAllMembers();
        } catch(DataAccessResourceFailureException e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        } catch(Exception e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<?> getMember(@PathVariable long memberId) {
        MemberDTO member;
        try {
            member = memberService.getMember(memberId);
        } catch (NullPointerException e) {
            throw new ApiRequestException("Member with id " + memberId + " not found!", HttpStatus.NOT_FOUND);
        } catch(DataAccessResourceFailureException e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        } catch(Exception e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<?> deleteMember(@PathVariable long memberId) {
        try {
            memberService.deleteMember(memberId);
        }  catch (NullPointerException e) {
            throw new ApiRequestException("Member with id " + memberId + " not found!", HttpStatus.NOT_FOUND);
        } catch(DataAccessResourceFailureException e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        } catch(Exception e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>("Member deleted Successfully", HttpStatus.OK);
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<?> updateMember(@PathVariable long memberId, @RequestBody MemberDTO member) {
        MemberDTO result;
        try {
            result = memberService.updateMember(memberId, member);
        } catch (NullPointerException e) {
            throw new ApiRequestException("Member with id " + memberId + " not found!", HttpStatus.NOT_FOUND);
        } catch(DataAccessResourceFailureException e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        } catch(Exception e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addMember(@RequestBody Member memberDTO) {
        MemberDTO result;
        try {
            result = memberService.addMember(memberDTO);
        } catch(DataAccessResourceFailureException e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        } catch(Exception e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{memberId}/transactions")
    public ResponseEntity<?> getTransactionsFromMember(@PathVariable long memberId) {
        Collection<TransactionDTO> result;
        try {
            result = transactionService.getTransactionsByMember(memberId);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{memberId}/badges")
    public ResponseEntity<?> getBadgesFromMember(@PathVariable long memberId) {
        Collection<BadgeDTO> result;
        try {
            MemberDTO member = memberService.getMember(memberId);
            result = member.getBadges();
        } catch (NullPointerException e) {
            throw new ApiRequestException("Member with id " + memberId + " not found!", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<Collection<BadgeDTO>>(result, HttpStatus.OK);
    }

    @GetMapping("/{memberId}/active-badge")
    public ResponseEntity<?> getActiveBadgeFromMember(@PathVariable long memberId) {
        BadgeDTO result = null;
        try {
            result = memberService.getActiveBadgeFromMember(memberId);
        } catch(EmptyStackException e) {
            throw new ApiRequestException("No active badge found for this member!", HttpStatus.NOT_FOUND);
        } catch (NullPointerException e) {
            throw new ApiRequestException("Member with id " + memberId + " not found!", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<BadgeDTO>(result, HttpStatus.OK);
    }

    @GetMapping("/{memberId}/memberships")
    public ResponseEntity<?> getAllMembershipsFromMember(@PathVariable long memberId) {
        Collection<MembershipDTO> result = new ArrayList<>();
        try {
            result = memberShipService.getMembershipsFromMember(memberId);
        } catch (NullPointerException e) {
            throw new ApiRequestException("Member with id " + memberId + " not found!", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
        return  new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{memberId}/plans")
    public ResponseEntity<?> getAllPlansByMemberId(@PathVariable long memberId){
        List<PlanDTO> planDTOs = null;
        try{
            planDTOs = memberService.getAllPlansByMemberId(memberId);
        }
        catch(EmptyStackException e) {
            throw new ApiRequestException("No plan found for this member!", HttpStatus.NOT_FOUND);
        } catch (NullPointerException e) {
            throw new ApiRequestException("Data not found!", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<List<PlanDTO>>(planDTOs, HttpStatus.OK);
    }


}
