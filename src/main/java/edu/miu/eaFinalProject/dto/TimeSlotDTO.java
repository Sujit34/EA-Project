package edu.miu.eaFinalProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class TimeSlotDTO {
    private Long id;
    private LocalDate startTime;
    private LocalDate endTime;
    private String day;
}
