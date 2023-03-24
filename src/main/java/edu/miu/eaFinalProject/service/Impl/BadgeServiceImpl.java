package edu.miu.eaFinalProject.service.Impl;

import edu.miu.eaFinalProject.domain.*;
import edu.miu.eaFinalProject.dto.BadgeDTO;
import edu.miu.eaFinalProject.dto.adapter.BadgeAdapter;
import edu.miu.eaFinalProject.repository.*;
import edu.miu.eaFinalProject.service.BadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BadgeServiceImpl implements BadgeService {

    @Autowired
    private BadgeRepository badgeRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private MemberShipRepository memberShipRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private PlanRepository planRepository;

    @Value("${meal.student}")
    private int studentMealCount;

    @Value("${meal.nonStudent}")
    private int nonStudentMealCount;

    @Override
    public BadgeDTO getBadgeById(Long badgeId) {
        Badge badge = badgeRepository.getById(badgeId);
        BadgeDTO badgeDTO = BadgeAdapter.getBadgeDTOFromBadge(badge);
        return badgeDTO;
    }

    @Override
    public void createBadge(BadgeDTO badgeDTO) {
        Badge badge = BadgeAdapter.getBadgeFromBadgeDTO(badgeDTO);
        badgeRepository.save(badge);
    }

    @Override
    public void updateBadge(long badgeId, BadgeDTO badgeDTO) {
        Optional<Badge> optionalBadge = badgeRepository.findById(badgeId);
        if (optionalBadge.isPresent()) {
            Badge badge = BadgeAdapter.getBadgeFromBadgeDTO(badgeDTO);

            badgeRepository.save(badge);
        }
    }

    @Override
    public BadgeDTO deleteBadgeById(Long badgeId) {
        Badge badge = badgeRepository.findById(badgeId).get();
        BadgeDTO badgeDTO = BadgeAdapter.getBadgeDTOFromBadge(badge);
        badgeRepository.deleteById(badgeId);
        return badgeDTO;
    }

    @Override
    public String scanBadge(String badgeId, long planId, long locationId) {
        Location location = locationRepository.getLocationById(locationId);
        if (location.isOpen() == false) return "Access Denied. Location is not open yet.";

        Badge badge = badgeRepository.findBadgeByBadgeId(badgeId);
        if(badge.isActive()==false) return "Access Denied. badge is not active.";

        Member member = memberRepository.findMemberByBadgeId(badgeId);
        List<Membership> memberships = memberShipRepository.findMembershipByMemberId(member.getId());
        boolean planExist = false;
        Membership membership =null;
        for (Membership m : memberships) {
            if (m.getPlan().getId() == planId) {
                planExist = true;
                membership = m;
            }
        }
        if (planExist == false)
            return "Access Denied. You are not in this plan.";

        Plan plan = planRepository.findById(planId);

        if (plan.getName().toLowerCase().equals("meal")) {
            //if only student -> 20 meal per week
            LocalDate today = LocalDate.now();
            if (member.getRoles().stream().filter(role -> !role.getRole().equalsIgnoreCase("Student")).count() == 0) {
                LocalDate startDate = LocalDate.now().with(DayOfWeek.MONDAY);
                LocalDate endDate = LocalDate.now().with(DayOfWeek.SUNDAY);

                int count = transactionRepository.getCountOfTransaction(membership.getId(), member.getId(), startDate, endDate);
                if (count >= studentMealCount) return "Access Denied. Over the limit";
            }
            //if only staff and faculty -> 30 meal per month
            else {
                LocalDate startDate = LocalDate.now().withDayOfMonth(1);
                LocalDate endDate = LocalDate.now().withDayOfMonth(30);

                int count = transactionRepository.getCountOfTransaction(membership.getId(), member.getId(), startDate, endDate);
                if (count >= nonStudentMealCount) return "Access Denied. Over the limit";
            }
        }
        Transaction transaction = new Transaction();
        transaction.setDateTime(LocalDate.now());
        transaction.setMember(member);
        transaction.setMembership(membership);
        transaction.setLocation(location);
        transactionRepository.save(transaction);

        return "Access granted.";
    }
}
