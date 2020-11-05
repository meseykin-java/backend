package com.grandprix.gpline.mm.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Видео скриншотов
 */
@Entity
@Table(name = "T_SCREENRECORD")
@Data
public class ScreenRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "F_SCREEN_REC_ID")
    private long id;                            // Идентификатор записи экрана

    @Column(name = "F_SCREEN_GUID")
    private String  screeGuid;                  // Идентификатор записи экрана для рекордера (логин оператора operatorLogin)

    @Column(name = "F_STORAGEFILENAME")
    private String fileName;                    // Путь к файлу

    @Column(name = "F_RECORDBEGIN")
    private Long recordBegin;                   // Дата-время начала записи экрана (UTC), мс (startDate)

    @Column(name = "F_RECORDEND")
    private Long recordEnd;                     // Дата-время окончания записи экрана (UTC), мс

    @Column(name = "F_DURATION")
    private Long duration;                      // Продолжительность записи экрана

    @Column(name = "F_CHANNELID")
    private int channelId;                      // Идентификатор канала

    @Column(name = "F_SYNCID")
    private String syncId;                      // Идентификатор привязки к звонку(оператору

    @Column(name = "F_RECORDERID")
    private long recorderId;                    // Идентификатор рекордера

    @Column(name = "F_RESTORED")
    private Integer restored;                   //

    @ManyToOne
    @JoinColumn(name = "F_FILESTOREID")         // файловое хранилище
    private FileStore fileStore;
}
