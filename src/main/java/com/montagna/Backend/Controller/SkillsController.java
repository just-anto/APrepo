package com.montagna.Backend.Controller;


import com.montagna.Backend.Dto.DtoSkills;
import com.montagna.Backend.Entity.Skills;
import com.montagna.Backend.Security.Controller.Message;
import com.montagna.Backend.Service.SkillsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author antom
 */
@RestController
@RequestMapping("/skills")
@CrossOrigin (origins = "https://portfoliomontagna-676d9.web.app")
public class SkillsController {
    @Autowired
    SkillsService skillsService;

    @GetMapping("/list")
    public ResponseEntity<List<Skills>> list() {
        List<Skills> list = skillsService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    /*@GetMapping("/detail/{id}")
    public ResponseEntity<Skills> getById(@PathVariable("id") int id) {
        if (!skillsService.existsById(id)) {
            return new ResponseEntity(new Message("No existe el id"), HttpStatus.NOT_FOUND);
        }
        Skills skills = skillsService.getOne(id).get();
        return new ResponseEntity(skills, HttpStatus.OK);
    }*/

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!skillsService.existsById(id)) {
            return new ResponseEntity(new Message("No existe"), HttpStatus.NOT_FOUND);
        }
        skillsService.delete(id);
        return new ResponseEntity(new Message("Skill eliminada"), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoSkills dtoskills) {
        if (StringUtils.isBlank(dtoskills.getName())) {
            return new ResponseEntity(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (skillsService.existsByName(dtoskills.getName())) {
            return new ResponseEntity(new Message("Esa skill ya existe"), HttpStatus.BAD_REQUEST);
        }

        Skills skills = new Skills(dtoskills.getName(), dtoskills.getPorcentage());
        skillsService.save(skills);

        return new ResponseEntity(new Message("Skill agregada"), HttpStatus.OK);
    }

    /*@PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoSkills dtoskills) {

        if (!skillsService.existsById(id)) {
            return new ResponseEntity(new Message("El ID no existe"), HttpStatus.BAD_REQUEST);
        }

        if (skillsService.existsByName(dtoskills.getName()) && skillsService.getByName(dtoskills.getName()).get()
                .getId() != id) {
            return new ResponseEntity(new Message("Esta skill ya existe"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(dtoskills.getName())) {
            return new ResponseEntity(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Skills skills = skillsService.getOne(id).get();
        skills.setName(dtoskills.getName());
        skills.setPorcentage(dtoskills.getPorcentage());

        skillsService.save(skills);
        return new ResponseEntity(new Message("Skill actualizada"), HttpStatus.OK);

    }*/

}
