package com.grandprix.gpline.mm.service;

import com.grandprix.gpline.mm.model.*;
import com.grandprix.gpline.mm.repository.*;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class ScreenRecordServiceTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ScreenRecordService screenRecordService;

    @TestConfiguration
    static class Configuration {

        @Bean
        public ScreenRecordService screenRecordService(ScreenRecordRepository screenRecordRepository) {
            return new ScreenRecordService(screenRecordRepository);
        }
    }

    @BeforeEach
    void setUp() {
        FileStore fileStore = new FileStore();
        fileStore.setHost("hostname");
        fileStore.setWritePath("C:/path");
        entityManager.persist(fileStore);

        ScreenRecord screenRecord1 = new ScreenRecord();
        screenRecord1.setSyncId("ivanov@mail.com");
        screenRecord1.setFileName("dir/file1");
        screenRecord1.setRecordBegin(10L);
        screenRecord1.setRecordEnd(100L);
        screenRecord1.setFileStore(fileStore);
        entityManager.persist(screenRecord1);

        ScreenRecord screenRecord2 = new ScreenRecord();
        screenRecord2.setSyncId("ivanov@mail.com");
        screenRecord2.setFileName("dir/file2");
        screenRecord2.setRecordBegin(101L);
        screenRecord2.setRecordEnd(200L);
        screenRecord2.setFileStore(fileStore);
        entityManager.persist(screenRecord2);

        ScreenRecord screenRecord3 = new ScreenRecord();
        screenRecord3.setSyncId("ivanov@mail.com");
        screenRecord3.setFileName("dir/file3");
        screenRecord3.setRecordBegin(201L);
        screenRecord3.setRecordEnd(300L);
        screenRecord3.setFileStore(fileStore);
        entityManager.persist(screenRecord3);
    }

    @Ignore
    void getScreenRecords_First() {
        String login = "ivanov@mail.com";
        Long startDate = 1L;
        Long endDate = 10L;
        assertThat(screenRecordService.getScreenRecords(login, startDate, endDate))
                .isNotEmpty()
                .doesNotContainNull()
                .hasSize(1)
                .containsExactly("sld://hostname?C:/path/dir/file1")
                .doesNotContain("sld://hostname?C:/path/dir/file2", "sld://hostname?C:/path/dir/file3");
    }

    @Ignore
    void getScreenRecords_All() {
        String login = "ivanov@mail.com";
        Long startDate = 1L;
        Long endDate = 1000L;
        assertThat(screenRecordService.getScreenRecords(login, startDate, endDate))
                .isNotEmpty()
                .doesNotContainNull()
                .hasSize(3)
                .doesNotHaveDuplicates()
                .containsExactly("sld://hostname?C:/path/dir/file1",
                        "sld://hostname?C:/path/dir/file2",
                        "sld://hostname?C:/path/dir/file3");
    }

    @Test
    void getScreenRecords_BadLogin() {
        String login = "sidorow@mail.ru";
        Long startDate = 1L;
        Long endDate = 10L;
        assertThat(screenRecordService.getScreenRecords(login, startDate, endDate))
                .isEmpty();
    }

    @Ignore
    void getScreenRecords_LoginUpperCase() {
        //String login = "IVANOV@MAIL.COM";
        String login = "ivanov@mail.com";
        Long startDate = 1L;
        Long endDate = 10L;
        assertThat(screenRecordService.getScreenRecords(login, startDate, endDate))
                .isEqualTo(Arrays.asList("sld://hostname?C:/path/dir/file1"));
    }
}