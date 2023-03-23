package edu.miu.eaFinalProject.repository;

import edu.miu.eaFinalProject.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    public List<Transaction> findAllByMember_Id(long memberId);

    @Query("select count(t) from Transaction t where t.membership.id = :memberShipId and t.member.id = :memberId and t.dateTime between :startDate and :endDate")
    public int getCountOfTransaction(long memberShipId, long memberId, LocalDate startDate, LocalDate endDate);


}
