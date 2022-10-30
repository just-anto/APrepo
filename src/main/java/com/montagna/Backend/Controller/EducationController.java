/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.montagna.Backend.Controller;

import com.montagna.Backend.Dto.DtoEducation;
import com.montagna.Backend.Entity.Education;
import com.montagna.Backend.Security.Controller.Message;
import com.montagna.Backend.Service.EducationService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/education")
@CrossOrigin
public class EducationController {
    @Autowired
    EducationService educationService;

    @GetMapping("/list")
    public ResponseEntity<List<Education>> list() {
        List<Education> list = educationService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoEducation dtoEducation) {
        if (StringUtils.isBlank(dtoEducation.getNameE())) {
            return new ResponseEntity(new Message("Nombre obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (educationService.existsByNameE(dtoEducation.getNameE())) {
            return new ResponseEntity(new Message("Experiencia existente"), HttpStatus.BAD_REQUEST);
        }

        Education education = new Education(dtoEducation.getNameE(), dtoEducation.getDescriptionE());
        educationService.save(education);

        return new ResponseEntity(new Message("Experiencia agregada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoEducation dtoEducation) {
        if (!educationService.existsById(id)) {
            return new ResponseEntity(new Message("Id inexistente"), HttpStatus.BAD_REQUEST);
        }
        if (educationService.existsByNameE(dtoEducation.getNameE()) && educationService.getByNameE(dtoEducation.getNameE()).get().getId() != id) {
            return new ResponseEntity(new Message("Experiencia existente"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(dtoEducation.getNameE())) {
            return new ResponseEntity(new Message("Nombre obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Education education = educationService.getOne(id).get();
        education.setNameE(dtoEducation.getNameE());
        education.setDescriptionE((dtoEducation.getDescriptionE()));

        educationService.save(education);
        return new ResponseEntity(new Message("Experiencia actualizada"), HttpStatus.OK);
    }

@DeleteMapping("/detail/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!educationService.existsById(id)) {
            return new ResponseEntity(new Message("Id inexistente"), HttpStatus.BAD_REQUEST);
        }

        educationService.delete(id);
        return new ResponseEntity(new Message("Experiencia eliminada"), HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Education> getById(@PathVariable("id") int id) {
        if (!educationService.existsById(id)) {
            return new ResponseEntity(new Message("No existe"), HttpStatus.NOT_FOUND);
        }
        Education education = educationService.getOne(id).get();
        return new ResponseEntity(education, HttpStatus.OK);
    }
}
