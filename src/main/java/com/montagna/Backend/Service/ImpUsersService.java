/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.montagna.Backend.Service;

import com.montagna.Backend.Entity.Users;
import com.montagna.Backend.Repository.IUsersRepository;
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
public class ImpUsersService {
    @Autowired
    IUsersRepository iuserRepository;

public List<Users> list() {
        return iuserRepository.findAll();
    }

    public Optional<Users> getOne(int id) {
        return iuserRepository.findById(id);
    }

    public Optional<Users> getByName(String name) {
        return iuserRepository.findByName(name);
    }

    public void save(Users users) {
        iuserRepository.save(users);
    }

    public void delete(int id) {
        iuserRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return iuserRepository.existsById(id);
    }

    public boolean existsByName(String name) {
        return iuserRepository.existsByName(name);
    }
}
