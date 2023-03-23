package edu.miu.eaFinalProject.repository;

import edu.miu.eaFinalProject.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    public Member findByEmail(String email);
    public Member findById(long memberId);

    @Query("Select m from Member m join fetch m.badges b where b.badgeId = :badgeId")
    public Member findMemberByBadgeId(String badgeId);



}
