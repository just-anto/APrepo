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
public class DtoExperience {
    @NotBlank
private String nameE;
@NotBlank 
private String descriptionE; 

//constructor

    public DtoExperience() {
    }

    public DtoExperience(String nameE, String descriptionE) {
        this.nameE = nameE;
        this.descriptionE = descriptionE;
    }

//getter & setter

    public String getNameE() {
        return nameE;
    }

    public void setNameE(String nameE) {
        this.nameE = nameE;
    }

    public String getDescriptionE() {
        return descriptionE;
    }

    public void setDescriptionE(String descriptionE) {
        this.descriptionE = descriptionE;
    }
}
