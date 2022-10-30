/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.montagna.Backend.Service;

import com.montagna.Backend.Entity.Experience;
import com.montagna.Backend.Repository.ExperienceRepository;
import java.util.List;
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
public class ExperienceService {
    @Autowired
    ExperienceRepository experienceRepository;

    public List<Experience> list() {
        return experienceRepository.findAll();
    }

    public Optional<Experience> getOne(int id) {
        return experienceRepository.findById(id);
    }

    public Optional<Experience> getByNameE(String nameE) {
        return experienceRepository.findByNameE(nameE);
    }

    public void save(Experience expe) {
        experienceRepository.save(expe);
    }

    public void delete(int id) {
        experienceRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return experienceRepository.existsById(id);
    }

    public boolean existsByNameE(String nameE) {
        return experienceRepository.existsByNameE(nameE);
    }
}
