/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grandprix.gpline.mm.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "T_MESSAGE_MM")
@Data
public class Message {                               // Сообщение

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "F_ID")
    private long id;

    @Column(name = "F_MESSAGE_SENDER_ID")
    private String sender;                           // Отправитель сообщения (?идентификатор?)

    @Column(name = "F_MESSAGE_RECIPIENT_ID")
    private String recipient;                        // Получатель сообщения (?идентификатор?)

    @Column(name = "F_MESSAGE_CREATE_DATE")
    private Long createDate;                         // Дата/Время создания текста сообщения

    @Column(name = "F_MESSAGE_NUMBER")
    private int messageNumber;                       // Порядковый(логический) номер сообщения в рамках одного контакта

    @Column(name = "F_MESSAGE_DIRECTION")
    private boolean messageDirection;                // Тип=направление сообщения: (0 – входящее 1- исходящее)(0 - incoming 1 outgoing)

    @Column(name = "F_MESSAGE_TEXT")
    private String messageText;                      // Текст сообщения

    @Column(name = "F_MESSAGE_SUBJECT")
    private String messageSubject;                   // Тема письма. Заполняется только для канала Email

    @Column(name = "F_DIALOG_WAIT_DATE")
    private Long waitDate;                           // Дата/Время наступления перевода обращения в статус «ожидает ответа клиента»

    @Column(name = "F_CONTACT_ID")
    private String contactId;                        // Контакт, к которому относится сообщение
    
//    @ManyToOne
//    @JoinColumn(name = "F_CONTACT_ID")
//    @JsonIgnore()
//    private Contact contact;                         // Контакт, к которому относится сообщение
}

