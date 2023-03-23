package edu.miu.eaFinalProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class PlanDTO {
    private long id;

    private String name;

    private String description;

    private List<RoleDTO> roles = new ArrayList<>();
}
