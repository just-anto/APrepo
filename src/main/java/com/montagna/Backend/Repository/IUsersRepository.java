/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.montagna.Backend.Repository;

import com.montagna.Backend.Entity.Users;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author antom
 */
@Repository
public interface IUsersRepository extends JpaRepository<Users, Integer> {

    public Optional<Users> findByName(String name);
    public boolean existsByName(String name);
    
}
