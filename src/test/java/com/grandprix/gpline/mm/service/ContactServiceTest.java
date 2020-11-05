package com.grandprix.gpline.mm.service;

import com.grandprix.gpline.mm.model.Contact;
import com.grandprix.gpline.mm.model.Message;
import com.grandprix.gpline.mm.model.Request;
import com.grandprix.gpline.mm.model.filter.ContactFilterPost;
import com.grandprix.gpline.mm.repository.ContactFilterRepository;
import com.grandprix.gpline.mm.repository.ContactRepository;
import com.grandprix.gpline.mm.repository.MessageRepository;
import com.grandprix.gpline.mm.repository.RequestRepository;
import com.grandprix.gpline.mm.repository.impl.ContactFilterRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ContactServiceTest {

    @Autowired
     private ContactRepository conactRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private  MessageRepository messagetRepository;

    @Autowired
    private ContactService contactService;

    @TestConfiguration
    static class Configuration {

        @Bean
        public ContactService contactService(ContactRepository contactRepository) {
            return new ContactService(contactRepository);
        }

        @Bean
        public ContactFilterRepository contactFilterRepository(EntityManager entityManager) {
            return new ContactFilterRepositoryImpl(entityManager);
        }
    }

    @BeforeEach
    public void setUp() {
        Request request1 = new Request();
        request1.setId("1");
        request1.setChannelId(1);
        request1.setFilialId("1");
        request1.setRegistrationDate(11L);
        request1.setRequestStatus(1);       // = closeStatus
        requestRepository.save(request1);

        Request request2 = new Request();
        //request2.setId(2L);
        request2.setId("2");
        request2.setChannelId(2);
        request2.setFilialId("2");
        request2.setRegistrationDate(22L);
        request2.setRequestStatus(2);       // = closeStatus
        requestRepository.save(request2);

        Message message1 = new Message();
        message1.setMessageText("1111");
        messagetRepository.save(message1);

        Message message2 = new Message();
        message2.setMessageText("2222");
        messagetRepository.save(message2);

        Message message3 = new Message();
        message3.setMessageText("3333");
        messagetRepository.save(message3);

        Contact contact1 = new Contact();
        contact1.setId("1");
        contact1.setOperatorLogin("111");
        contact1.setContactNumber(1);
        contact1.setStartDate(11L);
        contact1.setDuration(111L);
        contact1.setMessages(Arrays.asList(message1));
        contact1.setRequest(request1);
        conactRepository.save(contact1);

        Contact contact2 = new Contact();
        contact2.setId("2");
        contact2.setOperatorLogin("222");
        contact2.setContactNumber(1);
        contact2.setStartDate(22L);
        contact2.setDuration(222L);
        //contact2.setMessages(Arrays.asList(message1, message2));  // такого не должно быть
        contact2.setMessages(Arrays.asList(message2, message3));  // такого не должно быть
        contact2.setRequest(request2);
        conactRepository.save(contact2);

        Contact contact3 = new Contact();
        contact3.setId("3");
        contact3.setOperatorLogin("333");
        contact3.setContactNumber(2);
        contact3.setStartDate(133L);
        contact3.setDuration(33L);
        //contact2.setMessages(Arrays.asList(message1, message3));  // такого не должно быть
        contact3.setRequest(request2);
        conactRepository.save(contact3);
    }

//    @Test
//    public void getAllContacts() {
//        assertThat(contactService.getAllContacts())    // этот метод rest-сервиса в приложении не используется
//                .hasSize(3)
//                .extracting(Contact::getOperatorLogin)
//                .containsExactly("111", "222", "333");
//    }

    @Test
    public void getContactsByRequestId() {
        assertThat(contactService.getContactsByRequestId("1"))
                .extracting(Contact::getOperatorLogin)
                .containsExactly("111");
        assertThat(contactService.getContactsByRequestId("2"))
                .hasSize(2)
                .extracting(Contact::getOperatorLogin)
                .containsExactly("222", "333");
        assertThat(contactService.getContactsByRequestId("3"))
                .extracting(Contact::getOperatorLogin)
                .hasSize(0);
    }

//    @Test
//    public void getAllContactsByStartDate_empty() {    // этот метод rest-сервиса в приложении не используется
//        String contactStartDate = "1";
//        String contactEndDate = "10";
//        String order = "id";
//        int limit = 5;
//        int offset = 0;
//        assertThat(contactService.getAllContacts(contactStartDate, contactEndDate, order, limit, offset))
//                .extracting(Contact::getOperatorLogin)
//                .hasSize(0);
//    }

//    @Test
//    public void getAllContactsByStartDate_First() {    // этот метод rest-сервиса в приложении не используется
//        String contactStartDate = "1";
//        String contactEndDate = "11";
//        String order = "id";
//        int limit = 5;
//        int offset = 0;
//        assertThat(contactService.getAllContacts(contactStartDate, contactEndDate, order, limit, offset))
//                .extracting(Contact::getOperatorLogin)
//                .containsExactly("111");
//    }

//    @Test
//    public void getAllContactsByStartDate_Second() {    // этот метод rest-сервиса в приложении не используется
//        String contactStartDate = "21";
//        String contactEndDate = "22";
//        String order = "id";
//        int limit = 5;
//        int offset = 0;
//        assertThat(contactService.getAllContacts(contactStartDate, contactEndDate, order, limit, offset))
//                .extracting(Contact::getOperatorLogin)
//                .containsExactly("222");
//    }

//    @Test
//    public void getAllContactsByStartDate_BothFirst() {    // этот метод rest-сервиса в приложении не используется
//        String contactStartDate = "11";
//        String contactEndDate = "22";
//        String order = "id";
//        int limit = 5;
//        int offset = 0;
//        assertThat(contactService.getAllContacts(contactStartDate, contactEndDate, order, limit, offset))
//                .extracting(Contact::getOperatorLogin)
//                .containsExactly("111", "222");
//    }

//    @Test
//    public void getAllContactsCount_Zero() {    // этот метод rest-сервиса в приложении не используется
//        String contactStartDate = "1";
//        String contactEndDate = "10";
//        assertThat(contactService.getAllContactsCount(contactStartDate, contactEndDate))
//                .isZero();
//    }

//    @Test
//    public void getAllContactsCount_One() {    // этот метод rest-сервиса в приложении не используется
//        String contactStartDate = "1";
//        String contactEndDate = "11";
//        assertThat(contactService.getAllContactsCount(contactStartDate, contactEndDate))
//                .isOne();
//    }

//    @Test
//    public void getAllContactsCount_Two() {    // этот метод rest-сервиса в приложении не используется
//        String contactStartDate = "11";
//        String contactEndDate = "22";
//        assertThat(contactService.getAllContactsCount(contactStartDate, contactEndDate))
//                .isEqualTo(2);
//    }

//    @Test
//    public void getContactsByChannel() {    // этот метод rest-сервиса в приложении не используется
//        int channelId = 1;
//        assertThat(contactService.getContactsByChannel(channelId))
//                .extracting(Contact::getOperatorLogin)
//                .containsExactly("111");
//    }

//    @Test
//    public void getContacts() {    // этот метод rest-сервиса в приложении не используется
//        Integer channelId = 1;
//        Long filialId = 1L;
//        String operatorLogin = "111";
//        Integer closeStatus = 1;
//        Long regDateStart = 1L;
//        Long regDateEnd = 11L;
//        //String messageText = "%1111%";
//        String messageText = "";
//        String order = "id";
//        int limit = 5;
//        int offset = 0;
//        assertThat(contactService.getContacts(channelId, filialId, operatorLogin, closeStatus, regDateStart, regDateEnd,
//                    messageText, limit, offset, order))
//                .extracting(Contact::getOperatorLogin)
//                .containsExactly("111");
//    }

//    @Test
//    public void countContacts() {    // этот метод rest-сервиса в приложении не используется
//        Integer channelId = 1;
//        Long filialId = 1L;
//        String operatorLogin = "111";
//        Integer closeStatus = 1;
//        Long regDateStart = 1L;
//        Long regDateEnd = 11L;
//        //String messageText = "%1111%";
//        String messageText = "";
//        String order = "id";
//        int limit = 5;
//        int offset = 0;
//        assertThat(contactService.countContacts(channelId, filialId, operatorLogin, closeStatus, regDateStart, regDateEnd, messageText))
//                .isEqualTo(1);
//    }

// ----------------------------------------getContactsByFilter-------------------------------------------------------------------

//    @Test
//    public void getContactsByFilter_ByID() {    // этот метод rest-сервиса в приложении не используется
//        ContactFilterPost filter = new ContactFilterPost();
//        filter.setGuid("1");
//        filter.setLimit(1);
//        filter.setOffset(1);
//        filter.setOrderList(Arrays.asList("1", "1"));
//
//        int limit = 5;
//        int offset = 0;
//        String order = "id";
//
//        assertThat(contactService.getContactsByFilter(filter, limit, offset, order))
//                .extracting(Contact::getOperatorLogin)
//                .containsExactly("111");
//    }

//    @Test
//    public void getContactsByFilter_ByRegDate1() {    // этот метод rest-сервиса в приложении не используется
//        ContactFilterPost filter = new ContactFilterPost();
//        filter.setRegDateStart(1L);
//        filter.setRegDateEnd(20L);
//        filter.setLimit(1);
//        filter.setOffset(1);
//        filter.setOrderList(Arrays.asList("1", "1"));
//
//        int limit = 1;
//        int offset = 0;
//        String order = "id";
//
//        assertThat(contactService.getContactsByFilter(filter, limit, offset, order))
//                .extracting(Contact::getOperatorLogin)
//                .containsExactly("111");
//    }

//    @Test
//    public void getContactsByFilter_ByRegDate2() {    // этот метод rest-сервиса в приложении не используется
//        ContactFilterPost filter = new ContactFilterPost();
//        filter.setRegDateStart(21L);
//        filter.setRegDateEnd(23L);
//        filter.setLimit(1);
//        filter.setOffset(5);
//        filter.setOrderList(Arrays.asList("1", "1"));
//
//        int limit = 2;
//        int offset = 0;
//        String order = "id";
//
//        assertThat(contactService.getContactsByFilter(filter, limit, offset, order))
//                .extracting(Contact::getOperatorLogin)
//                .containsExactly("222", "333");
//    }

//    @Test
//    public void getContactsByFilter_ByRegDate3() {    // этот метод rest-сервиса в приложении не используется
//        ContactFilterPost filter = new ContactFilterPost();
//        filter.setRegDateStart(11L);
//        filter.setRegDateEnd(23L);
//        filter.setLimit(1);
//        filter.setOffset(10);
//        filter.setOrderList(Arrays.asList("1", "1"));
//
//        int limit = 3;
//        int offset = 0;
//        String order = "id";
//
//        assertThat(contactService.getContactsByFilter(filter, limit, offset, order))
//                .extracting(Contact::getOperatorLogin)
//                .containsExactly("111", "222", "333");
//    }

//    @Test
//    public void getContactsByFilter_ByFilialsId1() {    // этот метод rest-сервиса в приложении не используется
//        ContactFilterPost filter = new ContactFilterPost();
//        filter.setFilialIdList(Arrays.asList(1L));
//        filter.setLimit(1);
//        filter.setOffset(1);
//        filter.setOrderList(Arrays.asList("1", "1"));
//
//        int limit = 1;
//        int offset = 0;
//        String order = "id";
//
//        assertThat(contactService.getContactsByFilter(filter, limit, offset, order))
//                .extracting(Contact::getOperatorLogin)
//                .containsExactly("111");
//    }

//    @Test
//    public void getContactsByFilter_ByFilialsId2() {    // этот метод rest-сервиса в приложении не используется
//        ContactFilterPost filter = new ContactFilterPost();
//        filter.setFilialIdList(Arrays.asList(2L));
//        filter.setLimit(1);
//        filter.setOffset(1);
//        filter.setOrderList(Arrays.asList("1", "1"));
//
//        int limit = 2;
//        int offset = 0;
//        String order = "duration";
//
//        assertThat(contactService.getContactsByFilter(filter, limit, offset, order))
//                .extracting(Contact::getOperatorLogin)
//                //.contains("222", "333");
//                .containsExactly("333", "222");
//    }

//    @Test
//    public void getContactsByFilter_ByFilialsId3() {    // этот метод rest-сервиса в приложении не используется
//        ContactFilterPost filter = new ContactFilterPost();
//        filter.setFilialIdList(Arrays.asList(1L, 2L));
//        filter.setLimit(1);
//        filter.setOffset(1);
//        filter.setOrderList(Arrays.asList("1", "1"));
//
//        int limit = 3;
//        int offset = 0;
//        String order = "id";
//
//        assertThat(contactService.getContactsByFilter(filter, limit, offset, order))
//                .extracting(Contact::getOperatorLogin)
//                .containsExactly("111", "222", "333");
//    }

// --------------------------------------------getAllContactsByFilter()---------------------------------------------------------------

    @Test
    public void getAllContactsByFilter_ByID_1() {
        ContactFilterPost filter = new ContactFilterPost();
        filter.setShowAllRequests(true);
        filter.setShowChatbots(false);
        filter.setGuid("1");
        filter.setLimit(1);
        filter.setOffset(0);

        assertThat(contactService.getAllContactsByFilter(filter))
                .extracting(Contact::getOperatorLogin)
                .containsExactly("111");
    }

    @Test
    public void getAllContactsByFilter_ByID_2() {
        ContactFilterPost filter = new ContactFilterPost();
        filter.setShowAllRequests(true);
        filter.setShowChatbots(false);
        filter.setGuid("2");
        filter.setLimit(1);
        filter.setOffset(0);

        assertThat(contactService.getAllContactsByFilter(filter))
                .extracting(Contact::getOperatorLogin)
                .containsExactly("222");
    }

    @Test
    public void getAllContactsByFilter_ByID_99_empty() {
        ContactFilterPost filter = new ContactFilterPost();
        filter.setShowAllRequests(true);
        filter.setShowChatbots(false);
        filter.setGuid("99");
        filter.setLimit(1);
        filter.setOffset(0);

        assertThat(contactService.getAllContactsByFilter(filter))
                .isNotNull()
                .hasSize(0);
    }

    @Test
    public void getAllContactsByFilter_ByRegDate1() {
        ContactFilterPost filter = new ContactFilterPost();
        filter.setShowAllRequests(true);
        filter.setShowChatbots(false);
        filter.setRegDateStart(1L);
        filter.setRegDateEnd(20L);
        filter.setLimit(1);
        filter.setOffset(0);

        assertThat(contactService.getAllContactsByFilter(filter))
                .extracting(Contact::getOperatorLogin)
                .containsExactly("111");
    }

    @Test
    public void getAllContactsByFilter_ByRegDate0() {
        ContactFilterPost filter = new ContactFilterPost();
        filter.setShowAllRequests(true);
        filter.setShowChatbots(false);
        filter.setRegDateStart(1L);
        filter.setRegDateEnd(10L);
        filter.setLimit(1);
        filter.setOffset(0);

        assertThat(contactService.getAllContactsByFilter(filter))
                .isNotNull()
                .hasSize(0);
    }

    @Test
    public void getAllContactsByFilter_ByRegDate2() {
        ContactFilterPost filter = new ContactFilterPost();
        filter.setShowAllRequests(true);
        filter.setShowChatbots(false);
        filter.setRegDateStart(21L);
        filter.setRegDateEnd(23L);
        filter.setLimit(2);
        filter.setOffset(0);

        assertThat(contactService.getAllContactsByFilter(filter))
                .extracting(Contact::getOperatorLogin)
                //.containsExactly("333", "222");
                .containsExactly("222", "333");
    }

    @Test
    public void getAllContactsByFilter_ByRegDate3() {
        ContactFilterPost filter = new ContactFilterPost();
        filter.setShowAllRequests(true);
        filter.setShowChatbots(false);
        filter.setRegDateStart(11L);
        filter.setRegDateEnd(23L);
        filter.setLimit(3);
        filter.setOffset(0);

        assertThat(contactService.getAllContactsByFilter(filter))
                .extracting(Contact::getOperatorLogin)
                //.containsExactly("333", "222", "111");
                .containsExactly("222", "333", "111");
    }

//    @Test
//    public void getAllContactsByFilter_ByFilialsId1() {
//        ContactFilterPost filter = new ContactFilterPost();
//        filter.setFilialIdList(Arrays.asList("1"));
//        filter.setLimit(1);
//        filter.setOffset(0);
//
//        assertThat(contactService.getAllContactsByFilter(filter))
//                .extracting(Contact::getOperatorLogin)
//                .containsExactly("111");
//    }

//    @Test
//    public void getAllContactsByFilter_ByFilialsIdAndSortBy_duration() {
//        ContactFilterPost filter = new ContactFilterPost();
//        filter.setFilialIdList(Arrays.asList("2"));
//        filter.setLimit(2);
//        filter.setOffset(0);
//        filter.setOrderList(Arrays.asList("duration"));
//
//        assertThat(contactService.getAllContactsByFilter(filter))
//                .extracting(Contact::getOperatorLogin)
//                .contains("222", "333")
//                .containsExactlyInAnyOrderElementsOf(Arrays.asList("222", "333"))
//                .containsExactly("333", "222");
//    }

//    @Test
//    public void getAllContactsByFilter_ByFilialsId0() {
//        ContactFilterPost filter = new ContactFilterPost();
//        filter.setFilialIdList(Arrays.asList("3"));
//        filter.setLimit(1);
//        filter.setOffset(0);
//
//        assertThat(contactService.getAllContactsByFilter(filter))
//                .isNotNull()
//                .hasSize(0);
//    }

//    @Test
//    public void getAllContactsByFilter_ByFilialsIdAndSortBy_id() {
//        ContactFilterPost filter = new ContactFilterPost();
//        filter.setFilialIdList(Arrays.asList("1", "2"));
//        filter.setLimit(3);
//        filter.setOffset(0);
//        filter.setOrderList(Arrays.asList("request.id"));
//
//        assertThat(contactService.getAllContactsByFilter(filter))
//                .extracting(Contact::getOperatorLogin)
//                .containsExactly("111", "222", "333");
//    }

    @Test
    public void getAllContactsByFilter_ByChannel_1() {
        ContactFilterPost filter = new ContactFilterPost();
        filter.setShowAllRequests(true);
        filter.setShowChatbots(false);
        filter.setChannelIdList(Arrays.asList(1));
        filter.setLimit(3);
        filter.setOffset(0);
        filter.setOrderList(Arrays.asList("id"));

        assertThat(contactService.getAllContactsByFilter(filter))
                .extracting(Contact::getOperatorLogin)
                .containsExactly("111");
    }

    @Test
    public void getAllContactsByFilter_ByChannel_2() {
        ContactFilterPost filter = new ContactFilterPost();
        filter.setShowAllRequests(true);
        filter.setShowChatbots(false);
        filter.setChannelIdList(Arrays.asList(2));
        filter.setLimit(3);
        filter.setOffset(0);
        filter.setOrderList(Arrays.asList("id"));

        assertThat(contactService.getAllContactsByFilter(filter))
                .extracting(Contact::getOperatorLogin)
                .containsExactly("222", "333");
    }

    @Test
    public void getAllContactsByFilter_ByChannel_1_2() {
        ContactFilterPost filter = new ContactFilterPost();
        filter.setShowAllRequests(true);
        filter.setShowChatbots(false);
        filter.setChannelIdList(Arrays.asList(1, 2));
        filter.setLimit(3);
        filter.setOffset(0);
        filter.setOrderList(Arrays.asList("request.id"));

        assertThat(contactService.getAllContactsByFilter(filter))
                .extracting(Contact::getOperatorLogin)
                .containsExactly("111", "222", "333");
    }

    @Test
    public void getAllContactsByFilter_ByChannel_3_empty() {
        ContactFilterPost filter = new ContactFilterPost();
        filter.setShowAllRequests(true);
        filter.setShowChatbots(false);
        filter.setChannelIdList(Arrays.asList(3));
        filter.setLimit(3);
        filter.setOffset(0);
        filter.setOrderList(Arrays.asList("id"));

        assertThat(contactService.getAllContactsByFilter(filter))
                .isNotNull()
                .hasSize(0);
    }

    @Test
    public void getAllContactsByFilter_ByLogin_1() {
        ContactFilterPost filter = new ContactFilterPost();
        filter.setShowAllRequests(true);
        filter.setShowChatbots(false);
        filter.setOperatorLogin("111");
        filter.setLimit(3);
        filter.setOffset(0);

        assertThat(contactService.getAllContactsByFilter(filter))
                .extracting(Contact::getOperatorLogin)
                .containsExactly("111");
    }

    @Test
    public void getAllContactsByFilter_ByLogin_2_3() {
        ContactFilterPost filter = new ContactFilterPost();
        filter.setShowAllRequests(true);
        filter.setShowChatbots(false);
        filter.setOperatorLoginList(Arrays.asList("222", "333"));
        filter.setLimit(3);
        filter.setOffset(0);

        assertThat(contactService.getAllContactsByFilter(filter))
                .extracting(Contact::getOperatorLogin)
                //.containsExactly("333", "222");
                .containsExactly("222", "333");
    }

    @Test
    public void getAllContactsByFilter_ByLogin_4_empty() {
        ContactFilterPost filter = new ContactFilterPost();
        filter.setShowAllRequests(true);
        filter.setShowChatbots(false);
        filter.setOperatorLoginList(Arrays.asList("444"));
        filter.setLimit(3);
        filter.setOffset(0);

        assertThat(contactService.getAllContactsByFilter(filter))
                .isNotNull()
                .hasSize(0);
    }

// --------------------------------------------countAllContactsByFilter()---------------------------------------------------------------

    @Test
    public void countAllContactsByFilter_ByID() {
        ContactFilterPost filter = new ContactFilterPost();
        filter.setShowAllRequests(true);
        filter.setShowChatbots(false);
        filter.setGuid("1");
        filter.setLimit(1);
        filter.setOffset(0);

        assertThat(contactService.countAllContactsByFilter(filter))
                .isEqualTo(1);
    }


    @Test
    public void countAllContactsByFilter_ByRegDate2() {
        ContactFilterPost filter = new ContactFilterPost();
        filter.setShowAllRequests(true);
        filter.setShowChatbots(false);
        filter.setRegDateStart(21L);
        filter.setRegDateEnd(23L);
        filter.setLimit(2);
        filter.setOffset(0);

        assertThat(contactService.countAllContactsByFilter(filter))
                .isEqualTo(2);
    }
}