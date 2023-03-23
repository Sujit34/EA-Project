package edu.miu.eaFinalProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class BadgeDTO {
    private long id;
    private String badgeId;
    private boolean active;
}
