/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grandprix.gpline.mm.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "T_DEPARTMENT")
@Data
public class Department {                               // Филиал

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "F_ID")
    private int id;

    @Column(name = "F_NAME")
    private String name;                                // Наименование филиала
}
