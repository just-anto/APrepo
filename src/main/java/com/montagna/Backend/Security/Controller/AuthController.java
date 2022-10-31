/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.montagna.Backend.Security.Controller;

import com.montagna.Backend.Security.Dto.JwtDto;
import com.montagna.Backend.Security.Dto.NewUser;
import com.montagna.Backend.Security.Dto.UserLogin;
import com.montagna.Backend.Security.Entity.Role;
import com.montagna.Backend.Security.Entity.User;
import com.montagna.Backend.Security.Enums.RoleName;
import com.montagna.Backend.Security.Jwt.JwtProvider;
import com.montagna.Backend.Security.Service.RoleService;
import com.montagna.Backend.Security.Service.UserService;
import java.util.HashSet;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author antom
 */
@RestController
@RequestMapping("/auth")
@CrossOrigin (origins = "https://portfoliomontagna-676d9.web.app")
public class AuthController {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/new")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NewUser newUser, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<>(new Message("Nombre o email invalido"),HttpStatus.BAD_REQUEST);
        
        if(userService.existsByUserName(newUser.getUserName())){
            return new ResponseEntity<>(new Message("Ese nombre de usuario ya existe"), HttpStatus.BAD_REQUEST);
        }
        if(userService.existsByEmail(newUser.getEmail())){
            return new ResponseEntity<>(new Message("Ese email ya existe"), HttpStatus.BAD_REQUEST);
        }
        User user = new User(newUser.getName(), newUser.getUserName(),
            newUser.getEmail(), passwordEncoder.encode(newUser.getPassword()));
        
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getByRoleName(RoleName.ROLE_USER).get());
        
        if(newUser.getRole().contains("admin"))
            roles.add(roleService.getByRoleName(RoleName.ROLE_ADMIN).get());
        user.setRole(roles);
        userService.save(user);
        
        return new ResponseEntity<>(new Message("Usuario guardado"),HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody UserLogin userLogin,BindingResult bindingResult){
    if (bindingResult.hasErrors()) 
            return new ResponseEntity(new Message("Campos mal puestos"), HttpStatus.BAD_REQUEST);

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getUserName(), userLogin.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());

    return new ResponseEntity<>(jwtDto, HttpStatus.OK);

    }
}
