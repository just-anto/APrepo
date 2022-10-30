/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.montagna.Backend.Security.Service;

import com.montagna.Backend.Security.Entity.User;
import com.montagna.Backend.Security.Repositority.IUserRepository;
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
public class UserService {
    @Autowired
    IUserRepository iuserRepository;

    public Optional<User> getByUserName(String userName) {
        return iuserRepository.findByUserName(userName);
    }

    public boolean existsByUserName(String userName) {
        return iuserRepository.existsByUserName(userName);
    }

    public boolean existsByEmail(String email) {
        return iuserRepository.existsByEmail(email);
    }

    public void save(User user) {
        iuserRepository.save(user);
    }
}
