package edu.miu.eaFinalProject.service.Impl;

import edu.miu.eaFinalProject.domain.Membership;
import edu.miu.eaFinalProject.domain.Transaction;
import edu.miu.eaFinalProject.dto.MembershipDTO;
import edu.miu.eaFinalProject.dto.PlanDTO;
import edu.miu.eaFinalProject.dto.adapter.MemberAdapter;
import edu.miu.eaFinalProject.dto.adapter.MembershipAdapter;
import edu.miu.eaFinalProject.dto.adapter.PlanAdapter;
import edu.miu.eaFinalProject.exception.ApiRequestException;
import edu.miu.eaFinalProject.repository.MemberShipRepository;
import edu.miu.eaFinalProject.repository.TransactionRepository;
import edu.miu.eaFinalProject.service.MemberShipService;
import edu.miu.eaFinalProject.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class MemberShipImpl implements MemberShipService {

    @Autowired
    private MemberShipRepository memberShipRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public MembershipDTO saveMemberShip(MembershipDTO membershipDTO) {

        Membership membership = new Membership();
        membership.setStartDate(membershipDTO.getStartDate());
        membership.setEndDate(membershipDTO.getEndDate());
        membership.setType(membershipDTO.getType());
        membership.setPlan(PlanAdapter.getPlanFromPlanDTO(membershipDTO.getPlan()));
        membership.setMember(MemberAdapter.getMemberFromMemberDTO(membershipDTO.getMember()));
        memberShipRepository.save(membership);
        return MembershipAdapter.getMembershipDTOFromMembership(membership);
    }

    @Override
    public List<MembershipDTO> getAllMemberShip() {

        List<Membership> membershipDTOList = memberShipRepository.findAll();
        System.out.println("membershipDTOList = " + membershipDTOList);
        if (membershipDTOList.isEmpty()){
            new ResponseMessage("Member Ship is empty ");
        }
        return MembershipAdapter.getMembershipDTOListFromMembershipList(membershipDTOList);
    }

    @Override
    public void deleteMemberShip(long id) {
        Optional<Membership> membershipOptional = memberShipRepository.findById(id);
        if (membershipOptional.isPresent()) {
            List<Transaction> transactions = transactionRepository.findAllByMember_Id(id);
            transactionRepository.deleteAll(transactions);
            Membership membership =  membershipOptional.get();
            memberShipRepository.delete(membership);
        }else {
            throw new ApiRequestException("MemberShip with id = " + id + " cannot deleted", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public MembershipDTO getMemberShipById(long id) {
        Membership membership;
        Optional<Membership> membershipOptional = memberShipRepository.findById(id);
        if (membershipOptional.isPresent()) {
            membership = membershipOptional.get();
        }else {
            throw new ApiRequestException("MemberShip with id = " + id + " cannot found", HttpStatus.NOT_FOUND);
        }

        return MembershipAdapter.getMembershipDTOFromMembership(membership);
    }

    @Override
    public MembershipDTO updateMemberShip(long id,MembershipDTO membershipDTO) {
        Membership membership;
        Optional<Membership> membershipOptional = memberShipRepository.findById(id);
        if (membershipOptional.isPresent()) {
            membership = membershipOptional.get();
            membership.setType(membershipDTO.getType());
            membership.setStartDate(membershipDTO.getStartDate());
            membership.setEndDate(membershipDTO.getEndDate());
            memberShipRepository.save(membership);
        }else {
            throw new ApiRequestException("MemberShip with id=  " + id + " cannot updated", HttpStatus.NOT_FOUND);
        }

        return MembershipAdapter.getMembershipDTOFromMembership(membership);
    }



    @Override
    public Collection<MembershipDTO> getMembershipsFromMember(long memberId) {
        Collection<Membership> memberships = memberShipRepository.getAllByMember_Id(memberId);
        Collection<MembershipDTO> result = MembershipAdapter.getMembershipDTOListFromMembershipList(new ArrayList<>(memberships));
        return result;
    }
}