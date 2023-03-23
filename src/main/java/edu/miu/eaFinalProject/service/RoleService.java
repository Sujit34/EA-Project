package edu.miu.eaFinalProject.service;

import edu.miu.eaFinalProject.domain.Role;
import edu.miu.eaFinalProject.dto.RoleDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RoleService {

    public RoleDTO createRole(RoleDTO roleDTO);

    public RoleDTO getRoleById(Long id);

    public List<RoleDTO> getAllRole();

    public RoleDTO updateRole(RoleDTO roleDTO);

    public void deleteRole(Long id);


}
