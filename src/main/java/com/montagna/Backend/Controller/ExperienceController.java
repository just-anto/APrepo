/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.montagna.Backend.Controller;

import com.montagna.Backend.Dto.DtoExperience;
import com.montagna.Backend.Entity.Experience;
import com.montagna.Backend.Security.Controller.Message;
import com.montagna.Backend.Service.ExperienceService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author antom
 */
@RestController
@RequestMapping("/explab")
@CrossOrigin (origins = "https://portfoliomontagna-676d9.web.app")
public class ExperienceController {
    @Autowired
    ExperienceService experienceService;

    @GetMapping("/list")
    public ResponseEntity<List<Experience>> list() {
        List<Experience> list = experienceService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoExperience dtoExperience) {
        if (StringUtils.isBlank(dtoExperience.getNameE())) {
            return new ResponseEntity(new Message("Nombre obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (experienceService.existsByNameE(dtoExperience.getNameE())) {
            return new ResponseEntity(new Message("Experiencia existente"), HttpStatus.BAD_REQUEST);
        }

        Experience experience = new Experience(dtoExperience.getNameE(), dtoExperience.getDescriptionE());
        experienceService.save(experience);

        return new ResponseEntity(new Message("Experiencia agregada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoExperience dtoExperience) {
        if (!experienceService.existsById(id)) {
            return new ResponseEntity(new Message("Id inexistente"), HttpStatus.BAD_REQUEST);
        }
        if (experienceService.existsByNameE(dtoExperience.getNameE()) && experienceService.getByNameE(dtoExperience.getNameE()).get().getId() != id) {
            return new ResponseEntity(new Message("Experiencia existente"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(dtoExperience.getNameE())) {
            return new ResponseEntity(new Message("Nombre obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Experience experience = experienceService.getOne(id).get();
        experience.setNameE(dtoExperience.getNameE());
        experience.setDescriptionE((dtoExperience.getDescriptionE()));

        experienceService.save(experience);
        return new ResponseEntity(new Message("Experiencia actualizada"), HttpStatus.OK);
    }

    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!experienceService.existsById(id)) {
            return new ResponseEntity(new Message("Id inexistente"), HttpStatus.BAD_REQUEST);
        }

        experienceService.delete(id);
        return new ResponseEntity(new Message("Experiencia eliminada"), HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Experience> getById(@PathVariable("id") int id) {
        if (!experienceService.existsById(id)) {
            return new ResponseEntity(new Message("No existe"), HttpStatus.NOT_FOUND);
        }
        Experience experience = experienceService.getOne(id).get();
        return new ResponseEntity(experience, HttpStatus.OK);
    }
}
