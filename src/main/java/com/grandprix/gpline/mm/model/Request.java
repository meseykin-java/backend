/*

 */
package com.grandprix.gpline.mm.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "T_REQUEST_MM")
@Data
public class Request {                               // Обращение

    @Id
    @Column(name = "F_ID")
    //private long id;
    private String id;

    @Column(name = "F_CUSTOMER_CLASS")
    private String customerClass;                    // Класс клиента

    @Column(name = "F_REQUEST_REGISTRATION_DATE")
    private Long registrationDate;                   // Дата/время регистрации обращения

    @Column(name = "F_REQUEST_PRIORITY")
    private int priority;                            // Приоритет обращения

    @Column(name = "F_REQUEST_IS_REOPEN")
    private boolean reopen;                        // Признак переоткрытого обращения: "1" - переоткрыто, "0" - не переоткрыто.
    
    @Column(name = "F_REQUEST_IS_REPEAT")
    private boolean repeat;                        // Признак повторного обращения. «1» – повторное «0» – не повторное

    @Column(name = "F_SUPERVISOR_LOGIN")
    private String supervisorLogin;                  // Логин супервизора из AD, повторно открывшего обращение

    @Column(name = "f_request_reopen_date")
    private Long requestOpenDate;                    // Дата/время переоткрытия обращения

    @Column(name = "F_REQUEST_END_DATE")
    private Long requestCloseDate;                   // Дата/Время закрытия обращения

    @Column(name = "F_IN_MSG_COUNT")
    private Integer inMessageCount;                  // Количество входящих сообщений в данном обращении

    @Column(name = "F_OUT_MSG_COUNT")
    private Integer outMessageCount;                 // Количество исходящих сообщений в данном обращении

    @Column(name = "F_REQUEST_STATUS")
    private int requestStatus;                       // Статус, с которым было закрыто обращение

    @Column(name = "F_CONTACT_COUNT")
    private Integer contactCount;                    // Количество контактов в данном обращении

    @Column(name = "F_FILIAL_ID")
    private String filialId;                           // Филиал
//    private long filialId;                           // Филиал

    @Column(name = "F_CHANNEL_ID")
    private int channelId;                           // Канал

    @Column(name="F_PARENT_CHANNEL_ID")
    private Integer parentId;                        // Идентификатор первичного канала, по которому первоначально поступило обращение клиента. (Заполняется только при перевода обращения в другой канал.)

    @Column(name="F_CUSTOMER_TYPE")
    private String customerType;                     // Тип клиента

    @Column(name = "F_SENDER_ID")
    private String abonent;                          // Абонент (Клиент)

    @Column(name = "F_DIRECTION")
    private Boolean direction;                       // Направление 1го сообщения в 1м контакте

//    @Transient 
//    @OneToMany(mappedBy = "request")
////    @JsonManagedReference
////    @JsonIgnore
//    private List<Contact> contacts;
}
