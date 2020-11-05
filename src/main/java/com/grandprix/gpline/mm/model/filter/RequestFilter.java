package com.grandprix.gpline.mm.model.filter;

import java.io.Serializable;

import lombok.Data;

@Data
public class RequestFilter implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long regDateStart;                      // Левая граница диапазона регистрации обращения
    private Long regDateEnd;                        // Правая граница диапазона регистрации обращения
    private Long closeDateStart;                    // Левая граница диапазона закрытия обращения
    private Long closeDateEnd;                      // Правая граница диапазона закрытия обращения
    private Long filialId;                          
    private String operatorLogin;
    private String supervisorLogin;
    private Integer channelId;
    private Boolean direction;
    private Integer requestStatus;
    private Integer closeStatus;                    //TODO Статус закрытия - разобраться
    private String messageText; 
    private Integer contactNumber;
    private String fromClient;                      //TODO С номера клиента - разобраться 
    private String toClient;                        //TODO На номер клиента - разобраться
    private String clientType;                      //TODO Тип клиента - разобраться
    private Integer requestPriority;
    private String transferTo;
    private Boolean transferExists;
    private Integer durationStart;                   //В секундах  Duration.between(t1, t2).toSeconds();
    private Integer durationEnd;                     //В секундах
    
}