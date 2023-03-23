package edu.miu.eaFinalProject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
    private long id;
    private LocalDate dateTime;
    private MemberDTO memberDTO;
    private MembershipDTO membershipDTO;
    private LocationDTO locationDTO;
}
