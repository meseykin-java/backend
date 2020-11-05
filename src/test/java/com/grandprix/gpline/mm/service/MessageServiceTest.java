package com.grandprix.gpline.mm.service;

import com.grandprix.gpline.mm.model.Contact;
import com.grandprix.gpline.mm.model.Message;
import com.grandprix.gpline.mm.repository.ContactRepository;
import com.grandprix.gpline.mm.repository.MessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class MessageServiceTest {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ContactRepository conactRepository;

    @Autowired
    private MessageService messageService;

    @TestConfiguration
    static class Configuration {

        @Bean
        public MessageService messageService(MessageRepository messageRepository) {
            return new MessageService(messageRepository);
        }
    }

    @BeforeEach
    void setUp() {
        Message message1 = new Message();
        message1.setMessageText("111");
        message1.setCreateDate(1566905420001L);
        messageRepository.save(message1);

        Message message2 = new Message();
        message2.setMessageText("222");
        messageRepository.save(message2);

        Message message3 = new Message();
        message3.setMessageText("333");
        messageRepository.save(message3);

        Contact contact1 = new Contact();
        contact1.setId("1");
        contact1.setMessages(Arrays.asList(message1));
        conactRepository.save(contact1);

        Contact contact2 = new Contact();
        contact2.setId("2");
        contact2.setMessages(Arrays.asList(message2, message3));
        conactRepository.save(contact2);

        message1.setContactId("1");
        message2.setContactId("2");
        message3.setContactId("2");
    }

//    @Test
//    void getMessagesByContactId() {    // этот метод rest-сервиса в приложении не используется
//        assertThat(messageService.getMessagesByContactId("1"))
//                .hasSize(1)
//                .extracting(Message::getMessageText)
//                .containsExactly("111");
//        assertThat(messageService.getMessagesByContactId("2"))
//                .hasSize(2)
//                .extracting(Message::getMessageText)
//                .containsExactly("222", "333");
//        assertThat(messageService.getMessagesByContactId("3"))
//                .hasSize(0);
//    }

    @Test
    void findByContactIdAndCreateDate() {
        Message message = messageService.findByContactIdAndCreateDate("1", 1566905420001L);
        assertThat(message)
                .extracting(Message::getMessageText)
                .isEqualTo("111");

        Message messageNull = messageService.findByContactIdAndCreateDate("1", 1566905420000L);
        assertThat(messageNull)
                .isNull();

        messageNull = messageService.findByContactIdAndCreateDate("2", 1566905420001L);
        assertThat(messageNull)
                .isNull();
    }
}