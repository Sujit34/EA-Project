package edu.miu.eaFinalProject.dto.adapter;

import edu.miu.eaFinalProject.domain.Badge;
import edu.miu.eaFinalProject.dto.BadgeDTO;
import java.util.List;

public class BadgeAdapter {
    public static BadgeDTO getBadgeDTOFromBadge(Badge badge){
        if(badge == null) return null;
        BadgeDTO badgeDTO = new BadgeDTO();

        badgeDTO.setId(badge.getId());
        badgeDTO.setBadgeId(badge.getBadgeId());
        badgeDTO.setActive(badge.isActive());

        return badgeDTO;
    }

    public static Badge getBadgeFromBadgeDTO(BadgeDTO badgeDTO){
        if(badgeDTO == null) return null;
        Badge badge = new Badge();

        badge.setId(badgeDTO.getId());
        badge.setBadgeId(badgeDTO.getBadgeId());
        badge.setActive(badgeDTO.isActive());

        return badge;
    }

    public static List<BadgeDTO> getBadgeDTOListFromBadgeList (List<Badge> badges){
        if(badges == null) return null;
        return badges.stream().map(badge -> getBadgeDTOFromBadge(badge)).toList();
    }

    public static List<Badge> getBadgeListFromBadgeDTOList (List<BadgeDTO> badgeDTOs){
        if(badgeDTOs == null) return null;
        return badgeDTOs.stream().map(badgeDTO -> getBadgeFromBadgeDTO(badgeDTO)).toList();
    }
}
