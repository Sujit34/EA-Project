package edu.miu.eaFinalProject.dto.adapter;

import edu.miu.eaFinalProject.domain.Member;
import edu.miu.eaFinalProject.dto.MemberDTO;

import java.util.List;

public class MemberAdapter {
    public static MemberDTO getMemberDTOFromMember(Member member){
        if(member == null)
            return null;
        MemberDTO memberDTO = new MemberDTO();

        memberDTO.setId(member.getId());
        memberDTO.setFirstName(member.getFirstName());
        memberDTO.setLastName(member.getLastName());
        memberDTO.setEmail(member.getEmail());
        memberDTO.setBadges(BadgeAdapter.getBadgeDTOListFromBadgeList(member.getBadges()));
        memberDTO.setRoles(RoleAdapter.getRoleDTOListFromRoleList(member.getRoles()));
        return memberDTO;
    }

    public static Member getMemberFromMemberDTO(MemberDTO memberDTO){
        if(memberDTO == null) return null;
        Member member = new Member();

        member.setId(memberDTO.getId());
        member.setFirstName(memberDTO.getFirstName());
        member.setLastName(memberDTO.getLastName());
        member.setEmail(memberDTO.getEmail());
        member.setBadges(BadgeAdapter.getBadgeListFromBadgeDTOList(memberDTO.getBadges()));
        member.setRoles(RoleAdapter.getRoleListFromRoleDTOList(memberDTO.getRoles()));

        return member;
    }

    public static List<MemberDTO> getMemberDTOListFromMemberList (List<Member> members){
        if(members == null) return null;
        return members.stream().map(member -> getMemberDTOFromMember(member)).toList();
    }

    public static List<Member> getMemberListFromMemberDTOList (List<MemberDTO> memberDTOs){
        if(memberDTOs == null) return null;
        return memberDTOs.stream().map(memberDTO -> getMemberFromMemberDTO(memberDTO)).toList();
    }
}
