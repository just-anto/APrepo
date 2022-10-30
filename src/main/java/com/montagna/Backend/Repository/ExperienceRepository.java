/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.montagna.Backend.Repository;

import com.montagna.Backend.Entity.Experience;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author antom
 */
@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Integer> {

    public Optional<Experience> findByNameE(String nameE);

    public boolean existsByNameE(String nameE);
    
}
