package edu.miu.eaFinalProject.Controller;

import edu.miu.eaFinalProject.domain.Role;
import edu.miu.eaFinalProject.dto.RoleDTO;
import edu.miu.eaFinalProject.exception.ApiRequestException;
import edu.miu.eaFinalProject.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    RoleService roleService;

    @PostMapping("")
    public ResponseEntity<?> createRole(@RequestBody RoleDTO roleDTO) {
        RoleDTO roleDTOObj = null;
        try {
            roleDTOObj = roleService.createRole(roleDTO);
        } catch (Exception e) {
            throw new ApiRequestException("Error while creating role.!!", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<?> getRoleById(@PathVariable("roleId") long roleId) {
        RoleDTO roleDTO = null;
        try {
            roleDTO = roleService.getRoleById(roleId);
        } catch (Exception e) {
            throw new ApiRequestException("Error getting role by id.!!", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<RoleDTO>(roleDTO, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllRoles() {
        List<RoleDTO> roleDTOs = null;
        try {
            roleDTOs = roleService.getAllRole();
        } catch (Exception e) {
            throw new ApiRequestException("Error getting roles.!!", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<List<RoleDTO>>(roleDTOs, HttpStatus.OK);
    }


    @PutMapping("")
    public ResponseEntity<?> updateRole(@RequestBody RoleDTO roleDTO) {
        RoleDTO roleDTOObj = null;
        try {
            roleDTOObj = roleService.updateRole(roleDTO);
        } catch (Exception e) {
            throw new ApiRequestException("Error while updating role.!!", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<?> deleteRole(@PathVariable("roleId") long roleId) {
        RoleDTO roleDTO = null;
        try {
            roleService.deleteRole(roleId);
        } catch (Exception e) {
            throw new ApiRequestException("Error while deleting role.!!", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
