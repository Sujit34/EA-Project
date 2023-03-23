package edu.miu.eaFinalProject.dto.adapter;


import edu.miu.eaFinalProject.domain.Role;
import edu.miu.eaFinalProject.dto.RoleDTO;

import java.util.ArrayList;
import java.util.List;

public class RoleAdapter {
    public static RoleDTO getRoleDTOFromRole(Role role){
        if(role == null) return null;
        RoleDTO roleDTO = new RoleDTO();

        roleDTO.setId(role.getId());
        roleDTO.setRole(role.getRole());

        return roleDTO;
    }

    public static Role getRoleFromRoleDTO(RoleDTO roleDTO){
        if(roleDTO == null) return null;
        Role role = new Role();

        role.setId(roleDTO.getId());
        role.setRole(roleDTO.getRole());

        return role;
    }

    public static List<RoleDTO> getRoleDTOListFromRoleList (List<Role> roles){
        /*List<RoleDTO> roleDTOS = new ArrayList<>();
        for(Role r: roles) {
            roleDTOS.add(getRoleDTOFromRole(r));
        }
        return roleDTOS;*/
        if(roles==null)return null;
        return roles.stream().map(role -> getRoleDTOFromRole(role)).toList();
    }

    public static List<Role> getRoleListFromRoleDTOList (List<RoleDTO> roleDTOs){
        if(roleDTOs==null) return null;

        List<Role> roles = new ArrayList<>();
        for(RoleDTO r: roleDTOs) {
            roles.add(getRoleFromRoleDTO(r));
        }
        return roles;

//        return roleDTOs.stream().map(roleDTO -> getRoleFromRoleDTO(roleDTO)).toList();
    }
}
