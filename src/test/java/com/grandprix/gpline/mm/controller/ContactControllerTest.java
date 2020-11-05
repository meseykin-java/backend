package com.grandprix.gpline.mm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grandprix.gpline.mm.model.Contact;
import com.grandprix.gpline.mm.model.filter.ContactFilterPost;
//import com.grandprix.gpline.mm.model.filter.RequestStatus;
import com.grandprix.gpline.mm.service.ContactService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ContactController.class)
class ContactControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContactService contactService;
    private ContactFilterPost filter = new ContactFilterPost();
    private Contact contact1 = new Contact();
    private Contact contact2 = new Contact();
    private Integer limit = 1;
    private Integer offset = 1;
    private String order = "1";

    @BeforeEach
    void setUp() {
        contact1.setId("1");
        contact2.setId("2");

        filter.setGuid("1");
        filter.setRegDateStart(1L);
        filter.setRegDateEnd(1L);
        filter.setCloseDateStart(1L);
        filter.setCloseDateEnd(1L);
        filter.setFilialIdList(Arrays.asList("1","1"));
        filter.setRegularGroupIdList(Arrays.asList(1L,1L));
        filter.setOperatorLoginList(Arrays.asList("1","1"));
        filter.setOperatorLogin("1");
        filter.setSupervisorLoginList(Arrays.asList("1","1"));
        filter.setChannelIdList(Arrays.asList(1,1));
        filter.setDirection(true);
//        filter.setRequestStatus(RequestStatus.REQUEST_CLOSED_BY_SUPERVISOR.ordinal());
        filter.setCloseStatusList(Arrays.asList(1,1));
        filter.setMessageText("1");
        filter.setContactNumberRel("1");
        filter.setContactNumber(1);
        filter.setFromNumber("1");
        filter.setToNumber("1");
        filter.setClientClass("1");
        filter.setRequestPriority(1);
        filter.setTransferTo("1");
        //filter.setClientType("1");
        filter.setTransferExists(true);
        filter.setDurationStart(1);
        filter.setDurationEnd(1);
        filter.setLimit(1);
        filter.setOffset(1);
        filter.setOrderList(Arrays.asList("1","1"));
    }

//    @Test
//    void getAllContacts() throws Exception {    // этот метод rest-сервиса в приложении не используется
//        given(contactService.getAllContacts()).willReturn(Arrays.asList(contact1, contact2));
//        mockMvc.perform(get("/contacts/all")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].id", is(contact1.getId())))
//                .andExpect(jsonPath("$[1].id", is(contact2.getId())));
//    }

    @Test
    void getContacts() throws Exception {
        String requestId = "1";

        given(contactService.getContactsByRequestId(requestId)).willReturn(Arrays.asList(contact1, contact2));
        mockMvc.perform(get("/contacts/request/{requestId}", requestId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(contact1.getId())))
                .andExpect(jsonPath("$[1].id", is(contact2.getId())));
    }

//    @Test
//    void getAllContactsByByStartDate() throws Exception {    // этот метод rest-сервиса в приложении не используется
//        String startDate = "1";
//        String endDate = "1";
//
//        given(contactService.getAllContacts(startDate, endDate, order, limit, offset)).willReturn(Arrays.asList(contact1, contact2));
//        mockMvc.perform(get("/contacts")
//                    .param("startDate", startDate)
//                    .param("endDate", endDate)
//                    .param("limit", String.valueOf(limit))
//                    .param("offset", String.valueOf(offset))
//                    .param("order", order)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].id", is(contact1.getId())))
//                .andExpect(jsonPath("$[1].id", is(contact2.getId())));
//    }

//    @Test
//    void getAllContactsCount() throws Exception {    // этот метод rest-сервиса в приложении не используется
//        String startDate = "1";
//        String endDate = "1";
//
//        int count = 5;
//        given(contactService.getAllContactsCount(startDate, endDate)).willReturn(count);
//        mockMvc.perform(get("/contacts/count")
//                    .param("startDate", startDate)
//                    .param("endDate", endDate)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", equalTo(count)));
//    }

//    @Test
//    void getContactsByChannel() throws Exception {    // этот метод rest-сервиса в приложении не используется
//        Integer id = 1;
//
//        given(contactService.getContactsByChannel(id)).willReturn(Arrays.asList(contact1, contact2));
//        mockMvc.perform(get("/contacts/channel/{id}", id)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].id", is(contact1.getId())))
//                .andExpect(jsonPath("$[1].id", is(contact2.getId())));
//    }

//    @Test
//    void getContactsByGetFilter() throws Exception {    // этот метод rest-сервиса в приложении не используется
//        Integer channelId = 1;
//        Long filialId = 1L;
//        String operatorLogin = "1";
//        Integer closeStatus = 1;
//        Long regDateStart = 1L;
//        Long regDateEnd = 1L;
//        String messageText = "1";
//
//        given(contactService.getContacts(channelId, filialId, operatorLogin, closeStatus, regDateStart, regDateEnd,
//                messageText, limit, offset, order)).willReturn(Arrays.asList(contact1, contact2));
//        mockMvc.perform(get("/contacts/full")
//                    .param("channelId", String.valueOf(channelId))
//                    .param("filialId", String.valueOf(filialId))
//                    .param("operatorLogin", operatorLogin)
//                    .param("closeStatus", String.valueOf(closeStatus))
//                    .param("regDateStart", String.valueOf(regDateStart))
//                    .param("regDateEnd", String.valueOf(regDateEnd))
//                    .param("messageText", messageText)
//                    .param("limit", String.valueOf(limit))
//                    .param("offset", String.valueOf(offset))
//                    .param("order", order)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].id", is(contact1.getId())))
//                .andExpect(jsonPath("$[1].id", is(contact2.getId())));
//    }

//    @Test
//    void getRequestsCount() throws Exception {    // этот метод rest-сервиса в приложении не используется
//        Integer channelId = 1;
//        Long filialId = 1L;
//        String operatorLogin = "1";
//        Integer closeStatus = 1;
//        Long regDateStart = 1L;
//        Long regDateEnd = 1L;
//        String messageText = "1";
//
//        Long count = 5L;
//        given(contactService.countContacts(channelId, filialId, operatorLogin, closeStatus, regDateStart, regDateEnd, messageText)).willReturn(count);
//        mockMvc.perform(get("/contacts/full/count")
//                    .param("channelId", String.valueOf(channelId))
//                    .param("filialId", String.valueOf(filialId))
//                    .param("operatorLogin", operatorLogin)
//                    .param("closeStatus", String.valueOf(closeStatus))
//                    .param("regDateStart", String.valueOf(regDateStart))
//                    .param("regDateEnd", String.valueOf(regDateEnd))
//                    .param("messageText", messageText)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", equalTo(count.intValue())));
//    }

//    @Test
//    void getContactsByFilter() throws Exception {    // этот метод rest-сервиса в приложении не используется
//        filter.setRegDateStart(1L);
//        filter.setRegDateEnd(1L);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = objectMapper.writeValueAsString(filter);
//
//        given(contactService.getContactsByFilter(filter, limit, offset, order)).willReturn(Arrays.asList(contact1, contact2));
//        mockMvc.perform(post("/contacts/filter")
//                    .param("limit", String.valueOf(limit))
//                    .param("offset", String.valueOf(offset))
//                    .param("order", order)
//                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
//                .content(json))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].id", is(contact1.getId())))
//                .andExpect(jsonPath("$[1].id", is(contact2.getId())));
//    }

    @Test
    void getAllContactsByFilter() throws Exception {
        filter.setRegDateStart(1L);
        filter.setRegDateEnd(1L);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(filter);

        given(contactService.getAllContactsByFilter(filter)).willReturn(Arrays.asList(contact1, contact2));
        mockMvc.perform(post("/contacts/filter/all")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(contact1.getId())))
                .andExpect(jsonPath("$[1].id", is(contact2.getId())));
    }

    @Test
    void countAllContactsByFilter() throws Exception {
        filter.setRegDateStart(1L);
        filter.setRegDateEnd(1L);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(filter);

        Long count = 5L;

        given(contactService.countAllContactsByFilter(filter)).willReturn(count);
        mockMvc.perform(post("/contacts/filter/all/count")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", equalTo(count.intValue())));
    }
}