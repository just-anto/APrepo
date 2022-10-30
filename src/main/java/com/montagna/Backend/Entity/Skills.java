/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.montagna.Backend.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author antom
 */
@Entity
public class Skills {
     @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int porcentage;

//Construtor
    public Skills() {
    }

    public Skills(String name, int porcentage) {
        this.name = name;
        this.porcentage = porcentage;
    }

//getter & setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPorcentage() {
        return porcentage;
    }

    public void setPorcentage(int porcentage) {
        this.porcentage = porcentage;
    }
}
