/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.montagna.Backend.Controller;


import com.montagna.Backend.Dto.DtoUsers;
import com.montagna.Backend.Entity.Users;
import com.montagna.Backend.Security.Controller.Message;
import com.montagna.Backend.Service.ImpUsersService;
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
@RequestMapping("/users")
@CrossOrigin
public class UsersController {
    @Autowired
    ImpUsersService usersService;

    @GetMapping("/list/get")
    public ResponseEntity<List<Users>> list() {
        List<Users> list = usersService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping("users/create/")
    public String createUser(@RequestBody Users users) {
          usersService.save(users);
        return "Usuario nuevo creado";
}

    @PutMapping("users/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoUsers dtoUsers) {
        if (!usersService.existsById(id)) {
            return new ResponseEntity(new Message("Id inexistente"), HttpStatus.BAD_REQUEST);
        }
        if (usersService.existsByName(dtoUsers.getName()) && usersService.getByName(dtoUsers.getName()).get().getId() != id) {
            return new ResponseEntity(new Message("Usuario existente"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(dtoUsers.getName())) {
            return new ResponseEntity(new Message("Nombre obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Users users = usersService.getOne(id).get();
        users.setName(dtoUsers.getName());
        users.setLastname(dtoUsers.getLastname());
        users.setDescription(dtoUsers.getDescription());
        users.setImg(dtoUsers.getImg());

        usersService.save(users);
        return new ResponseEntity(new Message("Usuario actualizado"), HttpStatus.OK);
    }

@DeleteMapping("users/detail/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!usersService.existsById(id)) {
            return new ResponseEntity(new Message("Id inexistente"), HttpStatus.BAD_REQUEST);
        }

        usersService.delete(id);
        return new ResponseEntity(new Message("Usuario eliminado"), HttpStatus.OK);
    }

    @GetMapping("users/detail/{id}")
    public ResponseEntity<Users> getById(@PathVariable("id") int id) {
        if (!usersService.existsById(id)) {
            return new ResponseEntity(new Message("No existe"), HttpStatus.NOT_FOUND);
        }
        Users users = usersService.getOne(id).get();
        return new ResponseEntity(users, HttpStatus.OK);
    }
}
