/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.montagna.Backend.Security.Service;

import com.montagna.Backend.Security.Entity.Role;
import com.montagna.Backend.Security.Enums.RoleName;
import com.montagna.Backend.Security.Repositority.IRoleRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author antom
 */
@Service
@Transactional
public class RoleService {
    @Autowired
    IRoleRepository iroleRepository;

    public Optional<Role> getByRoleName(RoleName roleName) {
        return iroleRepository.findByRoleName(roleName);
    }

    public void save(Role role) {
        iroleRepository.save(role);
    }
}
