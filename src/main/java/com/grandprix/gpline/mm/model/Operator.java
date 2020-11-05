package com.grandprix.gpline.mm.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "T_USERTABLE")
@Data
public class Operator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "F_ID")
    private int id;

    @Column(name = "F_LOGIN")
    private String login;
}
