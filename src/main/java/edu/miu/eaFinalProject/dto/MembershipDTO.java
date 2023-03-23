package edu.miu.eaFinalProject.dto;

import edu.miu.eaFinalProject.domain.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class MembershipDTO {
    private long id;
    private Date startDate;
    private Date endDate;
    private String type;

    PlanDTO plan;

    private MemberDTO member;

}
