package com.grandprix.gpline.mm.model.filter;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class ContactFilterPost implements Serializable {
    private static final long serialVersionUID = 1L;

    private String guid;                            // ID обращения/контакта
    private Long regDateStart;                      // Левая граница диапазона регистрации обращения
    private Long regDateEnd;                        // Правая граница диапазона регистрации обращения
    private Long closeDateStart;                    // Левая граница диапазона закрытия обращения
    private Long closeDateEnd;                      // Правая граница диапазона закрытия обращения
    private List<String> filialIdList;                // Филиал
    private List<Long> regularGroupIdList;          // Группа операторов
    private List<String> operatorLoginList;         // Логин оператора
    private String operatorLogin;                   // Логин оператора (поиск по произвольному имени)
    private List<String> supervisorLoginList;       // Логин менеджера группы
    private List<Integer> channelIdList;            // Идентификатор канала
    private Boolean direction;                      // Направление обращения (входящее, исходящее)
    private Integer requestStatus;                  // Статус обращения 
    private List<Integer> closeStatusList;          // Статус закрытого обращения
                                                    //   Закрыто\Пустой текст;
                                                    //   Закрыто клиентом;
                                                    //   Закрыто чат-ботом;
                                                    //   Закрыто\Канал недоступен;
                                                    //   Закрыто\С ответом;
                                                    //   Закрыто\Без ответа;
                                                    //   Закрыто\Черный список;
                                                    //   Закрыто\Спам;
                                                    //   Закрыто\по таймауту
                                                    //   Передано в другое подразделение    
    private String messageText;                     // Текст в одном из сообщений
    private String contactNumberRel;                // Отношение (>=<) к количеству контактов
    private Integer contactNumber;                  // Количество контактов
    private String fromNumber;                      // MSISDN клиента, с которого поступило сообщение 
    private String toNumber;                        // MSISDN клиента, на который поступило сообщение
    private String clientClass;                     // Класс клиента (Базовый, Бронзовый, Золотой, Платиновый и т.д.)
    private Integer requestPriority;                // Приоритет
    private String transferTo;                      // Переведен к
    private String clientType;                      // Тип клиента (Коммерческий, Дилер, Внутренний и т.д.) //TODO #7476 ПОКА НЕ ИСПОЛЬЗУЕТСЯ В ЗАПРОСАХ
    private Boolean transferExists;                 // Существование перевода
    private Integer durationStart;                   //В секундах  Duration.between(t1, t2).toSeconds();
    private Integer durationEnd;                     //В секундах 

    //Технические параметры
    private Integer limit = 99999;
    private Integer offset = 0;
    private List<String> orderList;
    private Boolean showAllRequests;
    private Boolean showChatbots;
}