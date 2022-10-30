/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.montagna.Backend.Service;

import com.montagna.Backend.Entity.Skills;
import com.montagna.Backend.Repository.SkillsRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author antom
 */
@Transactional
@org.springframework.stereotype.Service
public class SkillsService {
    @Autowired
    SkillsRepository skillsrepository;

    public List<Skills> list() {
        return skillsrepository.findAll();
    }

    public Optional<Skills> gotOne(int id) {
        return skillsrepository.findById(id);
    }

    public Optional<Skills> getByName(String name) {
        return skillsrepository.findByName(name);
    }

    public void save(Skills skills) {
        skillsrepository.save(skills);
    }

    public void delete(int id) {
        skillsrepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return skillsrepository.existsById(id);
    }

    public boolean existsByName(String name) {
        return skillsrepository.existsByName(name);
    }

    public Object getOne(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
