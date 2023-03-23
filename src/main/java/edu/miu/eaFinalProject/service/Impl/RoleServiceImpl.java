package edu.miu.eaFinalProject.service.Impl;

import edu.miu.eaFinalProject.domain.Role;
import edu.miu.eaFinalProject.dto.RoleDTO;
import edu.miu.eaFinalProject.dto.adapter.RoleAdapter;
import edu.miu.eaFinalProject.repository.RoleRepository;
import edu.miu.eaFinalProject.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    public RoleDTO createRole(RoleDTO roleDTO) {
        Role role = RoleAdapter.getRoleFromRoleDTO(roleDTO);
        roleRepository.save(role);
        return RoleAdapter.getRoleDTOFromRole(role);
    }

    public RoleDTO getRoleById(Long id) {
        return RoleAdapter.getRoleDTOFromRole(roleRepository.findById(id).orElse(null));
    }

    public List<RoleDTO> getAllRole() {
        return RoleAdapter.getRoleDTOListFromRoleList(roleRepository.findAll());
    }

    public RoleDTO updateRole(RoleDTO roleDTO) {
        Role role = roleRepository.findById(roleDTO.getId()).orElse(null);
        role.setRole(roleDTO.getRole());
        roleRepository.save(role);
        return RoleAdapter.getRoleDTOFromRole(role);
    }

    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}
