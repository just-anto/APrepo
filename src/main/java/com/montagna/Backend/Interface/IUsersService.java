/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.montagna.Backend.Interface;

import com.montagna.Backend.Entity.Users;
import java.util.List;

/**
 *
 * @author antom
 */
public interface IUsersService {
    //Traer lista de usuarios
    public List<Users> getUsers();
    
    //Guardar un objeto de tipo usuarios
    public void saveUsers(Users users);
    
    //Eliminar un objeto pero lo buscamos por ID
    public void deleteUsers(Long id);
    
    //Buscar una usuario por ID
    public Users findUsers(Long id);
}
