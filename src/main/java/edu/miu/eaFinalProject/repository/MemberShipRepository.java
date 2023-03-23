package edu.miu.eaFinalProject.repository;

import edu.miu.eaFinalProject.domain.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.Collection;

@Repository
public interface MemberShipRepository extends JpaRepository<Membership,Long> {
    public List<Membership> findAllByMemberId(long memberId);
    @Query("select ms from Membership ms where ms.member.id = :memberId")
    public Collection<Membership> getAllByMember_Id(long memberId);

    public List<Membership> findMembershipByMemberId(long id);
}

