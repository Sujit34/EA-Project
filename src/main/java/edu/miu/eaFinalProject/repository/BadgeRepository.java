package edu.miu.eaFinalProject.repository;

import edu.miu.eaFinalProject.domain.Badge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BadgeRepository extends JpaRepository<Badge,Long> {
    public Badge findBadgeByBadgeId(String badgeId);
}
