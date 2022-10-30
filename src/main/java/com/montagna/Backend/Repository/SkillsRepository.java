/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.montagna.Backend.Repository;

import com.montagna.Backend.Entity.Skills;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author antom
 */
public interface SkillsRepository extends JpaRepository<Skills, Integer> {

    Optional<Skills> findByName(String name);

    public boolean existsByName(String name);
    
}
