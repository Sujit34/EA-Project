package edu.miu.eaFinalProject.service;

import edu.miu.eaFinalProject.dto.MembershipDTO;
import edu.miu.eaFinalProject.dto.PlanDTO;

import java.util.Collection;
import java.util.List;

public interface MemberShipService {

    MembershipDTO saveMemberShip(MembershipDTO membershipDTO);

    List<MembershipDTO> getAllMemberShip();

    void deleteMemberShip(long id);

    MembershipDTO getMemberShipById(long id);

    MembershipDTO updateMemberShip(long id,MembershipDTO membershipDTO );

    public Collection<MembershipDTO> getMembershipsFromMember(long memberId);
}
