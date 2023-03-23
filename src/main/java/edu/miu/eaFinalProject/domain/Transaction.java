package edu.miu.eaFinalProject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue
    private long id;
    private LocalDate dateTime = LocalDate.now();
    @ManyToOne
    private Location location;
    @ManyToOne
    private Member member;
    @ManyToOne
    private Membership membership;
}
