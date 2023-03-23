package edu.miu.eaFinalProject.service.Impl;

import edu.miu.eaFinalProject.domain.*;
import edu.miu.eaFinalProject.dto.*;
import edu.miu.eaFinalProject.dto.adapter.MemberAdapter;
import edu.miu.eaFinalProject.dto.adapter.MembershipAdapter;
import edu.miu.eaFinalProject.dto.adapter.RoleAdapter;
import edu.miu.eaFinalProject.repository.*;
import edu.miu.eaFinalProject.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EmptyStackException;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BadgeRepository badgeRepository;

    @Autowired
    private MemberShipRepository memberShipRepository;

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public Collection<MemberDTO> getAllMembers() throws Exception {
        Collection<MemberDTO> result = new ArrayList<>();
        Collection<Member> members = memberRepository.findAll();
        for(Member member: members) {
            result.add(MemberAdapter.getMemberDTOFromMember(member));
        }
        return result;
    }

    @Override
    public MemberDTO getMember(long memberId) {
        Member member = memberRepository.findById(memberId);
        return MemberAdapter.getMemberDTOFromMember(member);
    }

    @Override
    public MemberDTO updateMember(long memberId, MemberDTO memberDTO) throws Exception {
        Member member = null;
        try{
            member = memberRepository.findById(memberId);
            List<Role> roles = new ArrayList<>();
            member.setRoles(RoleAdapter.getRoleListFromRoleDTOList(memberDTO.getRoles()));
            member.setFirstName(memberDTO.getFirstName());
            member.setLastName(memberDTO.getLastName());
            member.setEmail(memberDTO.getLastName());
            member = memberRepository.save(member);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return MemberAdapter.getMemberDTOFromMember(member);
    }

    @Override
    public void deleteMember(long memeberId) {
        memberRepository.deleteById(memeberId);
    }

    @Override
    public MemberDTO addMember(Member member) {
        member.setPassword(encoder.encode(member.getPassword()));
        member = memberRepository.save(member);
        return MemberAdapter.getMemberDTOFromMember(member);
    }

    @Override
    public BadgeDTO getActiveBadgeFromMember(long memberId) {
        MemberDTO member = getMember(memberId);
        BadgeDTO result = null;

        for(BadgeDTO b: member.getBadges()) {
            if(b.isActive()) {
                result = b;
            }
        }
        if(result == null) {
            throw new EmptyStackException();
        }
        return result;
    }

    @Override
    public List<PlanDTO> getAllPlansByMemberId(long memberId){
        List<Membership> memberships = memberShipRepository.findAllByMemberId(memberId);
        List<MembershipDTO> membershipDTOs = MembershipAdapter.getMembershipDTOListFromMembershipList(memberships);
        List<PlanDTO> planDTOs = new ArrayList<>();
        for(MembershipDTO m: membershipDTOs){
                planDTOs.add(m.getPlan());
        }
        return planDTOs;
    }


}
