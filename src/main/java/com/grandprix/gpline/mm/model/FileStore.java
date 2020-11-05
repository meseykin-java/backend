package com.grandprix.gpline.mm.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Список файловых хранилищ
 */
@Entity
@Table(name = "T_FILESTORES")
@Data
public class FileStore {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "F_ID")
    private long id;                                    // Идентификатор записи

    @Column(name = "F_NAME")
    private String  name;                               // Наименование хранилища

    @Column(name = "F_HOST")
    private String  host;                               // Хост хранилища

    @Column(name = "F_PORT")
    private Integer port;                               // Порт хранилища

    @Column(name = "F_STORAGEMAXSIZE")
    private Integer maxSize;                            // Максимальный размер хранилища, МБ

    @Column(name = "F_WRITEPATH")
    private String  writePath;                          // Путь к файлам хранилища

    @Column(name = "F_STORE_LOGIN")
    private String  login;                              //Логин для доступа к хранилищу

    @Column(name = "F_STORE_PASSWORD")
    private String  password;                           // Пароль для доступа к хранилищу

    @Column(name = "F_BLOCK_SIZE")
    private Integer blockSize;                          // Размер кластера на диске по дефолту 4096 байт

    @Column(name = "F_REMOVE_META_INF")
    private String  remoteMetaInf;                      // Признак удалять метаинформацию/медиаинформацию о звонке. Режимы сервиса удаления

    @Column(name = "F_VOICE_STORE_DURATION")
    private Integer voiceDuration;                      // Срок хранения голосовых файлов, дней

    @Column(name = "F_SCREEN_STORE_DURATION")
    private Integer screenDuration;                     // Срок хранения экранов файлов, дней

    @Column(name = "F_VIDEO_STORE_DURATION")
    private Integer videoDuration;                      // Срок хранения видео файлов, дней

    @Column(name = "F_VOICE_FIRST_DATE_TO_REMOVE")
    private Timestamp  voiceFirstDateToRemove;          // Настройка сервиса удаления для аудиоданных. Предполагается, что все более ранние записи удалены

    @Column(name = "F_SCREEN_FIRST_DATE_TO_REMOVE")
    private Timestamp  videoFirstDateToRemove;          // Настройка сервиса удаления для экранов. Предполагается, что все более ранние записи удалены

    @Column(name = "F_VIDEO_FIRST_DATE_TO_REMOVE")
    private Timestamp  screenFirstDateToRemove;         // Настройка сервиса удаления для видео. Предполагается, что все более ранние записи удалены

    @Column(name = "F_FIRST_DATE_TO_BACKUP")
    private Timestamp firstDateToBackup;                // Настройка для резервного копирования: копировать с указанной даты

    @Column(name = "F_READPATH")
    private String  readPath;                           // Путь для клиентов, которые будут читать данные из хранилища

    @Column(name = "F_USE_FOR_REPORTS")
    private Integer useForReports;                      // Использовать это хранилище для сохранения отчетов

    @Column(name = "F_USE_FOR_EXPORT")
    private Integer userForExport;                      // Использовать это хранилище для экспорта

    @Column(name = "F_USE_AS_PCM_SOURCE")
    private Integer useAsPcmSource;                     // Использовать это хранилище как источник PCM файлов

    @Column(name = "F_INITIAL_STORE")
    private Integer initialStore;                       // Использовать это хранилище для первоначальной записи

    @Column(name = "F_RECORDERID")
    private long recorderId;                            // Код рекордера

    @Column(name = "F_DISPATCHERID")
    private Integer dispatcherId;                       // Идентификатор диспетчера обработывающего хранилище

    @Column(name = "F_DEPARTMENTID")
    private Integer departmentId;                       // Индентификатор порядка обработки хранилищ

    @Column(name = "F_ORDER")
    private Integer order;                              // Индентификатор порядка обработки хранилищ

    @Column(name = "F_SAVE_REPO")
    private Integer saveRepo;                           // Использовать это хранилище для хранения звонков под запретом удаления

    @Column(name = "F_SYSTEM_TYPE")
    private String  systemType;                         // Тип операционной системы FTP сервера
}
