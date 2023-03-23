package edu.miu.eaFinalProject.dto.adapter;

import edu.miu.eaFinalProject.domain.Membership;
import edu.miu.eaFinalProject.dto.MembershipDTO;
import java.util.List;

public class MembershipAdapter {
    public static MembershipDTO getMembershipDTOFromMembership(Membership membership){
        if(membership == null)
            return null;
        MembershipDTO membershipDTO = new MembershipDTO();

        membershipDTO.setId(membership.getId());
        membershipDTO.setStartDate(membership.getStartDate());
        membershipDTO.setEndDate(membership.getEndDate());
        membershipDTO.setType(membership.getType());
        membershipDTO.setMember(MemberAdapter.getMemberDTOFromMember(membership.getMember()));
        membershipDTO.setPlan(PlanAdapter.getPlanDTOFromPlan(membership.getPlan()));
        System.out.println("gettingmes " + MemberAdapter.getMemberDTOFromMember(membership.getMember()));
        System.out.println("memebrshipdar " +membership.getMember());
        return membershipDTO;
    }

    public static Membership getMembershipFromMembershipDTO(MembershipDTO membershipDTO){
        if(membershipDTO == null) return null;
        Membership membership = new Membership();

        membership.setId(membershipDTO.getId());
        membership.setStartDate(membershipDTO.getStartDate());
        membership.setEndDate(membershipDTO.getEndDate());
        membership.setType(membershipDTO.getType());
        membership.setMember(MemberAdapter.getMemberFromMemberDTO(membershipDTO.getMember()));
        return membership;
    }

    public static List<MembershipDTO> getMembershipDTOListFromMembershipList (List<Membership> memberships){
        if(memberships == null) return null;
        return memberships.stream().map(membership -> getMembershipDTOFromMembership(membership)).toList();
    }

    public static List<Membership> getMembershipListFromMembershipDTOList (List<MembershipDTO> membershipDTOs){
        if(membershipDTOs == null) return null;
        return membershipDTOs.stream().map(membershipDTO -> getMembershipFromMembershipDTO(membershipDTO)).toList();
    }
}
