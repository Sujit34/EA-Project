package edu.miu.eaFinalProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<BadgeDTO> badges = new ArrayList<>();
    private List<RoleDTO> roles = new ArrayList<>();
    private List<MembershipDTO> memberships = new ArrayList<>();
}
