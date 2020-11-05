/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grandprix.gpline.mm.model;

import lombok.Data;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "T_CONTACT_MM")
@Data
public class Contact {                          // Контакт

    @Id
    @Column(name = "F_ID")
    private String id;

    @Column(name = "F_CONTACT_HEAD")
    private Integer head;                       // Спецполе - признак группирующего контакта (1) Используется при сортировке, для разграничения контактов, принадлежащих одному обращению
                                                // У такого контакта все поля могут быть пустые, кроме F_REQUEST_ID
    
    @Column(name = "F_CONTACT_OPERATOR_LOGIN")
    private String operatorLogin;               // Логин оператора из AD, обрабатывающего обращение

    @Column(name = "F_CONTACT_START_DATE")
    private Long startDate;                     // Дата/Время начала обработки контакта

    @Column(name = "F_CONTACT_IS_AUTO")
    private Integer isAuto;                     // Признаки этапов ЖЦ обращения (автообработка) Если = null, то сообщение отправил оператор - заполнено поле operatorLogin

    @Column(name = "F_CONTACT_TRANSFER_TO")
    private String transferTo;                  // Направление перевода обращения (Другому оператору	В другую очередь (скилл-группу)	Назначить на Супервизора)

    @Column(name = "F_CONTACT_DURATION")
    private Long duration;                      // Продолжительность контакта/обращения (Время между последним и первым сообщением в секундах)

    @Column(name = "F_CONTACT_NUMBER")
    private Integer contactNumber;              // Номер  контакта в данном обращении

    @Column(name = "F_IN_MSG_COUNT")
    private Integer inMessageCount;             // Количество сообщений оператора в контакте

    @Column(name = "F_OUT_MSG_COUNT")
    private Integer outMessageCount;            // Количество сообщений клиента в контакте

    @Column(name = "F_MARKED")
    private Integer marked;                     // Контакт оценен?

    @ManyToOne
    @JoinColumn(name = "F_REQUEST_ID")
    private Request request;                    // Обращение, к которому относится контакт
    
//    @OneToMany(mappedBy = "contactId")
//    @OrderBy("messageNumber")
    @Transient
    private List<Message> messages;

}

