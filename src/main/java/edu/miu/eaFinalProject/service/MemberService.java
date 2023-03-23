package edu.miu.eaFinalProject.service;

import edu.miu.eaFinalProject.domain.Member;
import edu.miu.eaFinalProject.dto.BadgeDTO;
import edu.miu.eaFinalProject.dto.MemberDTO;
import edu.miu.eaFinalProject.dto.PlanDTO;

import java.util.Collection;
import java.util.List;

public interface MemberService {
    public Collection<MemberDTO> getAllMembers() throws Exception;
    public MemberDTO getMember(long memberId);
    public MemberDTO updateMember(long memberId, MemberDTO memberDTO) throws Exception;
    public void deleteMember(long memeberId);
    public MemberDTO addMember(Member memberDTO);
    public BadgeDTO getActiveBadgeFromMember(long memberId);
    public List<PlanDTO> getAllPlansByMemberId(long memberId);

}
