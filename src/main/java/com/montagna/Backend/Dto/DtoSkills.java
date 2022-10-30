/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.montagna.Backend.Dto;

import javax.validation.constraints.NotBlank;

/**
 *
 * @author antom
 */
public class DtoSkills {
    @NotBlank
    private String name;
@NotBlank
    private int porcentage;

//Construtor

    public DtoSkills() {
    }

    public DtoSkills(String name, int porcentage) {
        this.name = name;
        this.porcentage = porcentage;
    }

//getter & setter

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
