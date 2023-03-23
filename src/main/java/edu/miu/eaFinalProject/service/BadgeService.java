package edu.miu.eaFinalProject.service;

import edu.miu.eaFinalProject.dto.BadgeDTO;


public interface BadgeService {
    BadgeDTO getBadgeById(Long badgeId);
    void createBadge(BadgeDTO badge);
    void updateBadge(long badgeId, BadgeDTO badgeDTO);
    BadgeDTO deleteBadgeById(Long badgeId);

    public String scanBadge(String BadgeId,long planId, long locationId);
}
